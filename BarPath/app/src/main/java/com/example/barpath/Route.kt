package com.example.barpath
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object MainScreen: Route()

    @Serializable
    data object SettingsScreen: Route()

    @Serializable
    data object PictureScreen: Route()

    @Serializable
    data object ResultsHistoryScreen: Route()

    @Serializable
    data object AnalysisScreen: Route()
}