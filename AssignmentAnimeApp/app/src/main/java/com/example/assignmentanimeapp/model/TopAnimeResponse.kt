package com.example.assignmentanimeapp.model

data class TopAnimeResponse(
    val data: List<AnimeItem>
)

data class AnimeItem(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Images
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    val image_url: String
)
