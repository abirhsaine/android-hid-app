package com.example.redeemers_faz_com.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.redeemers_faz_com.BluetoothController
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.ui.screens.BluetoothScreen
import com.example.redeemers_faz_com.ui.screens.BoardScreen
import com.example.redeemers_faz_com.ui.screens.HomeScreen

// Define the navigation graph for the application
@Composable
fun Naviagation(navController: NavHostController, bluetoothController: BluetoothController, appDatabase: AppDatabase) {

    // Initialize the navigation host
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        // Add the HomeScreen to the navigation graph
        addHomeScreen(navController, this, appDatabase)

        // Add the BluetoothScreen to the navigation graph
        addBluetoothScreen(navController, this, bluetoothController)

        // Add the BoardScreen to the navigation graph
        addBoardScreen(navController, this, bluetoothController, appDatabase)
    }
}

// Add the BluetoothScreen to the navigation graph
private fun addBluetoothScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    bluetoothController: BluetoothController,
) {
    navGraphBuilder.composable(route = NavRoute.BluetoothPage.path) {
        BluetoothScreen(
            // Navigate to the Bluetooth screen
            navigateToBluetooth = {
                navController.navigate(NavRoute.BluetoothPage.path)
            },
            // Navigate to the Home screen
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            },
            bluetoothController = bluetoothController
        )
    }
}

// Add the HomeScreen to the navigation graph
private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    appDatabase: AppDatabase
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(
            // Navigate to the Bluetooth screen
            navigateToBluetooth = {
                navController.navigate(NavRoute.BluetoothPage.path)
            },
            // Navigate to the Home screen
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            },
            // Navigate to the Board screen with a specified board name
            navigateToBoard = { boardName ->
                navController.navigate(NavRoute.BoardPage.path + "/$boardName") {
                    // Pop up to the Home screen when navigating to the Board screen
                    popUpTo(NavRoute.Home.path)
                }
            },
            appDatabase = appDatabase
        )
    }
}

// Add the BoardScreen to the navigation graph
private fun addBoardScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    bluetoothController: BluetoothController,
    appDatabase: AppDatabase
) {
    navGraphBuilder.composable(route = NavRoute.BoardPage.path + "/{boardName}") { backStackEntry ->

        BoardScreen(
            // Extract the board name from the navigation arguments
            backStackEntry.arguments?.getString("boardName") ?: "",
            // Navigate to the Bluetooth screen
            navigateToBluetooth = {
                navController.navigate(NavRoute.BluetoothPage.path)
            },
            // Navigate to the Home screen
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            },
            bluetoothController = bluetoothController,
            appDatabase=appDatabase
        )
    }
}
