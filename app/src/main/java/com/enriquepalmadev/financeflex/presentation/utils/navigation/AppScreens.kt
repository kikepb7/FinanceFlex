package com.enriquepalmadev.financeflex.presentation.utils.navigation

sealed class AppScreens(val route: String) {
    data object LoginScreen: AppScreens("login_screen")
    data object SignupScreen: AppScreens("signup_screen")
    data object WorkingScreen: AppScreens("working_Screen")
    data object ProfileScreen: AppScreens("profile_screen")
    data object HomeScreen: AppScreens("home_screen")
    data object CoinListScreen: AppScreens("coin_list_screen")
    data object CoinDetailScreen: AppScreens("coin_detail_screen")
    data object StockListScreen: AppScreens("sotck_list_screen")
    data object StockDetailScreen: AppScreens("stock_detail_screen/{symbol}") {
        fun createRoute(symbol: String) = "stock_detail_screen/$symbol"
    }
}