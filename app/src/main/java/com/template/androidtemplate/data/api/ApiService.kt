package com.template.androidtemplate.data.api

import com.template.androidtemplate.data.model.GameOfThrones
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(ApiEndPoints.GAME_OF_THRONES_URL)
    suspend fun getGameOfThronesData(): Response<List<GameOfThrones>>

}