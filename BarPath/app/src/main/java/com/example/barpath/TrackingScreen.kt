package com.example.barpath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TrackingScreen (mainVm: MainScreenViewModel, trackingVm: TrackingScreenViewModel, onNavigateBack: () -> Unit ={}) {

    val accel by trackingVm.accel.collectAsState()
    val gyro by trackingVm.gyro.collectAsState()
    val repCount by trackingVm.repCount.collectAsState()
    val isTracking by trackingVm.isTracking.collectAsState()
    val isCalibrating by trackingVm.isCalibrating.collectAsState()
    val squatState by trackingVm.squatStateFlow.collectAsState()
    val depth by trackingVm.currentDepth.collectAsState()
    val averageTime by trackingVm.averageRepTime.collectAsState()
    val averageDepth by trackingVm.averageDepth.collectAsState()
    val currentMaxDepth by trackingVm.currentDepth.collectAsState()


    //LaunchedEffect(Unit) { trackingVm.startSensors() }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Spacer(Modifier.height(100.dp))
        Text("Total Reps: $repCount")
        Text("Current Depth: ${String.format("%.2f", depth)}\u00B0")
        Text("Average Rep Completion Time: ${String.format("%.2f", averageTime)}")
        Text("Average Depth Per Rep: ${String.format("%.2f", averageDepth)}\u00B0")
        Text("Max Depth Reached: ${String.format("%.2f", currentMaxDepth)}\u00B0")

        Spacer(modifier = Modifier.height(20.dp))

        Text("Tracking? $isTracking")
        Text("Calibrating? $isCalibrating")
        Text("State: $squatState")

        //Text("Accelerometer data:",color = mainVm._color2.value)
        //Text(
          //  text = "x: %.3f   y: %.3f   z: %.3f".format(accel[0], accel[1], accel[2]),
         //   color = mainVm._color2.value
      //  )


        Spacer(modifier = Modifier.height(20.dp))

        Text("Gyroscope data:")
        Text(
            text = "x: %.3f   y: %.3f   z: %.3f".format(gyro[0], gyro[1], gyro[2]),
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                mainVm.addStatistic(repCount,averageTime, averageDepth)
                trackingVm.stopTracking(save = true)
                trackingVm.stopSensors()
            }

        ) {
            Text("Save Set")
        }

        Button(
            onClick = {trackingVm.startTracking()
                trackingVm.startSensors()}
        ) {
            Text("Start Tracking")
        }

        Button(
            onClick = {trackingVm.stopTracking(save = false)
                trackingVm.stopSensors()}
        ) {
            Text("Cancel Tracking")
        }
        Button(
            onClick = { onNavigateBack()
            trackingVm.stopSensors()}
        ) {
            Text("Back")
        }
    }

    }

