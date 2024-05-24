package com.enriquepalmadev.financeflex.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenError
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenItemModel
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenState
import com.enriquepalmadev.financeflex.ui.navigation.AppScreens

@Composable
fun CoinListScreen(
    state: CoinListScreenState,
    navController: NavController,
) {

    if (state.loadingScreenData.loader) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }


    state.errorScreenData?.let {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ErrorScreen(
                coinListScreenError = CoinListScreenError(
                    image = it.image,
                    errorMsg = it.errorMsg
                )
            )
        }
    }

    LazyColumn {
        state.coinScreenData?.coinListModel?.let {
            items(it.coinsModel) { coin ->
                CoinListItem(
                    coinItemModel = coin,
                    navController = navController,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${AppScreens.CoinDetailScreen.route}/${coin.coin.id}")
                        }
                )
            }
        }
    }
}

@Composable
fun CoinListItem(
    navController: NavController,
    coinItemModel: CoinListScreenItemModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${coinItemModel.coin.rank}. ${coinItemModel.coin.name} (${coinItemModel.coin.symbol})",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (coinItemModel.coin.isActive) "active" else "inactive",
                color = if (coinItemModel.coin.isActive) Color.Green else Color.Red,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

/*@Composable
@Preview(showBackground = true)
fun CoinListScreenPreview() {
    CoinListScreen(navController = rememberNavController(),
        coinListModelScreen = CoinListModelScreen(
            coinList = listOf(
                CoinItemModel(
                    id = 1,
                    name = "Bitcoin",
                    symbol = "B",
                    rank = 1,
                    isActive = true
                ),
                CoinItemModel(
                    id = 2,
                    name = "Ethereum",
                    symbol = "E",
                    rank = 2,
                    isActive = true
                ),
                CoinItemModel(
                    id = 3,
                    name = "Solana",
                    symbol = "Sol",
                    rank = 3,
                    isActive = true
                )
            )
        )
    )
}*/
