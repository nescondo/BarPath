package com.example.barpath

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PictureScreen (vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {

    Column(
         // Fills remaining vertical space
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(100.dp)) // Fills remaining vertical space
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
    }
}