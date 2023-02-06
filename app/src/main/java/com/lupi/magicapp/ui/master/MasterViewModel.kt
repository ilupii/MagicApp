package com.lupi.magicapp.ui.master

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lupi.magicapp.api.repository.MainRepository
import com.lupi.magicapp.models.Card
import com.lupi.magicapp.utils.ApiResponse
import com.lupi.magicapp.utils.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val mutableCardsList : MutableLiveData<ApiResponse<List<Card>>> = MutableLiveData()

    fun getAllCards() = viewModelScope.launch {

        mutableCardsList.postValue(ApiResponse.Loading())

        try {

            val call = mainRepository.getAllCards("tcg", "name")

            if (call.isSuccessful) {
                val body = call.body()
                mutableCardsList.postValue(ApiResponse.Success(body?.data))
            } else {
                mutableCardsList.postValue(ApiResponse.Error(ErrorType.GENERIC_ERROR))
            }

        }catch (unknownHostException : UnknownHostException){
            mutableCardsList.postValue(ApiResponse.Error(ErrorType.NO_NETWORK_CONNECTION_ERROR))
        }catch (e : Exception){
            mutableCardsList.postValue(ApiResponse.Error(ErrorType.GENERIC_ERROR))
        }


    }

    fun getMutableCardList() = mutableCardsList
}