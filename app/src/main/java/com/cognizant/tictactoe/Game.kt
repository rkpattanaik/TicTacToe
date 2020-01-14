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

    /**
     * Play turn by turn
     */
    fun play(row: Int, col: Int) {
        if (row >= boardSize || col >= boardSize) throw InvalidFieldException()
        if (board[row][col] != null) throw FieldOccupiedException()
        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }
}