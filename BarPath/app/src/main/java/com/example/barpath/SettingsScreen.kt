package com.example.barpath

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun SettingsScreen(
    vm: MainScreenViewModel,
    trackVm: TrackingScreenViewModel,
    onNavigateBack: () -> Unit = {}
) {
    var themeExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 86.dp),
                onClick = { themeExpanded = !themeExpanded }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "App Theme",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Light / Dark mode",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }



            DropdownMenu(
                expanded = themeExpanded,
                onDismissRequest = { themeExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Light Mode") },
                    onClick = {
                        vm.setLightMode()
                        themeExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Dark Mode") },
                    onClick = {
                        vm.setDarkMode()
                        themeExpanded = false
                    }
                )
            }


            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 86.dp),
                onClick = {trackVm.deleteAllWorkoutSets() }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Delete History",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Remove saved sets",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }


            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 86.dp),
                onClick = { onNavigateBack() }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Back",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

        }
    }
}