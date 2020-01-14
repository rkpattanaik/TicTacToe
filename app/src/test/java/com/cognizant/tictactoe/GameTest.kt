package com.cognizant.tictactoe

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameTest {

    private lateinit var game: Game

    @Before
    fun setup() {
        game = Game()
    }

    @Test
    fun `test initial state of game`() {
        assertEquals(false, game.isFinished)
        assertEquals(null, game.winner)
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test(expected = InvalidFieldException::class)
    fun `test field outside board throws exception`() {
        game.play(2, 4)
    }

    @Test(expected = FieldOccupiedException::class)
    fun `test using occupied field throws exception`() {
        game.play(1, 2)
        game.play(1, 2)
    }

    @Test
    fun `test players' turns switch after playing`() {
        assertEquals(Player.X, game.currentPlayer)
        game.play(1, 2)
        assertEquals(Player.O, game.currentPlayer)
    }

    @Test
    fun `test win by row`() {
        // XXX
        // OO.
        // ...
        game.play(0, 0) // Player X
        game.play(1, 0) // Player O
        game.play(0, 1) // Player X
        game.play(1, 1) // Player O
        game.play(0, 2) // Player X
        assertEquals(Player.X, game.winner)
        assertEquals(true, game.isFinished)
    }

    @Test
    fun `test win by column`() {
        // XO.
        // XO.
        // X..
        game.play(0, 0) // Player X
        game.play(0, 1) // Player O
        game.play(1, 0) // Player X
        game.play(1, 1) // Player O
        game.play(2, 0) // Player X
        assertEquals(Player.X, game.winner)
        assertEquals(true, game.isFinished)
    }

    @Test
    fun `test win diagonally from top-left to bottom-right`() {
        // O..
        // XO.
        // XXO
        game.play(1, 0) // Player X
        game.play(0, 0) // Player O
        game.play(2, 0) // Player X
        game.play(1, 1) // Player O
        game.play(2, 1) // Player X
        game.play(2, 2) // Player O
        assertEquals(Player.O, game.winner)
        assertEquals(true, game.isFinished)
    }

    @Test
    fun `test win diagonally from bottom-left to top-right`() {
        // OOX
        // .X.
        // X..
        game.play(2, 0) // Player X
        game.play(0, 1) // Player O
        game.play(1, 1) // Player X
        game.play(0, 0) // Player O
        game.play(0, 2) // Player X
        assertEquals(Player.X, game.winner)
        assertEquals(true, game.isFinished)
    }

    @Test
    fun `test draw`() {
        // XOO
        // OXX
        // XXO
        game.play(0, 0) // Player X
        game.play(1, 0) // Player O
        game.play(1, 1) // Player X
        game.play(2, 2) // Player O
        game.play(2, 0) // Player X
        game.play(0, 2) // Player O
        game.play(2, 1) // Player X
        game.play(0, 1) // Player O
        game.play(1, 2) // Player X
        assertEquals(null, game.winner)
        assertEquals(true, game.isFinished)
    }
}