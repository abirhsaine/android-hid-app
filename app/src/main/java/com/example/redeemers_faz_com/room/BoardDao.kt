package com.example.redeemers_faz_com.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Data Access Object (DAO) interface for Board entities
@Dao
interface BoardDAO {
    // Function to insert a board into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoard(board: Board)

    // Function to delete a board from the database
    @Delete
    fun deleteBoard(board: Board)

    // Function to retrieve all boards from the database as a Flow
    @Query("SELECT * FROM board")
    fun getAllBoard(): Flow<List<Board>>

    @Query("SELECT MAX(id) FROM board")
    fun getMaxId(): Int?
}
