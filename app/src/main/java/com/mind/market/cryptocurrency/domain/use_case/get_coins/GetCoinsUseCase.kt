package com.mind.market.cryptocurrency.domain.use_case.get_coins

import com.mind.market.cryptocurrency.common.Resource
import com.mind.market.cryptocurrency.data.remote.dto.toCoin
import com.mind.market.cryptocurrency.domain.model.Coin
import com.mind.market.cryptocurrency.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: ICoinRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())

            val coins = repository.getCoins().map { coinDto ->
                coinDto.toCoin()
            }

            emit(Resource.Success<List<Coin>>(coins))
        } catch (exception: HttpException) {
            emit(Resource.Error<List<Coin>>(exception.localizedMessage ?: "An unexpected http error occured."))
        } catch (exception: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}