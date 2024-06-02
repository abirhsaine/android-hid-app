package com.example.redeemers_faz_com.ui.screens

// Import statements
import android.view.KeyEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.NewLabel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redeemers_faz_com.Shortcut
import com.example.redeemers_faz_com.room.AppDatabase
import com.example.redeemers_faz_com.room.Key
import com.example.redeemers_faz_com.util.DialogNewKeys
import com.example.redeemers_faz_com.util.SharedBoard
import compose.icons.AllIcons
import compose.icons.FeatherIcons
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Data class representing a tile
data class Tile(
    var name: String,
    var command: String,
    var icon: String
)

// Composable function for displaying a list of keyboard shortcut cards
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KeysList( //TODO : Zak to everybody , we must find why selectedBoardId is not working
    selectedBoardId: Int=0,
    onPressCard: (shortcut: Shortcut, releaseModifiers: Boolean) -> Boolean,
    database: AppDatabase
) {
    // Mutable state for managing the list of tiles
//    val keys by remember {
//        mutableStateOf(mutableStateListOf<Tile?>(*Array<Tile>(12) { Tile(name="", command="", icon="") }))
//    }

    // Mutable state to hold the list of keys
    var keys by remember { mutableStateOf(emptyList<Key>()) }

    // Collect keys from the database
    LaunchedEffect(Unit) {
        database.keysDao().getKeysByBoard(SharedBoard.id).collect { allKeys ->
            keys = allKeys
        }
    }

    // Convert keys to tiles
    var tiles = keys.map { key ->
        Tile(
            name = key.name ?: "",
            command = key.command ?: "",
            icon = key.icon ?: ""
        )
    }


    var openDialogNewKey = remember { mutableStateOf(false) }
    var newKey = remember { mutableIntStateOf(-1) }
    var allIcons = remember { FeatherIcons.AllIcons.take(20) } // to avoid app from crashing i am retrieving only 30 icons


    fun getIcon(icon : String): ImageVector {
        allIcons.forEach {
            if(it.name == icon) {
                return it
            }
        }
        return Icons.Filled.Abc
    }

//    fun addKey(key : Int, name : String, command : String, icon : String) {
//        keys[key] = Tile(name, command, icon)
//        openDialogNewKey.value = false
//
//        // Insert the new key into the database
//        GlobalScope.launch {
//            // Insert the new key asynchronously
//            database.keysDao().insertKeys(Key(0, SharedBoard.id, name, command, icon))
//        }
//    }
    fun addKey(key: Int, name: String, command: String, icon: String) {
        val newTile = Tile(name, command, icon)
        tiles = tiles.toMutableList().apply {
            add(newTile)
        }

        openDialogNewKey.value = false

        // Insert the new key into the database
        GlobalScope.launch {
            // Insert the new key asynchronously
            database.keysDao().insertKeys(Key(0, SharedBoard.id, name, command, icon))
        }
    }


    Text(
        text = "Keys Manager",
        color = Color.Black,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    )

    Button(
        onClick = {
            openDialogNewKey.value = true
        },
        modifier = Modifier
            .wrapContentSize()
    ) {
        Icon(Icons.Filled.NewLabel, "Floating action button.")
        Text("Add")
    }

    Text(
        text = "selectedBoardId : ${SharedBoard.id}",
        color = Color.Black,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    )


    Column {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxHeight()
    ) {

            items(tiles) { tile ->
            // Each item wrapped in a Column
                Row {
                    Box(modifier = Modifier.padding(10.dp)) {
                        Card(
                            onClick = {
                                onPressCard(
                                    Shortcut(KeyEvent.keyCodeFromString(tile.command)),
                                    true
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 8.dp
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    imageVector = getIcon(tile.icon),
                                    contentDescription = "Icon",
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                                Column {
                                    if (tile.name == "") {
                                        Text(
                                            text = "Without Name",
                                            fontStyle = FontStyle.Italic
                                        )
                                    } else {
                                        Text(text = tile.name)
                                    }
                                    Text(text = tile.command)
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    // Display the dialog for adding new keys
    when {
        openDialogNewKey.value -> {
            DialogNewKeys(
                newKeys = newKey.value,
                icons = allIcons,
                getIcon = { icon -> getIcon(icon) },
                onDismissRequest = { openDialogNewKey.value = false },
                onConfirmation = { key, name, command, icon -> addKey(key, name, command, icon) }
            )
        }
    }
}

