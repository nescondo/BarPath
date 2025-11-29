package com.example.barpath

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.barpath.data.SquatRep
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.abs

class TrackingScreenViewModel(app: Application) : AndroidViewModel(app), SensorEventListener {

    //Things for sensors
    private val _accel = MutableStateFlow(FloatArray(3))
    val accel = _accel.asStateFlow()

    private val _gyro = MutableStateFlow(FloatArray(3))
    val gyro = _gyro.asStateFlow()

    val slowAccel = accel
        .sample(200)
        .stateIn(viewModelScope, SharingStarted.Lazily, FloatArray(3))

    val slowGyro = gyro
        .sample(200)
        .stateIn(viewModelScope, SharingStarted.Lazily, FloatArray(3))

    private val sensorManager = app.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var accelSensor: Sensor? = null
    private var gyroSensor: Sensor? = null
    private var integratedPitch = 0f
    private var lastGyroTimestamp = 0L
    //initial state (calibration period)
    private var baselineAngle = 0f
    private var squatState = SquatState.CALIBRATING
    private val _isCalibrating = MutableStateFlow(false)
    val isCalibrating = _isCalibrating.asStateFlow()
    private val _isTracking = MutableStateFlow(false)
    val isTracking = _isTracking.asStateFlow()

    private val minRequiredDepth = 40f

    private val _squatState = MutableStateFlow(SquatState.CALIBRATING)
    val squatStateFlow = _squatState.asStateFlow()

    //enum to determine phase of current squat
    enum class SquatState {
        CALIBRATING, READY, DESCENDING, AT_BOTTOM, ASCENDING
    }

    // individual rep tracking
    private var currentRepStartTime = 0L
    private var currentRepNumber = 0
    private var currentRepMaxDepth = 0f
    private var depthReached = false
    private val currentReps = mutableListOf<SquatRep>()
    private val _repCount = MutableStateFlow(0)
    val repCount = _repCount.asStateFlow()

    private val _currentDepth = MutableStateFlow(0f)
    val currentDepth = _currentDepth.asStateFlow()


    fun startSensors() {
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        accelSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_GAME
            )
        }

        gyroSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    fun stopSensors() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                _accel.value = event.values.clone()
            }

            Sensor.TYPE_GYROSCOPE -> {
                _gyro.value = event.values.clone()

                // Integrate gyroscope data to get pitch angle
                if (_isTracking.value && !_isCalibrating.value) {
                    val currentTime = event.timestamp

                    if (lastGyroTimestamp != 0L) {

                        // Calculate time difference in seconds
                        val dt = (currentTime - lastGyroTimestamp) / 1_000_000_000f

                        // Get angular velocity around X-axis (pitch rotation)
                        val angularVelocityX = event.values[0] // rad/s

                        // Convert to degrees and calculate angle change
                        val angleChange = Math.toDegrees(angularVelocityX.toDouble()).toFloat() * dt

                        // Integrate to get total pitch angle
                        integratedPitch += angleChange

                        //set the current depth
                        _currentDepth.value = abs(integratedPitch - baselineAngle)
                        lastGyroTimestamp = currentTime

                        println("drifting too much, reset pitch")
                        println("Integrated pitch: $integratedPitch, angular velocity: $angularVelocityX rad/s")

                        processRepData(integratedPitch)
                    }

                    lastGyroTimestamp = currentTime
                }
            }
        }
    }

    // Tracking reps begin here
    private fun startCalibration() {
        println("in startCalibration")

        _isCalibrating.value = true
        _squatState.value = SquatState.CALIBRATING

        integratedPitch = 0f
        lastGyroTimestamp = 0L

        // wait 5 seconds (to put phone in pocket, set up under barbell, get into position, etc)
        viewModelScope.launch {
            delay(5000)

            baselineAngle = 0f //should always be ~0
            println("BASELINE PITCH: $baselineAngle")

            _isCalibrating.value = false
            _squatState.value = SquatState.READY
        }
    }

    fun startTracking() {
        println("in startTracking")

        _isTracking.value = true
        currentReps.clear()
        currentRepNumber = 0
        _repCount.value = 0
        depthReached = false

        integratedPitch = 0f
        lastGyroTimestamp = 0L

        startCalibration()
    }

    fun stopTracking() {
        println("Final reps: $currentReps")

        _isTracking.value = false
        lastGyroTimestamp = 0L
    }

    fun detectDescent(pitch: Float) {
        val pitchChange = abs(pitch - baselineAngle)
        println("detectDescent: pitch=$pitch, baseline=$baselineAngle, pitchChange=$pitchChange")

        if (pitchChange > 5f) {
            println("DESCENDING")
            _squatState.value = SquatState.DESCENDING

            currentRepStartTime = System.currentTimeMillis()
            currentRepMaxDepth = pitchChange
        }
    }

    fun detectBottom(pitch: Float) {
        val pitchChange = abs(pitch - baselineAngle)
        println("detectBottom: pitchChange=$pitchChange, maxDepth=$currentRepMaxDepth")

        // track max depth
        if (pitchChange > currentRepMaxDepth) {
            currentRepMaxDepth = pitchChange
        }

        // Check if minimum depth reached (parallel = 90, 5 degree threshold)
        if (pitchChange >= minRequiredDepth - 5 && !depthReached) {
            println("AT_BOTTOM (parallel reached at $pitchChange°)")
            depthReached = true
            _squatState.value = SquatState.AT_BOTTOM
        }
    }

    fun checkAscent(pitch: Float) {
        val pitchChange = abs(pitch - baselineAngle)
        println("checkAscent: pitchChange=$pitchChange, maxDepth=$currentRepMaxDepth")

        // Continue tracking max depth in case they go deeper
        if (pitchChange > currentRepMaxDepth) {
            currentRepMaxDepth = pitchChange
        }

        // Detect when starting to come back up (10 degree threshold)
        if (depthReached && pitchChange < currentRepMaxDepth - 10f) {
            println("ASCENDING")
            _squatState.value = SquatState.ASCENDING
        }
    }

    fun checkTop(pitch: Float) {

        val pitchChange = abs(pitch - baselineAngle)
        println("checkTop: pitchChange=$pitchChange")

        // Back to starting position (within 5 degrees of baseline)
        if (pitchChange <= 5f) {

            val duration = System.currentTimeMillis() - currentRepStartTime

            val completedRep = SquatRep(
                repNumber = currentRepNumber,
                totalDuration = duration,
                maxDepth = currentRepMaxDepth
            )

            currentReps.add(completedRep)
            currentRepNumber++
            _repCount.value = currentRepNumber
            println("REP COMPLETED: #$currentRepNumber, depth=${currentRepMaxDepth}°, duration=${duration}ms")
            currentRepMaxDepth = 0f
            depthReached = false
            _squatState.value = SquatState.READY
        }
    }

    fun processRepData(pitch: Float) {
        when (_squatState.value) {
            SquatState.CALIBRATING -> {}
            SquatState.READY -> detectDescent(pitch)
            SquatState.DESCENDING -> detectBottom(pitch)
            SquatState.AT_BOTTOM -> checkAscent(pitch)
            SquatState.ASCENDING -> checkTop(pitch)
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

}
