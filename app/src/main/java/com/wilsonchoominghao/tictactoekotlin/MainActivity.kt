package com.wilsonchoominghao.tictactoekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class ACTIVEPLAYER {
        FIRST,
        SECOND
    }

    enum class WINNER {
        PLAYER_ONE,
        PLAYER_TWO,
        DRAW
    }

    var activePlayer: ACTIVEPLAYER? = null
    var winner: WINNER? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Called when clicking image button (i.e the tic tac toe spaces)
    fun clickImageButton(view: View) {
        val selectedImageButton : ImageButton = view as ImageButton

        var number : Int = 0

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

    // Login of clicking image button
    private fun performAction(number:Int, imageButton: ImageButton) {
        if(activePlayer == ACTIVEPLAYER.FIRST) {
            imageButton.setImageResource(R.drawable.ic_cross_black_24dp)
            imageButton.isEnabled = false
            activePlayer = ACTIVEPLAYER.SECOND
            textView_CurrentPlayer.text = "CIRCLE"
        }
        else {
            imageButton.setImageResource(R.drawable.ic_circle_black_24dp)
            imageButton.isEnabled = false
            activePlayer = ACTIVEPLAYER.FIRST
            textView_CurrentPlayer.text = "CROSS"
        }

    }
}
