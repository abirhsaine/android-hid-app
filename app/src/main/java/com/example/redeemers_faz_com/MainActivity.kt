package com.example.redeemers_faz_com
import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.redeemers_faz_com.nav.Naviagation
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.theme.NavComposeTheme


class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var bluetoothController: BluetoothController
    private lateinit var appDatabase : AppDatabase

    // Function to ensure Bluetooth permissions
    private fun ensureBluetoothPermission(activity: ComponentActivity) {
        val requestPermissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted: Boolean ->
            if (isGranted) {
                Log.d(MainActivity.TAG, "Bluetooth connection granted")
            } else {
                Log.e(MainActivity.TAG, "Bluetooth connection not granted, Bye!")
                activity.finish()
            }
        }
        // Request BLUETOOTH_ADMIN permission
        requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
        // Request BLUETOOTH_CONNECT permission for Android S and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }

    // Function called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure Bluetooth permissions
        ensureBluetoothPermission(this)
        // Initialize Bluetooth controller and database
        bluetoothController = BluetoothController()
        appDatabase = AppDatabase.getDatabase(applicationContext)
        // Set content view using Compose
        setContent {
            MainScreen(bluetoothController, appDatabase)
        }
    }

    // Function called when the activity is paused
    override fun onPause() {
        super.onPause()
        // Release Bluetooth resources
        bluetoothController.release()
    }
}

// Composable function for the main screen
@Composable
private fun MainScreen(
    bluetoothController : BluetoothController,
    appDatabase : AppDatabase
) {
    // Apply the app theme
    NavComposeTheme {
        // Remember the navigation controller
        val navController = rememberNavController()
        // Display the navigation graph
        Naviagation(navController, bluetoothController, appDatabase)
    }
}

// Alias for key modifiers
typealias KeyModifier = Int
