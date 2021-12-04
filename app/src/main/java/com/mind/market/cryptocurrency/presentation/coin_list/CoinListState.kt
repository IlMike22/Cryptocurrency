package com.mind.market.cryptocurrency.presentation.coin_list

import com.mind.market.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins:List<Coin> = emptyList(),
    val error:String = ""

)
