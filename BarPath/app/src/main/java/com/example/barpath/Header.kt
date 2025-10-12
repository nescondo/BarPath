package com.example.barpath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.black))
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp),
                text = "BarPath",
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}