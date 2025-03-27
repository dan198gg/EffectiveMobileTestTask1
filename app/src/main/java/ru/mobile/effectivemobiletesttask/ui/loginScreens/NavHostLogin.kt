package ru.mobile.effectivemobiletesttask.ui.loginScreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostLogin(navHostController: NavHostController, viewModel: LoginsViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = ScreensRoute.OnboardingScreen.route
    ){
        composable(route = ScreensRoute.OnboardingScreen.route){
            OnboardingScreen(navHostController)
        }
        composable(route = ScreensRoute.LoginScreen.route){
            LoginScreen(viewModel)
        }
    }
}