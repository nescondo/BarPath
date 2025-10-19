package com.example.barpath

import android.R.attr.top
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ResultsHistoryScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}){


    val statsList by vm.myStat.collectAsState()

    Row(modifier = Modifier.background(vm._color1.value)
        .fillMaxHeight().fillMaxWidth()) {
        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { vm.addStatistic() },

            ) {
            Text("add dummy data")
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
        .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(statsList) { stat ->
            Card(
                modifier = Modifier.fillMaxWidth().background(Color.Blue),
                ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    //Text("Stats for game #${vm.numGames.collectAsState().value}")

                    Text("form: ${stat.form}")
                    Text("(filler image, will be photo taken/uploaded)")
                    Image(
                        painter = painterResource(id = stat.image),
                        contentDescription = "Form image",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        //.rotate(vm._rotation.value)
                    )
                }
                Button(
                    onClick = {vm.removeStatistic(stat)},
                ) {
                    Text("Delete")
                }
            }
        }
    }





}