package com.example.assignmentanimeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentanimeapp.adapter.AnimeAdapter
import com.example.assignmentanimeapp.databinding.ActivityMainBinding
import com.example.assignmentanimeapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AnimeAdapter { anime ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("anime_id", anime.mal_id)
            startActivity(intent)
        }

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getTopAnime()
                adapter.submitList(response.data)
            } catch (e: Exception) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Error: ${e.message}"
            }
        }
    }
}
