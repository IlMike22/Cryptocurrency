package com.mind.market.cryptocurrency.domain.use_case.get_coin

import com.mind.market.cryptocurrency.common.Resource
import com.mind.market.cryptocurrency.data.remote.dto.toCoinDetail
import com.mind.market.cryptocurrency.domain.model.Coin
import com.mind.market.cryptocurrency.domain.model.CoinDetail
import com.mind.market.cryptocurrency.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coinDetail))
        } catch (exception: HttpException) {
            emit(Resource.Error<CoinDetail>(exception.localizedMessage ?: "An unexpected error occured."))
        } catch (exception: IOException) {
            emit(Resource.Error<CoinDetail>("Cannot connect server. Please check your internet connection"))
        }
    }
}
