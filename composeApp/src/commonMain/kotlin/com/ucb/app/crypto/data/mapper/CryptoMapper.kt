package com.ucb.app.crypto.data.mapper

import com.ucb.app.crypto.data.dto.CryptoDto
import com.ucb.app.crypto.domain.model.CryptoModel

fun CryptoDto.toModel() = CryptoModel(
    id = id,
    name = name,
    symbol = symbol,
    image  = image,
    price = price,
    marketCapRank = marketCapRank,
    priceChange24h = priceChange24h,
    high24h = high24h,
    low24h = low24h
)