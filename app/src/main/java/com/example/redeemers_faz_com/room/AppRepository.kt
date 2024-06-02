package com.example.redeemers_faz_com.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class AppRepository(private val boardDAO: BoardDAO, private val keysDAO: KeysDAO) {

    // Flow to observe changes in the list of boards
    val allBoard: Flow<List<Board>> = boardDAO.getAllBoard()

    // Flow to observe changes in the list of keys
    val allKey: Flow<List<Key>> = keysDAO.getAllKeys()

    // Function to get keys by board ID
    fun getKeysByBoard(idBoard: Int): Flow<List<Key>> {
        return keysDAO.getKeysByBoard(idBoard)
    }

    // Function to insert a new board into the database
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertBoard(board: Board) {
        boardDAO.insertBoard(board)
    }

    // Function to insert new keys into the database
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertKeys(key: Key) {
        keysDAO.insertKeys(key)
    }
}
