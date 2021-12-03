package com.mind.market.cryptocurrency.data.repository

import com.mind.market.cryptocurrency.data.remote.ICoinPaprikaApi
import com.mind.market.cryptocurrency.data.remote.dto.CoinDetailDto
import com.mind.market.cryptocurrency.data.remote.dto.CoinDto
import com.mind.market.cryptocurrency.domain.repository.ICoinRepository
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: ICoinPaprikaApi
) : ICoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}