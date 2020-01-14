package com.cognizant.tictactoe

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        fabRestart.setOnClickListener {
            viewModel.startNewGame()
        }

        tlGameBoard.children.forEachIndexed { row, tableRow ->
            (tableRow as TableRow).children.forEachIndexed { col, button ->
                button.setOnClickListener {
                    play(row, col)
                }
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.board.observe(this, Observer { updateBoardFields(it) })
        viewModel.currentPlayer.observe(this, Observer {
            tvBroadcast.text = if (it == Player.X) getString(R.string.x_turn) else getString(R.string.o_turn)
        })
        viewModel.winner.observe(this, Observer {
            tvBroadcast.text = when(it) {
                Player.X -> getString(R.string.x_won)
                Player.O -> getString(R.string.o_won)
                else -> getString(R.string.draw)
            }
        })
    }

    private fun updateBoardFields(game: Game) {
        tlGameBoard.children.forEachIndexed { row, tableRow ->
            (tableRow as TableRow).children.forEachIndexed { col, button ->
                button as TextView
                if (game.board[row][col] == null) {
                    button.text = ""
                    button.isEnabled = !game.isFinished
                } else {
                    button.text = game.board[row][col].toString()
                    button.isEnabled = false
                }
            }
        }
    }

    private fun play(row: Int, col: Int) {
        try {
            viewModel.play(row, col)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
