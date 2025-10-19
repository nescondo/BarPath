package com.example.barpath

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun AnalysisScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {

   var rotationAngle by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.fillMaxWidth().
    fillMaxHeight().
    background(vm._color1.value)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(vm._color1.value),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Button(
                onClick = { onNavigateBack() }
            ) {
                Text("Back")
            }
            Button(onClick = { rotationAngle -= 10f }) {
                Text("Rotate Left")
            }
            Button(onClick = { rotationAngle += 10f }) {
                Text("Rotate Right")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample),
                contentDescription = "Sample image",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .rotate(rotationAngle),
                contentScale = ContentScale.Crop
            )
        }

    }
}

