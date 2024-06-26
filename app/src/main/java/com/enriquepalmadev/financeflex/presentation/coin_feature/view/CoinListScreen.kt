package com.enriquepalmadev.financeflex.presentation.coin_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.rounded.CurrencyExchange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenItemModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenLoading
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenState
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.ScreenError
import com.enriquepalmadev.financeflex.presentation.theme.Blue50
import com.enriquepalmadev.financeflex.presentation.theme.Green700
import com.enriquepalmadev.financeflex.presentation.theme.Green800
import com.enriquepalmadev.financeflex.presentation.theme.TransparentBlueEnd
import com.enriquepalmadev.financeflex.presentation.utils.navigation.AppScreens

@Composable
fun CoinListScreen(
    state: CoinListScreenState,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue50)
    ) {
        when {
            state.loadingScreenData.loader -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            state.errorScreenData != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorScreen(
                        screenError = ScreenError(
                            image = state.errorScreenData.image,
                            errorMsg = state.errorScreenData.errorMsg
                        )
                    )
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 12.dp),
                            text = stringResource(R.string.cryptos_header_label),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.breeserif))
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        state.coinScreenData?.coinListModel?.let {
                            items(it.coinsModel) { coin ->
                                CoinListItem(
                                    coinItemModel = coin,
                                    navController = navController,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    onCryptoClick = { navController.navigate("${AppScreens.CoinDetailScreen.route}/${coin.coin.id}") }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoinListItem(
    navController: NavController,
    coinItemModel: CoinListScreenItemModel,
    onCryptoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp, top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(TransparentBlueEnd),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.CurrencyExchange,
                    tint = Color.Blue,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(16.dp))

                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .width(1.dp)
                        .height(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${coinItemModel.coin.rank}. ${coinItemModel.coin.name} (${coinItemModel.coin.symbol})",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily(Font(R.font.breeserif)),
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (coinItemModel.coin.isActive) "ACTIVE" else "INACTIVE",
                        color = if (coinItemModel.coin.isActive) Green800 else colorResource(id = R.color.shiny_red),
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.breeserif)),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { onCryptoClick(coinItemModel.coin.symbol) },
                colors = ButtonDefaults.buttonColors(Green700),
                shape = RoundedCornerShape(30),
                modifier = Modifier
                    .defaultMinSize(minWidth = 48.dp)
                    .semantics {
                        contentDescription = R.string.crypto_detail_accessibility.toString()
                    }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListScreenPreview() {
    CoinListScreen(
        state = CoinListScreenState(
            loadingScreenData = CoinListScreenLoading(loader = false),
            errorScreenData = null,
            coinScreenData = CoinListScreenModel(
                coinListModel = CoinListModel(
                    coinsModel = listOf(
                        CoinListScreenItemModel(
                            coin = CoinModel("1", true, "Bitcoin", 1, "BTC")
                        ),
                        CoinListScreenItemModel(
                            coin = CoinModel("1", true, "Bitcoin", 1, "BTC")
                        ),

                        )
                )
            )
        ),
        navController = rememberNavController()
    )
}