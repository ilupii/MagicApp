package com.lupi.magicapp.ui.detail

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
class DetailViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val mutableCard : MutableLiveData<ApiResponse<Card>> = MutableLiveData()

    fun getCard(id : Int) = viewModelScope.launch {

        mutableCard.postValue(ApiResponse.Loading())

        try{

            val call = mainRepository.getCard(id)

            if(call.isSuccessful){
                mutableCard.postValue(ApiResponse.Success(call.body()?.data?.get(0)))
            }else{
                mutableCard.postValue(ApiResponse.Error(ErrorType.GENERIC_ERROR))
            }

        }catch (unknownHostException : UnknownHostException){
            mutableCard.postValue(ApiResponse.Error(ErrorType.NO_NETWORK_CONNECTION_ERROR))
        }catch (e : Exception){
            mutableCard.postValue(ApiResponse.Error(ErrorType.GENERIC_ERROR))
        }

    }

    fun getMutableCard() = mutableCard
}