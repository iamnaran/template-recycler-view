package com.template.androidtemplate.data.api

import com.template.androidtemplate.data.model.GameOfThrones
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getGameOfThronesData(): Response<List<GameOfThrones>> =
        apiService.getGameOfThronesData()

}