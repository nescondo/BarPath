package com.example.barpath

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainScreenViewModel(app: Application
) : AndroidViewModel(app), SensorEventListener {





    //colors for majority of app backgrounds (light/dark mode)
    val color1 = Color.LightGray
    val color2 = Color.Black
    val _color1 = mutableStateOf(color1)
    val _color2 = mutableStateOf(color2)
    //handling of history of collected data
    data class statistics(val form: String, val image: Int)

    //specific variables for handling stored data
    private val _myStat = MutableStateFlow(emptyList<statistics>())
    val myStat = _myStat.asStateFlow()

    val _form = MutableStateFlow("good")
    val form = _form


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
    private val sensorManager =
        app.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private var accelSensor: Sensor? = null
    private var gyroSensor: Sensor? = null

    fun startSensors() {
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        accelSensor?.let {
            sensorManager.registerListener(
                this, it, SensorManager.SENSOR_DELAY_GAME
            )
        }

        gyroSensor?.let {
            sensorManager.registerListener(
                this, it, SensorManager.SENSOR_DELAY_GAME
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
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


//function to remove a data entry in history list
fun removeStatistic(stat: statistics) {
    _myStat.update { list ->
        list - stat
    }
}

    //function to add a data entry in history list
    fun addStatistic() {
        _myStat.update {
            it + statistics(_form.value, R.drawable.sample)
        }


    }
}