package com.example.assignmentanimeapp.network


import com.example.assignmentanimeapp.model.AnimeDetailResponse
import com.example.assignmentanimeapp.model.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {
    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailResponse
}