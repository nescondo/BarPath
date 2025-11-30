package com.example.barpath

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeTracking(trackingVm: TrackingScreenViewModel) {

    val sets by trackingVm.sets.collectAsState(emptyList())
    val latestSet = sets.firstOrNull()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Last Session",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    icon = Icons.Default.FitnessCenter,
                    label = "Reps",
                    value = latestSet?.totalReps?.toString() ?: "0",
                    modifier = Modifier
                        .weight(1f)
                )
                StatItem(
                    icon = Icons.Default.Timer,
                    label = "Duration",
                    value = trackingVm.convertMillisecondsToMinutesAndSeconds(latestSet?.duration ?: 0f),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    icon = Icons.AutoMirrored.Filled.TrendingUp,
                    label = "Avg Rep Speed",
                    value = latestSet?.avgSpeed?.let { "%.1fs".format(it) } ?: "0.0s",
                    modifier = Modifier
                        .weight(1f)
                )
                StatItem(
                    icon = Icons.Default.Star,
                    label = "Lowest Depth",
                    value = latestSet?.lowestDepth?.let { "%.2f\u00B0".format(it) } ?: "0.00\u00B0",
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}