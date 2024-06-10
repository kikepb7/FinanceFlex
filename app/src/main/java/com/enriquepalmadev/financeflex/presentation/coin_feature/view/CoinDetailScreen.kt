package com.enriquepalmadev.financeflex.presentation.coin_feature.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.data.coin_feature.dto.LinksDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.TeamMemberDto
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinDetailScreenModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinDetailScreenState
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenLoading
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.ScreenError
import com.enriquepalmadev.financeflex.presentation.theme.Blue50
import com.enriquepalmadev.financeflex.presentation.theme.Green20
import com.enriquepalmadev.financeflex.presentation.utils.openUrl


val linkIconMap = mapOf(
    "facebook" to R.drawable.facebook_logo,
    "reddit" to R.drawable.reddit_logo,
    "website" to R.drawable.website_logo,
    "youtube" to R.drawable.youtube_logo
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinDetailScreenState,
    onLinkClicked: (String) -> Unit
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
                val coinDetail = state.coinDetailScreenData?.coinDetail

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 64.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "#${coinDetail?.rank} - ${coinDetail?.name} (${coinDetail?.symbol})",
                                fontSize = 30.sp,
                                fontFamily = FontFamily(Font(R.font.breeserif)),
                                modifier = Modifier.weight(8f)
                            )

                            coinDetail?.logo?.let { logo ->
                                Image(
                                    painter = rememberAsyncImagePainter(model = logo),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        coinDetail?.description?.let { it1 ->
                            Text(
                                text = it1,
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        val linkIcon = mapOf(
                            "facebook" to coinDetail?.links?.facebook,
                            "reddit" to coinDetail?.links?.reddit,
                            "youtube" to coinDetail?.links?.youtube
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            linkIcon.forEach { (type, links) ->
                                val icon = linkIconMap[type]
                                if (icon != null && links?.isNotEmpty() == true) {
                                    links.forEach { link ->
                                        val context = LocalContext.current
                                        val onClick: () -> Unit = {
                                            openUrl(context, link)
                                            onLinkClicked(link)
                                        }
                                        Box(
                                            modifier = Modifier
                                                .size(56.dp)
                                                .clip(CircleShape)
                                                .background(Color.Transparent)
                                                .clickable(onClick = onClick),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painter = painterResource(id = icon),
                                                contentDescription = "$type logo",
                                                modifier = Modifier
                                                    .size(56.dp)
                                                    .padding(8.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))


                        FlowRow(
                            maxItemsInEachRow = 2,
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            coinDetail?.tags?.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = stringResource(R.string.team_members),
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.breeserif))
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                    }

                    if (coinDetail != null) {
                        items(coinDetail.team) { teamMember ->
                            TeamListItem(
                                teamMember = teamMember,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoinTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.breeserif))
        )
    }
}

@Composable
fun TeamListItem(
    teamMember: TeamMemberDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = teamMember.name,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.breeserif))
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = teamMember.position,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.breeserif)),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinDetailScreenPreview() {
    CoinDetailScreen(
        state = CoinDetailScreenState(
            loadingScreenData = CoinListScreenLoading(loader = false),
            errorScreenData = null,
            coinDetailScreenData = CoinDetailScreenModel(
                coinDetail = CoinDetailModel(
                    rank = 1,
                    name = "Bitcoin",
                    symbol = "BTC",
                    isActive = true,
                    description = "Bitcoin is a decentralized digital currency.",
                    tags = listOf("Cryptocurrency", "Blockchain", "Decentralized"),
                    team = listOf(
                        TeamMemberDto(name = "Satoshi Nakamoto", position = "Founder", id = "1"),
                        TeamMemberDto(name = "Hal Finney", position = "Developer", id = "2")
                    ),
                    coinId = "1",
                    logo = "https://static.coinpaprika.com/coin/btc-bitcoin/logo.png",
                    links = LinksDto(
                        explorer = emptyList(),
                        facebook = listOf("https://facebook.com/bitcoin"),
                        reddit = listOf("https://reddit.com/r/bitcoin"),
                        sourceCode = emptyList(),
                        website = listOf("https://bitcoin.org"),
                        youtube = listOf("https://youtube.com/bitcoin")
                    )
                ),
            )
        ), {}
    )
}