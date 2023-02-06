package com.lupi.magicapp.api.repository

import com.lupi.magicapp.api.service.ApiService
import com.lupi.magicapp.models.CardsList
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository{

    override suspend fun getAllCards(banlist: String, sort: String): Response<CardsList> {
        return apiService.getAllCards(banlist, sort)
    }

    override suspend fun getCard(id: Int): Response<CardsList> {
        return apiService.getCard(id)
    }

}