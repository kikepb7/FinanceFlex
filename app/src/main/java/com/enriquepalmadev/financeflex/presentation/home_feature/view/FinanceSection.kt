package com.enriquepalmadev.financeflex.presentation.home_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CandlestickChart
import androidx.compose.material.icons.rounded.CurrencyBitcoin
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.enriquepalmadev.financeflex.presentation.theme.Blue50
import com.enriquepalmadev.financeflex.presentation.utils.navigation.AppScreens
import com.enriquepalmadev.financeflex.presentation.theme.BlueStart
import com.enriquepalmadev.financeflex.presentation.theme.CustomBlue
import com.enriquepalmadev.financeflex.presentation.theme.CustomGreen
import com.enriquepalmadev.financeflex.presentation.theme.CustomWhite
import com.enriquepalmadev.financeflex.presentation.theme.Green200
import com.enriquepalmadev.financeflex.presentation.theme.GreenStart
import com.enriquepalmadev.financeflex.presentation.theme.OrangeStart
import com.enriquepalmadev.financeflex.presentation.theme.PurpleStart

data class Finance(
    val icon: ImageVector,
    val name: String,
    val background: Color
)

val financeList = listOf(
    Finance(
        icon = Icons.Rounded.CandlestickChart,
        name = "Stocks",
        background = OrangeStart
    ),

    Finance(
        icon = Icons.Rounded.CurrencyBitcoin,
        name = "Crypto",
        background = BlueStart
    ),

    Finance(
        icon = Icons.Rounded.Person,
        name = "Profile",
        background = PurpleStart
    ),

    Finance(
        icon = Icons.Rounded.MonetizationOn,
        name = "Transactions",
        background = GreenStart
    )
)

@Composable
fun FinanceSection(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(color = Blue50)
    ) {

        LazyRow {
            items(financeList.size) {
                FinanceItem(it, navController)
            }
        }
    }
}

@Composable
fun FinanceItem(
    index: Int,
    navController: NavController
) {
    val finance = financeList[index]
    var lastPaddingEnd = 0.dp
    if (index == financeList.size - 1) {
        lastPaddingEnd = 16.dp
    }

    Box(modifier = Modifier.padding(start = 16.dp, end = lastPaddingEnd)) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Green200)
                .size(90.dp)
                .clickable {
                    when (finance.name) {
                        "Stocks" -> navController.navigate(AppScreens.StockListScreen.route)
                        "Crypto" -> navController.navigate(AppScreens.CoinListScreen.route)
                        "Profile" -> navController.navigate(AppScreens.ProfileScreen.route)
                    }
                }
                .padding(13.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(finance.background)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = finance.icon,
                    contentDescription = finance.name,
                    tint = Color.White
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = finance.name,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}