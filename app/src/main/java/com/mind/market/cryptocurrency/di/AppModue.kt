package com.mind.market.cryptocurrency.di

import Constants
import com.mind.market.cryptocurrency.data.remote.ICoinPaprikaApi
import com.mind.market.cryptocurrency.data.repository.CoinRepository
import com.mind.market.cryptocurrency.domain.repository.ICoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModue {
    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): ICoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: ICoinPaprikaApi): ICoinRepository {
        return CoinRepository(api)
    }
}