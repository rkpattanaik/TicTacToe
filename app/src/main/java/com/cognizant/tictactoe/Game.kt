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
        updateGame()
    }

    private fun updateGame() {
        // Check Winner
        for (index in board.indices) {
            // Check winner by row
            if ((0 until boardSize).map { board[index][it] }.distinct().size == 1) {
                board[index][0]?.let { winner = it }
            }

            // Check winner by column
            if ((0 until boardSize).map { board[it][index] }.distinct().size == 1) {
                board[0][index]?.let { winner = it }
            }

            // Check winner by diagonally from top-left to bottom-right
            if ((0 until boardSize).map { board[it][it] }.distinct().size == 1) {
                board[0][0]?.let { winner = it }
            }

            // Check winner by diagonally from bottom-left to top-right
            if ((0 until boardSize).map { board[it][boardSize - 1 - it] }.distinct().size == 1) {
                board[0][boardSize - 1]?.let { winner = it }
            }
        }

        // Check if game finished
        isFinished = winner != null || !board.flatten().contains(null)
    }
}