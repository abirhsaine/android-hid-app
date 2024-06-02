package com.example.redeemers_faz_com.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redeemers_faz_com.BluetoothController
import com.example.redeemers_faz_com.BluetoothUiConnection
import com.example.redeemers_faz_com.theme.NavComposeTheme
import com.example.redeemers_faz_com.util.SpecialButton

// Bluetooth screen composable function
@Composable
fun BluetoothScreen(
    navigateToBluetooth: () -> Unit,
    navigateToHome: () -> Unit,
    bluetoothController: BluetoothController
) {
    // Surface composable to draw a container with a background color
    Surface(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        color = MaterialTheme.colors.background
    ) {
        // Column composable to arrange children vertically
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Row composable to arrange children horizontally with space evenly distributed
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Custom button for Bluetooth navigation
                SpecialButton(
                    text = "Bluetooth",
                    onClick = { navigateToBluetooth() }
                )

                // Custom button for Home navigation
                SpecialButton(
                    text = "Home",
                    onClick = { navigateToHome() }
                )
            }

            // Spacer composable to add space between elements
            Spacer(modifier = Modifier.height(1.dp).background(Color.White).padding(top = 5.dp))

            // Text composable to display "Bluetooth" text with specified style
            Text("Bluetooth", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            // Spacer composable to add space between elements
            Spacer(modifier = Modifier.height(20.dp))

            // Column composable to arrange children vertically
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Bluetooth connection UI
                BluetoothUiConnection(bluetoothController)
                // Bluetooth desk UI (commented out)
                // BluetoothDesk(bluetoothController)
            }
        }
    }
}

// Preview function to display a preview of the BluetoothScreen composable
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    // Create a preview BluetoothController instance
    val previewBluetoothController = BluetoothController()

    // NavComposeTheme to provide the theme for the preview
    NavComposeTheme(useSystemUiController = false) {
        // Surface composable to draw a container with a background color
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            // Bluetooth screen composable with preview parameters
            BluetoothScreen(
                navigateToBluetooth = {},
                navigateToHome = {},
                bluetoothController = previewBluetoothController,
            )
        }
    }
}
