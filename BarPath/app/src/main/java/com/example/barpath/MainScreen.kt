package com.example.barpath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


//TODO: screens we should probably have: main(dashboard), settings, take picture/edit picture, screen to
//TODO: do form calculations, and screen for results/history of form
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    onPictureClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onResultsClick: () -> Unit,
    modifier: Modifier = Modifier.Companion)
{



    Column(modifier = Modifier.fillMaxSize().background(mainScreenViewModel._color1.value)) {
        Header()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {onPictureClick()}
            ) {
                Text("Tracking")
            }
            Button(
                onClick = {onSettingsClick()}
            ) {
                Text("Settings")
            }
            Button(
                onClick = {onAnalysisClick()}
            ) {
                Text("Analyze form")
            }
            Button(
                onClick = {onResultsClick()}
            ) {
                Text("History")
            }

        }


    }


}
