package com.cdr.Lab811.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import com.cdr.Lab811.Dependencies
import com.cdr.Lab811.databinding.ActivityMainBinding
import com.cdr.Lab811.screens.main.MainViewModel.Companion.TITLE_DIFFICULTY
import com.cdr.Lab811.screens.main.MainViewModel.Companion.TITLE_RESULT
import com.cdr.Lab811.screens.main.MainViewModel.Companion.difficultyCases
import com.cdr.Lab811.screens.main.MainViewModel.Companion.resultCases
import com.cdr.Lab811.screens.statistic.AllStatisticActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { MainViewModel(Dependencies.statisticRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Dependencies.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.addStatisticButton.setOnClickListener { addStatisticButtonPressed() }
        binding.allStatisticButton.setOnClickListener { allStatisticButtonPressed() }

        setUpSpinners()
    }

    private fun addStatisticButtonPressed() {
        if (isEditTextsEmpty()) {
            viewModel.insertNewStatisticDataInDatabase(
                mistakes = binding.mistakesEditText.text.toString().toLong(),
                points = binding.pointsEditText.text.toString().toLong()
            )

            Toast.makeText(this, "Данные вставлены", Toast.LENGTH_SHORT)
                .show()
        } else Toast.makeText(this, "Не все поля были заполнены!", Toast.LENGTH_SHORT)
            .show()

    }

    private fun allStatisticButtonPressed() {
        val intent = Intent(this, AllStatisticActivity::class.java)
        startActivity(intent)
    }

    private fun setUpSpinners() {
        binding.difficultySpinner.adapter = SimpleAdapter(
            this,
            difficultyCases,
            android.R.layout.simple_list_item_1,
            arrayOf(TITLE_DIFFICULTY),
            intArrayOf(android.R.id.text1)
        )
        binding.difficultySpinner.onItemSelectedListener = viewModel.onDifficultyLevelSelectedListener

        binding.resultSpinner.adapter = SimpleAdapter(
            this,
            resultCases,
            android.R.layout.simple_list_item_1,
            arrayOf(TITLE_RESULT),
            intArrayOf(android.R.id.text1)
        )
        binding.resultSpinner.onItemSelectedListener = viewModel.onResultSelectedListener
    }

    private fun isEditTextsEmpty(): Boolean {
        val isMistakes = if (binding.mistakesEditText.text.toString().isNotBlank()) {
            binding.valueTextInputMistakes.error = null
            true
        } else {
            binding.valueTextInputMistakes.error = "Пустые поля"
            false
        }
        val isPoints = if (binding.pointsEditText.text.toString().isNotBlank()) {
            binding.valueTextInputPoints.error = null
            true
        } else {
            binding.valueTextInputPoints.error = "Пустые поля"
            false
        }

        return isMistakes && isPoints
    }
}