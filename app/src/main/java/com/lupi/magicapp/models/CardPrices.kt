package com.lupi.magicapp.models

import com.google.gson.annotations.SerializedName

data class CardPrices (
	@SerializedName("cardmarket_price") val cardmarket_price : Double,
	@SerializedName("tcgplayer_price") val tcgplayer_price : Double,
	@SerializedName("ebay_price") val ebayPrice : Double,
	@SerializedName("amazon_price") val amazonPrice : Double,
	@SerializedName("coolstuffinc_price") val coolstuffincPrice : Double
)