package com.example.cryptocurrencyapp.presentation.coinDetails

import com.example.cryptocurrencyapp.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetails? = null,
    val error: String = ""
) {
}