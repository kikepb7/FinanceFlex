package com.enriquepalmadev.financeflex.ui.coin_feature.view

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
import androidx.compose.material.icons.rounded.MonetizationOn
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
import com.enriquepalmadev.financeflex.ui.navigation.AppScreens
import com.enriquepalmadev.financeflex.ui.theme.BlueStart
import com.enriquepalmadev.financeflex.ui.theme.GreenStart
import com.enriquepalmadev.financeflex.ui.theme.OrangeStart
import com.enriquepalmadev.financeflex.ui.theme.PurpleStart

data class Finance(
    val icon: ImageVector,
    val name: String,
    val background: Color
)

val financeList = listOf(
    Finance(
        icon = Icons.Rounded.Star,
        name = "Stocks",
        background = OrangeStart
    ),

    Finance(
        icon = Icons.Rounded.Wallet,
        name = "Crypto",
        background = BlueStart
    ),

    Finance(
        icon = Icons.Rounded.Star,
        name = "Analytics",
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
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(90.dp)
                .clickable {
                    when (finance.name) {
//                        "Stocks" -> navController.navigate(AppScreens.CompanyListingsScreen.route)
                        "Crypto" -> navController.navigate(AppScreens.CoinListScreen.route)
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