package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(val api: CoinPaprikaApi) : CoinRepository {
    override fun getCoins(): List<CoinDto> {
        TODO("Not yet implemented")
    }

    override fun getCoinById(coinId: String): CoinDetailsDto {
        TODO("Not yet implemented")
    }
}