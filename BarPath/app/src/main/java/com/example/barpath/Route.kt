package com.example.barpath
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object DashboardScreen

    @Serializable
    data object SettingsScreen

    @Serializable
    data object AddScreen
}