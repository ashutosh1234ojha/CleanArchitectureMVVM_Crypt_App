package com.example.cryptocurrencyapp.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.useCase.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _coinList = mutableStateOf(CoinListState())
    val coinList: State<CoinListState> = _coinList


    init {
        getCoinList()
    }

    private fun getCoinList() {
        viewModelScope.launch {

            getCoinsUseCase().collect {
                when (it) {
                    is Resource.Loading -> {
                        _coinList.value = CoinListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _coinList.value = CoinListState(coins = it.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _coinList.value =
                            CoinListState(error = it.message ?: "An unexpected error occurred")
                    }

                }

            }
        }
    }


}