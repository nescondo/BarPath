package com.example.barpath

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


@Composable
fun SettingsScreen(vm: MainScreenViewModel, onNavigateBack: () -> Unit ={}) {
    var _selectedTheme by remember { mutableStateOf(false) }

    Column(){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){


        Text(
            modifier = Modifier
                .padding(start = 80.dp, top = 40.dp),
            text = "Select app theme:"
        )

        IconButton(
            modifier = Modifier
                .padding(top = 40.dp),
            onClick = { _selectedTheme = !_selectedTheme }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "More options")
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
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text("Dark Mode") },
                onClick = {}
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
