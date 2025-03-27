package ru.mobile.effectivemobiletesttask.ui.loginScreens

sealed class ScreensRoute(val route: String) {
    object OnboardingScreen: ScreensRoute(route = "onboarding_screen")
    object LoginScreen: ScreensRoute(route = "login_screen")
}