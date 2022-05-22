package com.connectshield.tictactoetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.children

/**
 * @author Asatryan on 21.05.2022
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val viewsList = mutableMapOf<CellID, ImageButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as TicTacToeApp).mainViewModel

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        gridLayout.children.forEachIndexed { index, view ->
            viewsList[CellID.values()[index]] = view as ImageButton
        }

        viewsList.forEach { (cellId, imageButton) ->
            imageButton.setOnClickListener { viewModel.tap(cellId) }
        }

        val textView = findViewById<TextView>(R.id.resultTextView)
        viewModel.observe(this) {
            textView.text = it
        }

        viewModel.observeResult(this) {
            it.show(viewsList)
        }

        val newGameButton = findViewById<View>(R.id.newGameButton)
        newGameButton.setOnClickListener {
            viewModel.newGame()
        }

        viewModel.init()
    }
}