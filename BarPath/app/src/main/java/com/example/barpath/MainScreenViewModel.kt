package com.example.barpath

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {

    //colors for majority of app backgrounds (light/dark mode)
    val color1 = Color.LightGray
    val color2 = Color.Black
    val _color1 = mutableStateOf(color1)
    val _color2 = mutableStateOf(color2)
    //handling of history of collected data
    data class statistics(val form: String)

    //specific variables for handling stored data
    private val _myStat = MutableStateFlow(emptyList<statistics>())
    val myStat = _myStat.asStateFlow()

    val _form = MutableStateFlow("good")
    val form = _form

    fun removeStatistic(stat: statistics) {
        _myStat.update { list ->
            list - stat
        }
    }

    fun addStatistic() {
        _myStat.update {
            it + statistics(_form.value)
        }


    }
}