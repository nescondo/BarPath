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
    val accel by vm.slowAccel.collectAsState()
    val gyro by vm.slowGyro.collectAsState()

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
            Text("Take Photo")
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload existing photo")
        }

        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }
        Text("Accelerometer")
        Text("X: ${accel[0]}  Y: ${accel[1]}  Z: ${accel[2]}")

        Spacer(Modifier.height(16.dp))

        Text("Gyroscope")
        Text("X: ${gyro[0]}  Y: ${gyro[1]}  Z: ${gyro[2]}")
    }

}