package com.lupi.magicapp.api.repository

import javax.inject.Inject

class MainRepository @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun getAllCards(banlist : String, sort : String) = apiRepository.getAllCards(banlist, sort)
    suspend fun getCard(id : Int) = apiRepository.getCard(id)
}