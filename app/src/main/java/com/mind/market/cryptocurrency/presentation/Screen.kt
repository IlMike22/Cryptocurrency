package com.mind.market.cryptocurrency.presentation

import Constants.ROUTE_COIN_DETAIL
import Constants.ROUTE_COIN_LIST

sealed class Screen(val route: String) {
    object CoinListScreen : Screen(ROUTE_COIN_LIST)
    object CoinDetailScreen: Screen(ROUTE_COIN_DETAIL)
}
