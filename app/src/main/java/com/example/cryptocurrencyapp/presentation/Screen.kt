package com.example.cryptocurrencyapp.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin-list-screen")
    object CoinDetailScreen : Screen("coin-detail-screen")
}