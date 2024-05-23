package com.enriquepalmadev.financeflex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enriquepalmadev.financeflex.ui.view.CoinListScreen
import com.enriquepalmadev.financeflex.ui.view.HomeScreen
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
                    navController = navController)
            }
        }
        /*composable(route = AppScreens.CoinListScreen.route) {
            val state = remember { *//* provide initial CoinListScreenState *//* }
            CoinListScreen(state = state, navController = navController)
        }*/
    }
}