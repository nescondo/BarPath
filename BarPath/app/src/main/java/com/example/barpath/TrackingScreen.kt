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

    LaunchedEffect(Unit) { trackingVm.startSensors() }



    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
            .background(mainVm._color1.value)
            .fillMaxHeight(),
    ) {
        Spacer(Modifier.height(100.dp))
        Button(
            onClick = {trackingVm.startTracking()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Tracking")
        }

        Button(
            onClick = {trackingVm.stopTracking()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Stop Tracking")
        }

        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }
        Text("Debug info",color =mainVm._color2.value)


        Text("Accelerometer",color = mainVm._color2.value)
        Text("x: ${"%.3f".format(accel[0])}",color = mainVm._color2.value)
        Text("y: ${"%.3f".format(accel[1])}",color = mainVm._color2.value)
        Text("z: ${"%.3f".format(accel[2])}",color = mainVm._color2.value)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Gyroscope",color = mainVm._color2.value)
        Text("x: ${"%.3f".format(gyro[0])}",color = mainVm._color2.value)
        Text("y: ${"%.3f".format(gyro[1])}",color = mainVm._color2.value)
        Text("z: ${"%.3f".format(gyro[2])}",color = mainVm._color2.value)
        }
    }

