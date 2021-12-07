package com.mind.market.cryptocurrency.presentation.coin_detail

import Constants.PARAM_COIN_ID
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mind.market.cryptocurrency.common.Resource
import com.mind.market.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId:String) {
        viewModelScope.launch {
            coinDetailUseCase(coinId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = CoinDetailState(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(
                            error = result.message ?: "An unexpected error occurred."
                        )
                    }
                    is Resource.Success -> {
                        _state.value = CoinDetailState(
                            coinDetail = result.data
                        )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}