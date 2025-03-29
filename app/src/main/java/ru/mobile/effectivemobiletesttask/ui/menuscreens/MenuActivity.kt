package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.graphics.drawable.Drawable.ConstantState
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mobile.effectivemobiletesttask.ui.loginScreens.LoginsViewModel
import ru.mobile.effectivemobiletesttask.ui.menuscreens.ui.theme.EffectiveMobileTestTaskTheme

class MenuActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: CoursesViewModel by viewModel()
            val navController = rememberNavController()
            var buttonsVisible = remember { mutableStateOf(true) }
            Box(Modifier.background(Color.Black)) {
                Scaffold( Modifier.background(Color.Black), contentColor = Color.Black,
                    containerColor = Color.Black,
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            state = buttonsVisible,
                            modifier = Modifier
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier.padding(it)
                    ) {
                        NavHostCourses(navController, viewModel)
                    }
                }
            }
        }
    }
}
