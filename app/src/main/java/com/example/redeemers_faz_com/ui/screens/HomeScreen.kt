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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.room.AppRepository
import com.example.redeemers_faz_com.room.BoardViewModel
import com.example.redeemers_faz_com.util.SpecialButton


@Composable
fun HomeScreen(
    navigateToBluetooth: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToBoard: (Int) -> Unit,
    appDatabase: AppDatabase // Add the AppDatabase parameter here
) {
    var appRepository = AppRepository(boardDAO = appDatabase.boardDao(), keysDAO = appDatabase.keysDao())
    var boardViewModel = BoardViewModel(repository = appRepository)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialButton(
                    text = "Bluetooth",
                    onClick = { navigateToBluetooth() }
                )

                SpecialButton(
                    text = "Home",
                    onClick = { navigateToHome() }
                )
            }

            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.White)
                .padding(top = 5.dp))

            Text("Home", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            // Pass the appDatabase parameter to BoardList
            BoardList(navigateToBoard = navigateToBoard, database = appDatabase)
        }
    }
}
