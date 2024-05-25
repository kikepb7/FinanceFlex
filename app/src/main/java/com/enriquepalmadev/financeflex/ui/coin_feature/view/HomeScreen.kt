package com.enriquepalmadev.financeflex.ui.coin_feature.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enriquepalmadev.financeflex.ui.components.BottomNavigationBar

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
        ) {
//            WalletSection()
//            CardSection()
            Spacer(modifier = Modifier.height(4.dp))
            FinanceSection(navController = navController)
//            CurrenciesSection()
        }
    }
}