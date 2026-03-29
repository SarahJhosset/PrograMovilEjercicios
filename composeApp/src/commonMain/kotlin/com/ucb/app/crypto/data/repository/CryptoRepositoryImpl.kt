package com.ucb.app.crypto.data.repository

import com.ucb.app.crypto.data.datasource.CryptoRemoteDatasource
import com.ucb.app.crypto.data.mapper.toModel
import com.ucb.app.crypto.domain.model.CryptoModel
import com.ucb.app.crypto.domain.repository.CryptoRepository

class CryptoRepositoryImpl(
    val remote: CryptoRemoteDatasource
) : CryptoRepository {

    override suspend fun getCryptos(): List<CryptoModel> {
        return remote.getCryptos().map { it.toModel() }
    }
}