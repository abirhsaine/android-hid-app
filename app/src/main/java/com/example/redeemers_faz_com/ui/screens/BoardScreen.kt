package com.example.redeemers_faz_com.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redeemers_faz_com.BluetoothController
import com.example.redeemers_faz_com.KeyboardSender
import com.example.redeemers_faz_com.Shortcut
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.util.SpecialButton

// Board screen composable function
@Composable
fun BoardScreen(
    boardName: String,
    navigateToBluetooth: () -> Unit,
    navigateToHome: () -> Unit,
    bluetoothController: BluetoothController,
    appDatabase: AppDatabase
) {
    // Check if Bluetooth is connected
    val connected = bluetoothController.status as? BluetoothController.Status.Connected
    var keyboardSender: KeyboardSender? = null
    var isConnected = false

    // Initialize KeyboardSender if Bluetooth is connected
    if (connected != null) {
        keyboardSender = KeyboardSender(connected.btHidDevice, connected.hostDevice)
        isConnected = true
    }

    // Function to handle keyboard shortcut press
    fun press(shortcut: Shortcut, releaseModifiers: Boolean = true): Boolean {
        @SuppressLint("MissingPermission")
        if (keyboardSender == null) {
            Log.e("PressShortcut", "Bluetooth not connected")
            return false
        } else {
            val result = keyboardSender.sendKeyboard(shortcut.shortcutKey, shortcut.modifiers, releaseModifiers)
            if (!result) {
                Log.e("PressShortcut", "Error sending shortcut")
                return false
            } else {
                return true
            }
        }
    }

    // Surface composable to draw a container with a background color
    Surface(
        modifier = Modifier.fillMaxSize().background(Color.Black),
    ) {
        // Column composable to arrange children vertically
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Display message and button to activate Bluetooth if not connected
            if (!isConnected) {
                Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Please connect to your device", color = Color.Magenta, modifier = Modifier.padding(end = 6.dp))

                }

                Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    TextButton(
                        onClick = { navigateToBluetooth() }
                    ) {
                        Text("click to connect now")
                    }
                }
            }

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
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.White)
                .padding(top = 5.dp))

            // Text composable to display board name with specified style
            Text("Board ID : $boardName", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            // Spacer composable to add space between elements
            Spacer(modifier = Modifier.height(10.dp))


            // Call KeysList composable to display keyboard shortcut cards
            KeysList( database = appDatabase, onPressCard = { shortcut, releaseModifiers -> press(shortcut, releaseModifiers) })
        }
    }
}
