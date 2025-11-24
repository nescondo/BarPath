package com.example.barpath

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensorClass (private val context: Context): SensorEventListener{
    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val _accelFlow = MutableStateFlow(FloatArray(3))
    val accelFlow: StateFlow<FloatArray> = _accelFlow

    private val _gyroFlow = MutableStateFlow(FloatArray(3))
    val gyroFlow: StateFlow<FloatArray> = _gyroFlow

    fun start() {
        val accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        accel?.let {
            sensorManager.registerListener(
                this, it, SensorManager.SENSOR_DELAY_GAME
            )
        }
        gyro?.let {
            sensorManager.registerListener(
                this, it, SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                _accelFlow.value = event.values.clone()
            }
            Sensor.TYPE_GYROSCOPE -> {
                _gyroFlow.value = event.values.clone()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
