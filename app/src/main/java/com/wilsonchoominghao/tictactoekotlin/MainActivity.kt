package com.wilsonchoominghao.tictactoekotlin

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*************************************************************
     *
     * Variables
     *
     *************************************************************/

    enum class ACTIVEPLAYER {
        FIRST,      // CIRCLE O
        SECOND      // CROSS X
    }

    enum class WINNER {
        PLAYER_ONE, // CIRCLE O
        PLAYER_TWO, // CROSS X
        DRAW
    }

    var activePlayer: ACTIVEPLAYER? = null
    var winner: WINNER? = null

    var playerOneSelections: ArrayList<Int> = ArrayList()       // To track selections for winning logic
    var playerTwoSelections: ArrayList<Int> = ArrayList()       // To track selections for winning logic
    var disabledImages: ArrayList<ImageButton?> = ArrayList()   // To track all selections (for both players)
    var allImages: ArrayList<ImageButton?> = ArrayList()        // To change color of buttons after win or draw

    /*************************************************************
     *
     * Activity
     *
     *************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Let the CIRCLE player start
        activePlayer = ACTIVEPLAYER.FIRST
        addAllImages()
    }

    // Runs after clickImageButton, performs logic of clicking image button
    private fun performAction(number: Int, imageButton: ImageButton) {
        if (activePlayer == ACTIVEPLAYER.FIRST) {
            imageButton.setImageResource(R.drawable.ic_circle_black_24dp)
            imageButton.isEnabled = false
            playerOneSelections.add(number)
            disabledImages.add(imageButton)
            activePlayer = ACTIVEPLAYER.SECOND
            textView_CurrentPlayer.text = "CROSS (X)"
            winnerCheck(playerOneSelections, WINNER.PLAYER_ONE)
        } else if (activePlayer == ACTIVEPLAYER.SECOND) {
            imageButton.setImageResource(R.drawable.ic_cross_black_24dp)
            imageButton.isEnabled = false
            playerTwoSelections.add(number)
            disabledImages.add(imageButton)
            activePlayer = ACTIVEPLAYER.FIRST
            textView_CurrentPlayer.text = "CIRCLE (O)"
            winnerCheck(playerTwoSelections, WINNER.PLAYER_TWO)
        }
    }

    // Winning logic
    private fun winnerCheck(selections: ArrayList<Int>, win: WINNER) {
        // Checking values using hardcoded logic (top left, middle and top right)
        // Tic Tac Toe board is using numpad / FGC style numbering e.g. hadouken is 214P or 216P
        if (selections.contains(7)) {
            if (selections.contains(8)) {
                if (selections.contains(9)) {
                    winner = win                // top row win
                    allImages.get(7)?.setColorFilter(Color.GREEN)
                    allImages.get(8)?.setColorFilter(Color.GREEN)
                    allImages.get(9)?.setColorFilter(Color.GREEN)
                }
            }
            if (selections.contains(4)) {
                if (selections.contains(1)) {
                    winner = win                // left column win
                    allImages.get(7)?.setColorFilter(Color.GREEN)
                    allImages.get(4)?.setColorFilter(Color.GREEN)
                    allImages.get(1)?.setColorFilter(Color.GREEN)
                }
            }
            if (selections.contains(5)) {
                if (selections.contains(3)) {
                    winner = win                // diagonal top left to bottom right win
                    allImages.get(7)?.setColorFilter(Color.GREEN)
                    allImages.get(5)?.setColorFilter(Color.GREEN)
                    allImages.get(3)?.setColorFilter(Color.GREEN)
                }
            }
        }
        if (selections.contains(5)) {
            if (selections.contains(4)) {
                if (selections.contains(6)) {
                    winner = win                // middle row win
                    allImages.get(5)?.setColorFilter(Color.GREEN)
                    allImages.get(4)?.setColorFilter(Color.GREEN)
                    allImages.get(6)?.setColorFilter(Color.GREEN)
                }
            }
            if (selections.contains(8)) {
                if (selections.contains(2)) {
                    winner = win                // middle column win
                    allImages.get(5)?.setColorFilter(Color.GREEN)
                    allImages.get(8)?.setColorFilter(Color.GREEN)
                    allImages.get(2)?.setColorFilter(Color.GREEN)
                }
            }
            if (selections.contains(9)) {
                if (selections.contains(1)) {
                    winner = win                // diagonal top right to bottom left win
                    allImages.get(5)?.setColorFilter(Color.GREEN)
                    allImages.get(9)?.setColorFilter(Color.GREEN)
                    allImages.get(1)?.setColorFilter(Color.GREEN)
                }
            }
        }
        if (selections.contains(3)) {
            if (selections.contains(2)) {
                if (selections.contains(1)) {
                    winner = win                // bottom row win
                    allImages.get(3)?.setColorFilter(Color.GREEN)
                    allImages.get(2)?.setColorFilter(Color.GREEN)
                    allImages.get(1)?.setColorFilter(Color.GREEN)
                }
            }
            if (selections.contains(6)) {
                if (selections.contains(9)) {
                    winner = win                // right column win
                    allImages.get(3)?.setColorFilter(Color.GREEN)
                    allImages.get(6)?.setColorFilter(Color.GREEN)
                    allImages.get(9)?.setColorFilter(Color.GREEN)
                }
            }
        }

        // Check if anyone won or draw game
        if (winner == WINNER.PLAYER_ONE) {
            createWinnerAlert("Player One Wins!", "Congrats Circle O!", AlertDialog.BUTTON_POSITIVE, "OK", false)
        } else if (winner == WINNER.PLAYER_TWO) {
            createWinnerAlert("Player Two Wins!", "Congrats Cross X!", AlertDialog.BUTTON_POSITIVE, "OK", false)
        } else if (disabledImages.size == 9) {
            createWinnerAlert("Draw Game!", "Nobody wins but nobody loses!", AlertDialog.BUTTON_POSITIVE, "OK", false)
        }
    }

    // Display winner
    private fun createWinnerAlert(title: String, message: String, buttonType: Int, buttonText: String, cancelable: Boolean) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(cancelable)

        alertDialog.setButton(buttonType, buttonText, { dialog: DialogInterface?, which: Int ->
            null
        })
        textView_CurrentPlayerTitle.text = "GAME OVER: "
        textView_CurrentPlayer.text = title
        alertDialog.show()
    }

    /*************************************************************
     *
     * Helper Functions
     *
     *************************************************************/

    private fun addAllImages() {
        // Add null to 0 for easy processing
        allImages.add(null)
        allImages.add(imageButton_1)
        allImages.add(imageButton_2)
        allImages.add(imageButton_3)
        allImages.add(imageButton_4)
        allImages.add(imageButton_5)
        allImages.add(imageButton_6)
        allImages.add(imageButton_7)
        allImages.add(imageButton_8)
        allImages.add(imageButton_9)
    }

    /*************************************************************
     *
     * OnClick Functions
     *
     *************************************************************/

    // Called when clicking image button (i.e the tic tac toe spaces)
    fun clickImageButton(view: View) {
        // Only allow logic processing if nobody has won and not draw
        if (winner == null) {
            val selectedImageButton: ImageButton = view as ImageButton

            var number: Int = 0

            when (selectedImageButton.id) {
                R.id.imageButton_1 -> number = 1
                R.id.imageButton_2 -> number = 2
                R.id.imageButton_3 -> number = 3
                R.id.imageButton_4 -> number = 4
                R.id.imageButton_5 -> number = 5
                R.id.imageButton_6 -> number = 6
                R.id.imageButton_7 -> number = 7
                R.id.imageButton_8 -> number = 8
                R.id.imageButton_9 -> number = 9
            }

            performAction(number, selectedImageButton)
        }
    }

    // Reset game state to start
    fun clickResetGame(view: View) {
        winner = null
        activePlayer = ACTIVEPLAYER.FIRST
        textView_CurrentPlayerTitle.text = "Current Player: "
        textView_CurrentPlayer.text = "CIRCLE (O)"

        playerOneSelections.clear()
        playerTwoSelections.clear()
        for (i in disabledImages.indices) {
            disabledImages[i]?.setImageResource(0)
            disabledImages[i]?.isEnabled = true
            disabledImages[i]?.setColorFilter(0)
        }
        disabledImages.clear()

        Toast.makeText(this, "Game Reset!", Toast.LENGTH_SHORT).show()
    }
}
