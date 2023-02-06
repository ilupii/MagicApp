package com.lupi.magicapp.api.service

import com.lupi.magicapp.models.CardsList
import com.lupi.magicapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.BASE_CARD)
    suspend fun getAllCards(@Query("banlist") banlist : String, @Query("sort") sort : String) : Response<CardsList>

    @GET(Constants.BASE_CARD)
    suspend fun getCard(@Query("id") id : Int) : Response<CardsList>
}