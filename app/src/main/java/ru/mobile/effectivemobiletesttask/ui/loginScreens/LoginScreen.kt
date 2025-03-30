package ru.mobile.effectivemobiletesttask.ui.loginScreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mobile.effectivemobiletesttask.R
import ru.mobile.effectivemobiletesttask.ui.menuscreens.MenuActivity


@Composable
fun LoginScreen(viewModel: LoginsViewModel) {
    val ctx = LocalContext.current
    val urlVK = "https://vk.com/"
    val urlOK = "https://ok.ru/"
    val config = LocalConfiguration.current
    val heightScreen = config.screenHeightDp
    val sateEmail = remember { mutableStateOf("example@gmail.com") }
    val widthScreen = config.screenWidthDp
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(vertical = 70.dp, horizontal = 10.dp)
        ) {
            Text("Вход", fontSize = 36.sp, modifier = Modifier.align(Alignment.Start), color = Color.White)
            Text(
                "Email",
                fontSize = 21.sp,
                modifier = Modifier.padding(vertical = (5.dp)).align(Alignment.Start), color = Color.White
            )
            TextField(

                value = viewModel.email.value,
                onValueChange = {
                    if (it.isNotEmpty()) sateEmail.value = ""
                    else sateEmail.value = "example@gmail.com"
                    viewModel.email.value = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xff32333A),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xff32333A),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                modifier = Modifier.padding(vertical = 3.dp).height(45.dp).fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                label = { Text(sateEmail.value)}
            )
            Text(
                "Пароль",
                fontSize = 21.sp,
                modifier = Modifier.padding(top = (5.dp)).align(Alignment.Start), color = Color.White
            )
            TextField(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xff32333A),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xff32333A),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                modifier = Modifier.padding(vertical = 3.dp).height(45.dp).fillMaxWidth(),
                shape = RoundedCornerShape(60.dp),
                label = { Text("Введите пароль")}
            )
            Button(
                onClick = {
                    if (viewModel.check()){
                        viewModel.saveName()
                        ctx.startActivity(Intent(ctx, MenuActivity::class.java))
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff12B956)),
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
            ) {
                Text("Вход")
            }
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Text("Нету аккаунта?", fontSize = 16.sp, color = Color.White)
                Text("Регитрация", fontSize = 16.sp, color = Color(0xff12B956))
            }
            Text("Забыл пароль", fontSize = 16.sp, color = Color(0xff12B956))

            HorizontalDivider(
                color = Color(0xff4D555E),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(urlVK)
                        )
                        ctx.startActivity(urlIntent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff2683ED)),
                    modifier = Modifier.weight(1f)
                ) {
                    Image(painterResource(R.drawable.wk_image), "", Modifier.height(20.dp).width(30.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(urlOK)
                        )
                        ctx.startActivity(urlIntent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffF98509)),
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painterResource(R.drawable.ok_image),
                        "",
                        Modifier.height(20.dp).width(30.dp)
                    )
                }
            }
        }
    }
}


