package com.cognizant.tictactoe

/**
 * [Game] class contains the core logic of TicTacToe
 */
class Game {

    private val boardSize = 3
    val board = Array(boardSize) { Array<Player?>(boardSize) { null } }

    var currentPlayer = Player.X
        private set
    var isFinished = false
        private set
    var winner: Player? = null
        private set

}