package com.mind.market.cryptocurrency.domain.repository

import com.mind.market.cryptocurrency.data.remote.dto.CoinDetailDto
import com.mind.market.cryptocurrency.data.remote.dto.CoinDto

interface ICoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}