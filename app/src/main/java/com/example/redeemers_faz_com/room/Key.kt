package com.example.redeemers_faz_com.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class representing a key in the database
@Entity
data class Key (
    @PrimaryKey(autoGenerate = true) val id: Int, // Primary key for the key entity
    @ColumnInfo(name = "id_board") val idBoard: Int, // Foreign key referencing the associated board
    val name: String?, // Name of the key (nullable)
    val command: String?, // Command associated with the key (nullable)
    val icon: String? // Icon associated with the key (nullable)
)
