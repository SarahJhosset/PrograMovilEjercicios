package com.ucb.app.crypto.data.datasource

import com.ucb.app.crypto.data.dto.CryptoDto

interface CryptoRemoteDatasource {
    suspend fun getCryptos(): List<CryptoDto>
}