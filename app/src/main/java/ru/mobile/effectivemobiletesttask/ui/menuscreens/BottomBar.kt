package ru.mobile.effectivemobiletesttask.ui.menuscreens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier
) {
    val screens = listOf(
        MenuScreensRoutes.AllCoursesScreen, MenuScreensRoutes.LikedCoursesScreen, MenuScreensRoutes.ProfileScreen
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.img!!, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (screen.route != "") {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White, selectedIconColor = Color.Green,
                    unselectedTextColor = Color.White, selectedTextColor = Color.Green
                ),
            )
        }
    }

}