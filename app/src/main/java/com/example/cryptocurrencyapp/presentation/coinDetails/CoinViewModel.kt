package com.example.cryptocurrencyapp.presentation.coinDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.useCase.getCoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    val getCoinUseCase: GetCoinUseCase,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coin = mutableStateOf(CoinDetailState())
    val coin: State<CoinDetailState> = _coin

    init {
        saveStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }


    private fun getCoin(coinId: String) {
        viewModelScope.launch {

            getCoinUseCase(coinId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _coin.value = CoinDetailState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _coin.value = CoinDetailState(coin = it.data)
                    }

                    is Resource.Error -> {
                        _coin.value =
                            CoinDetailState(error = it.message ?: "An unexpected error occurred")
                    }

                }

            }
        }
    }


}