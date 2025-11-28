package com.example.barpath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
    LaunchedEffect(Unit) { trackingVm.startSensors() }



    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
            .background(mainVm._color1.value)
            .fillMaxHeight(),
    ) {
        Spacer(Modifier.height(100.dp))


        Text("Tracking: $isTracking",color = mainVm._color2.value)
        Text("Calibrating: $isCalibrating",color = mainVm._color2.value)
        Text("Reps: $repCount",color = mainVm._color2.value)

        Text("Accelerometer data:",color = mainVm._color2.value)
        Text(
            text = "x: %.3f   y: %.3f   z: %.3f".format(accel[0], accel[1], accel[2]),
            color = mainVm._color2.value
        )


        Spacer(modifier = Modifier.height(20.dp))

        Text("Gyroscope data:",color = mainVm._color2.value)
        Text(
            text = "x: %.3f   y: %.3f   z: %.3f".format(gyro[0], gyro[1], gyro[2]),
            color = mainVm._color2.value
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {trackingVm.startTracking()}
        ) {
            Text("Start Tracking")
        }

        Button(
            onClick = {trackingVm.stopTracking()}
        ) {
            Text("Stop Tracking")
        }
        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }
    }

    }

