package com.cognizant.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private lateinit var game: Game

    private val _board = MutableLiveData<Game>()
    val board: LiveData<Game>
        get() = _board

    private val _currentPlayer = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player>
        get() = _currentPlayer

    private val _winner = MutableLiveData<Player?>()
    val winner: LiveData<Player?>
        get() = _winner

    init {
        startNewGame()
    }

    fun startNewGame() {
        game = Game()
        postValues()
    }

    fun play(row: Int, col: Int) {
        game.play(row, col)
        postValues()
    }

    private fun postValues() {
        _board.value = game

        if (!game.isFinished) {
            _currentPlayer.value = game.currentPlayer
        } else {
            _winner.value = game.winner
        }
    }
}