package com.example.barpath

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.barpath.ui.theme.BarPathTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainScreenViewModel by viewModels<MainScreenViewModel>()
        //   enableEdgeToEdge()
        //  WindowCompat.getInsetsController(window, window.decorView).apply {
        //    isAppearanceLightStatusBars = false


        setContent {
            BarPathTheme {
                val nc = rememberNavController()
                val navBackStackEntry by nc.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val atHome = currentRoute?.endsWith("MainScreen") ?: false
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = nc,
                        startDestination = Route.MainScreen
                    ) {
                        composable<Route.MainScreen> {
                            MainScreen(mainScreenViewModel,
                                onSettingsClick = { nc.navigate(Route.SettingsScreen) },
                                onPictureClick = {nc.navigate(Route.PictureScreen)},
                                onAnalysisClick = {nc.navigate(Route.AnalysisScreen)},
                                onResultsClick = {nc.navigate(Route.ResultsHistoryScreen)},
                                //onStatisticsClick = { nc.navigate(Route.StatisticsScreen) }
                            )
                        }
                        composable<Route.SettingsScreen> {
                            SettingsScreen(
                                mainScreenViewModel,
                                onNavigateBack = { nc.popBackStack() }
                            )
                        }
                        composable<Route.PictureScreen> {
                            PictureScreen(mainScreenViewModel,
                                onNavigateBack = { nc.popBackStack() }
                            )
                        }
                        composable<Route.ResultsHistoryScreen> {
                            ResultsHistoryScreen(mainScreenViewModel,
                                onNavigateBack = { nc.popBackStack() }
                            )
                        }
                        composable<Route.AnalysisScreen> {
                            AnalysisScreen(mainScreenViewModel,
                                onNavigateBack = { nc.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}