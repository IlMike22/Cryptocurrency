package com.mind.market.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mind.market.cryptocurrency.common.Resource
import com.mind.market.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,

    ) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = CoinListState(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _state.value = CoinListState(
                            error = result.message ?: "Unexpected error"
                        )
                    }
                    is Resource.Success -> {
                        _state.value = CoinListState(
                            coins = result.data ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}