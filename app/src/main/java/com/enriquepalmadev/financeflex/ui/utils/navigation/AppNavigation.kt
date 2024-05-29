package com.enriquepalmadev.financeflex.ui.utils.navigation

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enriquepalmadev.financeflex.ui.coin_feature.view.CoinDetailScreen
import com.enriquepalmadev.financeflex.ui.coin_feature.view.CoinListScreen
import com.enriquepalmadev.financeflex.ui.home_feature.view.HomeScreen
import com.enriquepalmadev.financeflex.ui.coin_feature.viewmodel.CoinDetailViewModel
import com.enriquepalmadev.financeflex.ui.coin_feature.viewmodel.CoinListViewModel
import com.enriquepalmadev.financeflex.ui.login_feature.auth.GoogleAuthUiUser
import com.enriquepalmadev.financeflex.ui.login_feature.view.LoginScreen
import com.enriquepalmadev.financeflex.ui.login_feature.viewmodel.LoginViewModel
import com.enriquepalmadev.financeflex.ui.profile_feature.view.ProfileScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val context =
        LocalContext.current  // En los composables, no se puede utilizar directamente 'applicationContext'
    val googleAuthUiUser by lazy {
        GoogleAuthUiUser(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            val viewModel = viewModel<LoginViewModel>()
            val state by viewModel.state.collectAsState()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        coroutineScope.launch {
                            val signInResult = googleAuthUiUser.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    navController.navigate(AppScreens.HomeScreen.route) {
                        popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                    }
                    viewModel.resetState()
                }
            }

            LoginScreen(
                state = state,
                navController = navController,
                onEmailSignInClick = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password) {
                        navController.navigate(AppScreens.HomeScreen.route) {
                            popUpTo(AppScreens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                onGoogleSignInClick = {
                    coroutineScope.launch {
                        val signInIntentSender = googleAuthUiUser.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(
                userData = googleAuthUiUser.getSignedInUser(),
                onSignOut = {
                    coroutineScope.launch {
                        googleAuthUiUser.signOut()
                    }

                    navController.navigate(AppScreens.LoginScreen.route)
                },
                onCardClick = {}
            )
        }
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
                )
            }
        }
    }
}