package com.example.redeemers_faz_com.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ViewModel class for handling Board-related data
class BoardViewModel(private val repository: AppRepository) : ViewModel() {

    // LiveData to observe changes in the list of boards
    val allBoard: LiveData<List<Board>> = repository.allBoard.asLiveData()

    // Function to insert a new board into the database
    fun insertBoard(board: Board) = viewModelScope.launch {
        repository.insertBoard(board)
    }
}
