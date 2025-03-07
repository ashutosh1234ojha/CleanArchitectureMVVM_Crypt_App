package com.example.cryptocurrencyapp.presentation.coinList.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencyapp.presentation.Screen
import com.example.cryptocurrencyapp.presentation.coinList.CoinListViewModel

@Composable
fun CoinListScreen(navController: NavController, viewModel: CoinListViewModel = hiltViewModel()) {


    val value = viewModel.coinList.value

    Box(modifier = Modifier.fillMaxSize().padding(vertical = 20.dp, horizontal = 10.dp)) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(value.coins) {
                CoinListItem(coin = it, onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}")
                })
            }

        }


        if (value.error.isNotBlank()) {
            Text(
                value.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)

            )
        }

        if (value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}