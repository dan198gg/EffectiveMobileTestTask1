package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavHostCourses(navController: NavHostController, coursesViewModel: CoursesViewModel) {
    NavHost(navController, startDestination = MenuScreensRoutes.AllCoursesScreen.route) {
        composable(MenuScreensRoutes.AllCoursesScreen.route) {
            AllCoursesScreen(viewModel = coursesViewModel)
        }
        composable(MenuScreensRoutes.LikedCoursesScreen.route) {
            LikedCoursesScreen(viewModel = coursesViewModel)
        }
    }
}