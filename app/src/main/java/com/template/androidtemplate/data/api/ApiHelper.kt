package com.template.androidtemplate.data.api

import com.template.androidtemplate.data.model.GameOfThrones
import retrofit2.Response

interface ApiHelper {
    suspend fun getGameOfThronesData(): Response<List<GameOfThrones>>
}