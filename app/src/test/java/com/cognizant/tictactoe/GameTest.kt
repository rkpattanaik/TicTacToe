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
}