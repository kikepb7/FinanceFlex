package com.enriquepalmadev.financeflex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enriquepalmadev.financeflex.ui.view.CoinDetailScreen
import com.enriquepalmadev.financeflex.ui.view.CoinListScreen
import com.enriquepalmadev.financeflex.ui.view.HomeScreen
import com.enriquepalmadev.financeflex.ui.viewmodel.CoinDetailViewModel
import com.enriquepalmadev.financeflex.ui.viewmodel.CoinListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = AppScreens.CoinListScreen.route) {
            val viewModel: CoinListViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state?.let { screenState ->
                CoinListScreen(
                    state = screenState,
                    navController = navController
                )
            }
        }
        composable(
            route = "${AppScreens.CoinDetailScreen.route}/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel: CoinDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            val coinId = backStackEntry.arguments?.getString("coinId") ?: ""

            LaunchedEffect(coinId) {
                viewModel.getCoinDetail(coinId = coinId)
            }

            state?.let { screenState ->
                CoinDetailScreen(
                    state = screenState,
                    navController = navController
                )
            }
        }
    }
}