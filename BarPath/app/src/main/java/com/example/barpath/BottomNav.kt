package com.example.barpath

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomNav() {
    NavigationBar(
        containerColor = colorResource(id = R.color.black)
    ) {
        NavigationBarItem(
            selected = false,
            label = {
                Text(
                    modifier = Modifier
                        .offset(y = (-5).dp),
                    text = "Dashboard",
                )
            },
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.dashboard_icon),
                    contentDescription = "Dashboard icon",
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.bigger_add_circle_icon),
                    contentDescription = "Main navbar add icon",
                )
            }
        )
        NavigationBarItem(
            selected = false,
            label = {
                Text(
                    modifier = Modifier
                        .offset(y = (-5).dp),
                    text = "More",
                )
            },
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.more_icon),
                    contentDescription = "More icon",
                )
            }
        )
    }
}