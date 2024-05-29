package com.enriquepalmadev.financeflex.ui.utils.navigation

sealed class AppScreens(val route: String) {
    data object LoginScreen: AppScreens("login_screen")
    data object ProfileScreen: AppScreens("profile_screen")
    data object HomeScreen: AppScreens("home_screen")
    data object CoinListScreen: AppScreens("coin_list_screen")
    data object CoinDetailScreen: AppScreens("coin_detail_screen")
}