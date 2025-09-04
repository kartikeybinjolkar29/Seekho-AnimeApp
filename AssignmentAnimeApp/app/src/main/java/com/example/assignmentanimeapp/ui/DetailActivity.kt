package com.example.assignmentanimeapp.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.assignmentanimeapp.databinding.ActivityDetailBinding
import com.example.assignmentanimeapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeId = intent.getIntExtra("anime_id", -1)
        if (animeId != -1) fetchAnimeDetails(animeId)
    }

    private fun fetchAnimeDetails(id: Int) {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getAnimeDetails(id)
                val anime = response.data

                binding.tvTitle.text = anime.title
                binding.tvSynopsis.text = anime.synopsis ?: "No synopsis available"
                binding.tvEpisodes.text = "Episodes: ${anime.episodes ?: "?"}"
                binding.tvRating.text = "Score: ${anime.score ?: "-"}"
                binding.tvGenres.text = "Genres: ${anime.genres?.joinToString { it.name } ?: "N/A"}"

                val youtubeId = anime.trailer?.youtube_id
                if (!youtubeId.isNullOrEmpty()) {
                    binding.webViewTrailer.visibility = View.VISIBLE
                    binding.imgPoster.visibility = View.GONE
                    val embedHtml = """
                        <iframe width="100%" height="100%" 
                            src="https://www.youtube.com/embed/$youtubeId" 
                            frameborder="0" allowfullscreen>
                        </iframe>
                    """.trimIndent()
                    binding.webViewTrailer.settings.javaScriptEnabled = true
                    binding.webViewTrailer.webViewClient = WebViewClient()
                    binding.webViewTrailer.loadData(embedHtml, "text/html", "utf-8")
                } else {
                    binding.webViewTrailer.visibility = View.GONE
                    binding.imgPoster.visibility = View.VISIBLE
                    Glide.with(this@DetailActivity)
                        .load(anime.images.jpg.image_url)
                        .into(binding.imgPoster)
                }
            } catch (e: Exception) {
                binding.tvTitle.text = "Error: ${e.message}"
            }
        }
    }
}
