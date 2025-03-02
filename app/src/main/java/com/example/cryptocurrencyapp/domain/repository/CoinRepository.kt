package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    fun getCoins():List<CoinDto>
    fun getCoinById(coinId:String): CoinDetailsDto
}