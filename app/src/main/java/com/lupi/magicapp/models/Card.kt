package com.lupi.magicapp.models

import com.google.gson.annotations.SerializedName

data class Card (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("type") val type : String,
	@SerializedName("frameType") val frameType : String,
	@SerializedName("desc") val desc : String,
	@SerializedName("atk") val atk : Int,
	@SerializedName("def") val def : Int,
	@SerializedName("level") val level : Int,
	@SerializedName("race") val race : String,
	@SerializedName("attribute") val attribute : String,
	@SerializedName("archetype") val archetype : String,
	@SerializedName("card_sets") val cardSets : List<CardSets>,
	@SerializedName("card_images") val cardImages : List<CardImages>,
	@SerializedName("card_prices") val cardPrices : List<CardPrices>
)