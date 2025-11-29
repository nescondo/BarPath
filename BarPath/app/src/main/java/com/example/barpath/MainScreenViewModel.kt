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
) : AndroidViewModel(app) {


    //colors for majority of app backgrounds (light/dark mode)
    val color1 = Color.DarkGray
    val color2 = Color.White
    val _color1 = mutableStateOf(color1)
    val _color2 = mutableStateOf(color2)
    //handling of history of collected data
    data class statistics(val reps: Int, val averageTime: Float, val depthAverage: Float)

    //specific variables for handling stored data
    private val _myStat = MutableStateFlow(emptyList<statistics>())
    val myStat = _myStat.asStateFlow()

    val _form = MutableStateFlow("good")
    val form = _form


    //Things for sensors

//function to remove a data entry in history list
fun removeStatistic(stat: statistics) {
    _myStat.update { list ->
        list - stat
    }
}

    //function to add a data entry in history list
    fun addStatistic(reps: Int, time: Float, depth: Float) {
        _myStat.update {
            it + statistics(reps, time, depth)
        }


    }
}