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
}