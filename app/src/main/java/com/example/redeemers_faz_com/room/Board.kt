package com.example.redeemers_faz_com.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class representing a board in the database
@Entity
data class Board (
    @PrimaryKey val id: Int, // Primary key for the board entity
    val name: String? // Name of the board (nullable)
)



