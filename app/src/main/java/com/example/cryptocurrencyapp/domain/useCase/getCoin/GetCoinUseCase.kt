package com.example.cryptocurrencyapp.domain.useCase.getCoin

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {

        try {
            emit(Resource.Loading())

            val coinDetails = coinRepository.getCoinById(coinId)

            emit(
                Resource.Success(
                    coinDetails.toCoinDetail()
                )
            )

        } catch (httpException: HttpException) {
            emit(Resource.Error(httpException.localizedMessage ?: "An unexpected error occurred"))
        } catch (ioException: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }
    }

}