package ru.mobile.effectivemobiletesttask.ui.loginScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import dev.chrisbanes.haze.HazeState


@Composable
fun OnboardingScreen(navHostController: NavHostController){
    val configuration = LocalConfiguration.current
    val heightScreen = configuration.screenHeightDp
    val widthScreen = configuration.screenWidthDp
    val scrollState = rememberScrollState(initial = widthScreen/2)
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Text("Тысячи курсов в одном месте", fontSize = 37.sp, color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = (heightScreen * 0.09).dp))
            RectWithCourse(
                modifier = Modifier
                    .width((widthScreen * 1.5).dp)
                    .horizontalScroll(state = scrollState)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .height((heightScreen * 0.375).dp)
            )
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xff12B956)), onClick = {
                navHostController.navigate(ScreensRoute.LoginScreen.route)
            }, modifier = Modifier.padding((heightScreen * 0.1).dp).width((widthScreen * 0.91).dp)) {
                Text("Продолжить")
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0x11111)
@Composable
fun RectWithCourse(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val heightScreen = configuration.screenHeightDp

    val padding = (heightScreen * 0.003).dp
    Box(modifier = modifier){
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row{
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("1С Администрирование", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF12B956)),
                    modifier = Modifier.rotate(-15f).height((heightScreen * 0.075).dp)
                ) { Text("RabbitMQ") }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("Трафик", color = Color.White) }
            }
            Row(modifier = Modifier.padding(padding).height((heightScreen * 0.065).dp)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("Контент маркетинг", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("B2B маркетинг", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("Google Аналитика", color = Color.White) }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier =  Modifier.padding(padding)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("UX исследователь", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("Веб-аналитика", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF12B956)),
                    modifier = Modifier.rotate(15f).height((heightScreen * 0.075).dp)
                ) { Text("Big Data") }

            }
            Row(Modifier.padding(padding).zIndex(1f).height((heightScreen * 0.065).dp)) { Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                modifier = Modifier.fillMaxHeight().alpha(0.79f)
            ) { Text("Геймдизайн", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("Веб-дизайн", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("Cinema 4D", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.fillMaxHeight().alpha(0.79f)
                ) { Text("Промпт инженеринг", color = Color.White) }

            }
            Row(Modifier.padding(padding)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("Webflow", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF12B956)),
                    modifier = Modifier.rotate(-15f).height((heightScreen * 0.075).dp).zIndex(0f)
                ) { Text("Three.js") }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("Парсинг", color = Color.White) }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF32333A)),
                    modifier = Modifier.height((heightScreen * 0.075).dp).alpha(0.79f)
                ) { Text("Python-разработка", color = Color.White) }
            }

        }
    }
}
