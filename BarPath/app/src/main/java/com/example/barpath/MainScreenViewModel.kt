package com.example.barpath

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainScreenViewModel: ViewModel() {


    val selectedTheme = MutableList<Boolean?>(2) { false }
    val _selectedTheme = MutableStateFlow(selectedTheme)
}