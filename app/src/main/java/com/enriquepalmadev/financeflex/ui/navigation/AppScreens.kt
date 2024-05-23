package com.enriquepalmadev.financeflex.ui.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen: AppScreens("home_screen")
    data object CoinListScreen: AppScreens("coin_list_screen")
}