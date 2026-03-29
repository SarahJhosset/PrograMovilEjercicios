package com.ucb.app.crypto.domain.model

data class CryptoModel(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val price: Double,
    val marketCapRank: Int,
    val priceChange24h: Double,
    val high24h: Double,
    val low24h: Double
)