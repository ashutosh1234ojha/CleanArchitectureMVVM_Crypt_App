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

    private val _coinList = mutableStateOf(CoinDetailState())
    val coinList: State<CoinDetailState> = _coinList

    init {
        saveStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinList(coinId)
        }
    }


    private fun getCoinList(coinId: String) {
        viewModelScope.launch {

            getCoinUseCase(coinId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _coinList.value = CoinDetailState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _coinList.value = CoinDetailState(coins = it.data)
                    }

                    is Resource.Error -> {
                        _coinList.value =
                            CoinDetailState(error = it.message ?: "An unexpected error occurred")
                    }

                }

            }
        }
    }


}