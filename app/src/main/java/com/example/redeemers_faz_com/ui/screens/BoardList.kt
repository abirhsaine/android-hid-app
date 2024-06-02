package com.example.redeemers_faz_com.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NewLabel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.room.Board
import com.example.redeemers_faz_com.util.SharedBoard


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun BoardList(navigateToBoard: (Int) -> Unit, database: AppDatabase) {
    // Mutable state to hold the list of boards
    var boards by remember { mutableStateOf(emptyList<Board>()) }

    // Collect boards from the database
    LaunchedEffect(Unit) {
        database.boardDao().getAllBoard().collect { allBoards ->
            boards = allBoards
        }
    }

    // Mutable state to hold the input for adding a new board
    var inputNewBoard by remember { mutableStateOf("") }

    // Get the keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current


    // Function to add a new board
    fun addNewBoard() {
        if(inputNewBoard.isNotBlank()) {
            val maxId = database.boardDao().getMaxId() ?: 0 // Get the maximum ID from the database
            val newId = maxId + 1 // Generate a new unique ID by incrementing the maximum ID
            val newBoard = Board(newId, inputNewBoard) // Create a new Board object with the generated ID

            database.boardDao().insertBoard(newBoard) // Insert the new board into the database
            inputNewBoard = "" // Clear the input field

            keyboardController?.hide() // Hide the keyboard after adding the new board
        }
    }

    // Composable function starts here
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
        modifier = Modifier.fillMaxWidth() // Make column full width
    ) {
        // TextField for entering the name of the new board
        Row(
            horizontalArrangement = Arrangement.Center, // Center horizontally
            modifier = Modifier.fillMaxWidth() // Make row full width
        ) {

            TextField(
                value = inputNewBoard,
                onValueChange = { inputNewBoard = it },
                label = { Text("Add Board") },
                modifier = Modifier.padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray, // Background color
                    textColor = Color.Black, // Text color
                    cursorColor = Color.Blue, // Cursor color
                    focusedIndicatorColor = Color.Blue, // Focused indicator color
                    unfocusedIndicatorColor = Color.Gray // Unfocused indicator color
                ),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            )
        }
        // Button for adding a new board
        Row(
            horizontalArrangement = Arrangement.Center, // Center horizontally
            modifier = Modifier.fillMaxWidth() // Make row full width
        ) {
            Button(
                onClick = { addNewBoard() },
                modifier = Modifier.padding(top = 8.dp) // Add some space between TextField and Button
            ) {
                Icon(Icons.Default.NewLabel, "Adding new Deck")
                Text("Add")
            }
        }
        // Lazy column to display the list of boards
        LazyColumn {
            items(boards) { board ->
                // Each board is displayed in a Card
                Card(
//                    onClick = { board.name?.let { navigateToBoard(it) } },

                    onClick = {
                        navigateToBoard(board.id)
                        SharedBoard.id=board.id;
                              },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .padding(0.dp)
                ) {
                    // Text displaying the name of the board
                    Text(
                        modifier = Modifier.padding(12.dp),

                        text = board.name ?: "",
                        color = Color.Black
                    )
                }
            }
        }
    }

}


