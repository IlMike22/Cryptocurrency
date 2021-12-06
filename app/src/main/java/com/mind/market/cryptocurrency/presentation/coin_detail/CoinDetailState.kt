package com.mind.market.cryptocurrency.presentation.coin_detail

import com.mind.market.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val coinDetail: CoinDetail? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
