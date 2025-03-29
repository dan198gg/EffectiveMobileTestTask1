package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.mobile.effectivemobiletesttask.R

sealed class MenuScreensRoutes(val route: String = "", val img : ImageVector? = null, val title: String) {
    object AllCoursesScreen: MenuScreensRoutes("all_courses", Icons.Outlined.Home,"Главная")
    object LikedCoursesScreen: MenuScreensRoutes("liked_courses", Icons.Default.FavoriteBorder,"Избранное")
    object ProfileScreen: MenuScreensRoutes("", Icons.Outlined.AccountCircle,"Аккаунт")
}