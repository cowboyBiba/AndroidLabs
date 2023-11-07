package com.cdr.Lab811.screens.statistic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdr.Lab811.Dependencies
import com.cdr.Lab811.R
import com.cdr.Lab811.databinding.ActivityAllStatisticBinding
import com.cdr.Lab811.ActivityCamera

class AllStatisticActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllStatisticBinding
    private val viewModel by lazy { AllStatisticViewModel(Dependencies.statisticRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Dependencies.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityAllStatisticBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel.allStatic.observe(this) { allStatistic ->
            val adapter = StatisticAdapter(viewModel.statisticItemListener)
            adapter.data = allStatistic.reversed()

            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, ActivityCamera::class.java)
            startActivity(intent)
        }
    }
}