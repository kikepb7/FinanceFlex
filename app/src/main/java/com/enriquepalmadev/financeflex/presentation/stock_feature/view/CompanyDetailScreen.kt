package com.enriquepalmadev.financeflex.presentation.stock_feature.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.ScreenError
import com.enriquepalmadev.financeflex.presentation.coin_feature.view.ErrorScreen
import com.enriquepalmadev.financeflex.presentation.coin_feature.view.LoadingScreen
import com.enriquepalmadev.financeflex.presentation.stock_feature.viewmodel.CompanyInfoViewModel
import com.enriquepalmadev.financeflex.presentation.theme.Blue50
import com.enriquepalmadev.financeflex.presentation.theme.GreenBlue30
import com.enriquepalmadev.financeflex.presentation.theme.TransparentGreenEnd

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CompanyDetailScreen(
    symbol: String,
    screenError: ScreenError,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue50)
                .padding(8.dp),
        ) {
            state.company?.let { company ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.BarChart,
                        contentDescription = "Market icon"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = company.name.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                        .height(256.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(TransparentGreenEnd),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(16.dp)
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Black)) {
                                        append("Ticker: ")
                                    }
                                    withStyle(style = SpanStyle(color = GreenBlue30)) {
                                        append("$${company.symbol}")
                                    }
                                },
                                fontStyle = FontStyle.Italic,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Black)) {
                                        append("Sector: ")
                                    }
                                    withStyle(style = SpanStyle(color = GreenBlue30)) {
                                        append(company.industry)
                                    }
                                },
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Black)) {
                                        append("País: ")
                                    }
                                    withStyle(style = SpanStyle(color = GreenBlue30)) {
                                        append("$${company.country}")
                                    }
                                },
                                fontSize = 14.sp,
                                color = Color.Black,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = company.description,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.breeserif))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (state.stockInfos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Gráfico de la empresa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.breeserif))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StockChart(
                        infos = state.stockInfos,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(horizontal = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            ErrorScreen(
                screenError = ScreenError(
                    image = screenError.image,
                    errorMsg = screenError.errorMsg
                )
            )
        }
    }
}