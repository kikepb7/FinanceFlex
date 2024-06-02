package com.enriquepalmadev.financeflex.ui.home_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enriquepalmadev.financeflex.ui.utils.components.BottomNavigationBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigation = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
//            WalletSection()
//            CardSection()
            Spacer(modifier = Modifier.height(4.dp))
            FinanceSection(navController = navController)
//            CurrenciesSection()
            PortfolioSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
