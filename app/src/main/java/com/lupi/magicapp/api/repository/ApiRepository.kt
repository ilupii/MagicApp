package com.lupi.magicapp.api.repository

import com.lupi.magicapp.models.CardsList
import retrofit2.Response

interface ApiRepository {
    suspend fun getAllCards(banlist : String, sort : String) : Response<CardsList>
    suspend fun getCard(id : Int) : Response<CardsList>
}