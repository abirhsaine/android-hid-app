package com.example.redeemers_faz_com.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Data Access Object (DAO) interface for Key entities
@Dao
interface KeysDAO {
    // Function to insert keys into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeys(key: Key)

    // Function to delete keys from the database
    @Delete
    fun deleteKeys(key: Key)

    // Function to retrieve all keys from the database as a Flow
    @Query("SELECT * FROM `key`")
    fun getAllKeys(): Flow<List<Key>>

    // Function to retrieve keys by board ID from the database as a Flow
    @Query("SELECT * FROM `key` WHERE id_board = :idBoard")
    fun getKeysByBoard(idBoard: Int): Flow<List<Key>>





}
