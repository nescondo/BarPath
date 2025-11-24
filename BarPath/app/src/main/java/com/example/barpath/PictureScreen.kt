package com.example.barpath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PictureScreen (vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {
    val accel by vm.accel.collectAsState()
    val gyro by vm.gyro.collectAsState()

    LaunchedEffect(Unit) { vm.startSensors() }



    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
            .background(vm._color1.value)
            .fillMaxHeight(),
    ) {
        Spacer(Modifier.height(100.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Tracking")
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Stop Tracking")
        }

        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }
        Text("Debug info",color =vm._color2.value)


        Text("Accelerometer",color = vm._color2.value)
        Text("x: ${"%.3f".format(accel[0])}",color = vm._color2.value)
        Text("y: ${"%.3f".format(accel[1])}",color = vm._color2.value)
        Text("z: ${"%.3f".format(accel[2])}",color = vm._color2.value)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Gyroscope",color = vm._color2.value)
        Text("x: ${"%.3f".format(gyro[0])}",color = vm._color2.value)
        Text("y: ${"%.3f".format(gyro[1])}",color = vm._color2.value)
        Text("z: ${"%.3f".format(gyro[2])}",color = vm._color2.value)
        }
    }

