package com.enriquepalmadev.financeflex.ui.navigation

sealed class AppScreens(val route: String) {
    data object SignInScreen: AppScreens("sign_in_screen")
    data object ProfileScreen: AppScreens("profile_screen")
    data object HomeScreen: AppScreens("home_screen")
    data object CoinListScreen: AppScreens("coin_list_screen")
    data object CoinDetailScreen: AppScreens("coin_detail_screen")
}