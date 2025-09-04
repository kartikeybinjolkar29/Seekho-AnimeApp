package com.example.assignmentanimeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentanimeapp.databinding.ItemAnimeBinding
import com.example.assignmentanimeapp.model.AnimeItem

class AnimeAdapter(
    private val onClick: (AnimeItem) -> Unit
) : ListAdapter<AnimeItem, AnimeAdapter.AnimeViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<AnimeItem>() {
        override fun areItemsTheSame(old: AnimeItem, new: AnimeItem) = old.mal_id == new.mal_id
        override fun areContentsTheSame(old: AnimeItem, new: AnimeItem) = old == new
    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: AnimeItem) {
            binding.tvTitle.text = anime.title
            binding.tvEpisodes.text = "Episodes: ${anime.episodes ?: "?"}"
            binding.tvRating.text = "Score: ${anime.score ?: "-"}"
            Glide.with(binding.imgPoster.context).load(anime.images.jpg.image_url).into(binding.imgPoster)

            binding.root.setOnClickListener { onClick(anime) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
