package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.skydoves.cloudy.cloudy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mobile.data.models.Course
import ru.mobile.data.room.CourseEntity
import ru.mobile.effectivemobiletesttask.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AllCoursesScreen(viewModel: CoursesViewModel) {
    val config = LocalConfiguration.current
    val heightScreen = config.screenHeightDp
    val corScope = rememberCoroutineScope()
    Box(modifier = Modifier.background(Color.Black)){

            Column(Modifier.padding(horizontal = 10.dp).padding(top = 10.dp)) {
                Row(
                    modifier = Modifier.padding(top = 10.dp).height((heightScreen * 0.08).dp)
                ) {
                    TextField(modifier = Modifier.weight(5f).fillMaxHeight(),
                        value = viewModel.searchText.value,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xff32333A),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xff32333A),
                            unfocusedTextColor = Color.White,
                            focusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(6000f),
                        label = { Text("Search courses...", color = Color(0xffF2F2F3)) },
                        onValueChange = { viewModel.searchText.value = it },
                        textStyle = TextStyle(fontSize = 15.sp),
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Искать") })
                    Spacer(Modifier.width(10.dp))
                    Button(
                        onClick = {},
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff32333A)),
                        modifier = Modifier.fillMaxHeight().weight(1f)
                    ) { Image(painterResource(R.drawable.img_2), "Фильтр") }
                }
                Row(
                    modifier = Modifier.height(32.dp).align(Alignment.End).clickable {
                        viewModel.thisCourseAll.clear()
                        viewModel.mutableLiveData = viewModel.sortByDate(viewModel.mutableLiveData)
                        for (el in viewModel.mutableLiveData.value?.courses!!){
                        viewModel.thisCourseAll.add(el)
                    }
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("По дате добавления", color = Color(0xff12B956))
                    Spacer(modifier = Modifier.width(3.dp))
                    Image(
                        painterResource(R.drawable.img_3),
                        "",
                        modifier = Modifier.fillMaxHeight()
                    )
                }

                LazyColumn {
                    items(viewModel.thisCourseAll) {
                        var colorState =
                            remember { mutableStateOf(Color.Black) }
                        if (it?.hasLike == true) {
                            colorState.value = Color(0xff12B956)
                        }else{
                            colorState.value = Color.Black
                        }
                        Card(
                            modifier = Modifier.padding(top = 15.dp).fillMaxWidth()
                                .height((heightScreen * 0.330).dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xff24252A))
                        ) {
                            Column {
                                Box(
                                    Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                                        .background(Color.Transparent).weight(0.9f)
                                ) {
                                    Image(
                                        painterResource(R.drawable.img_6),
                                        "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    Box(Modifier.align(Alignment.TopEnd).padding(top = 5.dp, end = 5.dp).clickable {

                                        CoroutineScope(Dispatchers.IO).launch {
                                            if(it!!.hasLike == false) {
                                                it.hasLike = true
                                                viewModel.db.dao.insertNote(
                                                    CourseEntity(
                                                        it.id,
                                                        it.hasLike,
                                                        it.price,
                                                        it.publishDate,
                                                        it.rate,
                                                        it.startDate,
                                                        it.text,
                                                        it.title
                                                    )
                                                )
                                                viewModel.thisCourseLiked.clear()
                                                for (it in viewModel.db.dao.getAllNotes()){
                                                    viewModel.thisCourseLiked.add(Course(
                                                        it.hasLike,it.id,
                                                        it.price,
                                                        it.publishDate,
                                                        it.rate,
                                                        it.startDate,
                                                        it.text,
                                                        it.title))
                                                }
                                                colorState.value = Color(0xff12B956)
                                            }else{
                                                it.hasLike = false
                                                viewModel.db.dao.delete(
                                                    CourseEntity(
                                                        it.id,
                                                        it.hasLike,
                                                        it.price,
                                                        it.publishDate,
                                                        it.rate,
                                                        it.startDate,
                                                        it.text,
                                                        it.title
                                                    )
                                                )
                                                viewModel.thisCourseLiked.clear()
                                                for (it in viewModel.db.dao.getAllNotes()){
                                                    viewModel.thisCourseLiked.add(Course(
                                                        it.hasLike,it.id,
                                                        it.price,
                                                        it.publishDate,
                                                        it.rate,
                                                        it.startDate,
                                                        it.text,
                                                        it.title))
                                                }
                                                colorState.value = Color.Black
                                            }
                                        }
                                        }){
                                        Image(
                                            imageVector =  Icons.Outlined.FavoriteBorder,
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(colorState.value)
                                        )
                                    }
                                    Row(Modifier.align(Alignment.BottomStart)) {
                                        Box(
                                            Modifier.padding(5.dp).width(48.dp)
                                                .height(20.dp).clip(RoundedCornerShape(12.dp)).background(color = Color.White.copy(alpha = 0.6f))

                                                    ) {

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center,
                                                modifier = Modifier
                                            ) {
                                                Image(
                                                    painterResource(R.drawable.img_5),
                                                    "",
                                                    modifier = Modifier.weight(1f)
                                                )
                                                Text(
                                                    it?.rate.toString(),
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                        }

                                        Box(
                                            Modifier.padding(5.dp).width(105.dp)
                                                .height(20.dp).clip(RoundedCornerShape(12.dp)).background(color = Color.White.copy(alpha = 0.6f))
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = viewModel.prettyDate(it?.publishDate.toString()),
                                                    modifier = Modifier.weight(1f).fillMaxHeight(),
                                                    fontSize = 11.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    Column {
                                        Text(
                                            text = it?.title.toString(),
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(5.dp)
                                        )
                                        Text(
                                            text = it?.text.toString(),
                                            maxLines = 2,
                                            color = Color.White,
                                            modifier = Modifier.alpha(0.6f)
                                        )
                                        Spacer(Modifier.weight(1f))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.align(
                                                Alignment.End
                                            )
                                        ) {
                                            Text(
                                                it?.price.toString(),
                                                modifier = Modifier.align(Alignment.Bottom)
                                                    .padding(5.dp),
                                                color = Color.White,
                                                fontSize = 20.sp
                                            )
                                            Spacer(modifier = Modifier.weight(6f))
                                            Text(
                                                "Подробнее",
                                                modifier = Modifier,
                                                color = Color(0xff12B956)
                                            )
                                            Image(
                                                painterResource(R.drawable.img_7),
                                                "",
                                                modifier = Modifier.padding(end = 5.dp).weight(1f)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }
}


//@RequiresApi(Build.VERSION_CODES.S)
//@Preview
//@Composable
//fun adawdadaw(){
//    AllCoursesScreen(CoursesViewModel())
//}
//
