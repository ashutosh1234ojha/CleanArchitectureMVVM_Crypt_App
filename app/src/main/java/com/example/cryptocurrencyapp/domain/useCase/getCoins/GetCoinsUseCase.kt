package com.example.cryptocurrencyapp.domain.useCase.getCoins

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        try {
            emit(Resource.Loading())

            val coins = coinRepository.getCoins()

            emit(Resource.Success(coins.map { it.toCoin() }))

        } catch (httpException: HttpException) {
            emit(Resource.Error(httpException.localizedMessage ?: "An unexpected error occurred"))
        } catch (ioException: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }
    }

}