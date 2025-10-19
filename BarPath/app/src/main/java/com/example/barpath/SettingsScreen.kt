package com.example.barpath

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@Composable
fun SettingsScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {


    var _selectedTheme by remember { mutableStateOf(false) }
    //var theme by remember {mutableStateOf(vm._theme.value)}
    //var tempTheme by remember {mutableStateOf(vm._theme.value)}
    Column(modifier = Modifier.background(vm._color1.value).fillMaxHeight()){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(vm._color1.value),
            verticalAlignment = Alignment.CenterVertically
        ){


        Text(
            modifier = Modifier
                .padding(start = 80.dp, top = 40.dp),
            color = vm._color2.value,
            text = "Select app theme:"
        )

        IconButton(
            modifier = Modifier
                .padding(top = 40.dp),

            onClick = { _selectedTheme = !_selectedTheme }) {
            Icon(Icons.Default.KeyboardArrowDown,
                contentDescription = "More options",
                tint = vm._color2.value)
        }
            Box(
                modifier = Modifier
                    .padding(start = 70.dp, top = 70.dp)

            ){
        DropdownMenu(
            expanded = _selectedTheme,
            onDismissRequest = { _selectedTheme = false }
        ) {
            DropdownMenuItem(
                text = { Text("Light Mode") },
                onClick = {vm._color1.value = Color.LightGray
                vm._color2.value = Color.Black}
            )
            DropdownMenuItem(
                text = { Text("Dark Mode") },
                onClick = {vm._color1.value = Color.DarkGray
                vm._color2.value = Color.White}
            )
        }
        }
    }
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Back")
        }

        Button(
            onClick = { onNavigateBack() }
        ) {
            Text("Delete History")
        }
}

    }
