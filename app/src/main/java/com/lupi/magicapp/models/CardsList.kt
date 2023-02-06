package com.lupi.magicapp.models

import com.google.gson.annotations.SerializedName

data class CardsList (
	@SerializedName("data") val data : List<Card>
)