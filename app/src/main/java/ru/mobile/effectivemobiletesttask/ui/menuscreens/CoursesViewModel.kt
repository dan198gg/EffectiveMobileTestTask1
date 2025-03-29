package ru.mobile.effectivemobiletesttask.ui.menuscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mobile.data.models.Course
import ru.mobile.data.models.CourseData
import ru.mobile.data.repository.MobileRepossitory
import ru.mobile.data.room.CourseEntity
import ru.mobile.data.room.MainDB
import ru.mobile.domain.MakePrettyDate
import ru.mobile.domain.SortCoursesByDate
import ru.mobile.domain.SortLikedCourses
import ru.mobile.effectivemobiletesttask.ui.loginScreens.LoginsViewModel
import ru.mobile.effectivemobiletesttask.ui.loginScreens.LoginsViewModel.Companion

class CoursesViewModel(val mobileRepository: MobileRepossitory?):ViewModel() {

    val searchText = mutableStateOf("")
    val db = createDb()
    var mutableLiveData: MutableLiveData<CourseData?> = MutableLiveData(CourseData(mutableListOf()))
    var thisCourseAll: SnapshotStateList<Course?> = SnapshotStateList()
    var thisCourseLiked: SnapshotStateList<Course?> = SnapshotStateList()

    var likedCourses = mutableLiveData.value?.courses?.takeWhile { it?.hasLike == true }?.toMutableList()
    @RequiresApi(Build.VERSION_CODES.O)
    fun prettyDate(date: String): String {
        return MakePrettyDate(date).date()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByDate(list: MutableLiveData<CourseData?>): MutableLiveData<CourseData?> {
        return SortCoursesByDate(list).sortCoursesByDate()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByDateLiked(list: MutableList<CourseEntity?>): MutableList<CourseEntity?> {
        return SortLikedCourses(list).sortLikedCoursesByDate()
    }

    fun createDb(): MainDB{
        return mobileRepository!!.createRoomDB()
    }


    init {
        CoroutineScope(Dispatchers.IO).launch {
            mobileRepository?.getCourses(mutableLiveData)
        }
        for (el in mutableLiveData.value?.courses!!.toList()) thisCourseAll.add(el)
        viewModelScope.launch {
            mutableLiveData.observeForever({
                thisCourseAll.clear()
                for (el in mutableLiveData.value?.courses!!.toList()) thisCourseAll.add(el)


                CoroutineScope(Dispatchers.IO).launch {
                    thisCourseLiked.clear()
                    for (el in mutableLiveData.value?.courses!!.toList()){
                    if(el?.hasLike == true) {
                        db.dao.insertNote(CourseEntity(el.id, el.hasLike, el.price, el.publishDate, el.rate, el.startDate,el.text, el.title))
                    }
                }
                    for (el in db.dao.getAllNotes()) {
                        thisCourseLiked.add(
                            Course(
                                el.hasLike,
                                el.id,
                                el.price,
                                el.publishDate,
                                el.rate,
                                el.startDate,
                                el.text,
                                el.title
                            )
                        )
                    }
                }
            })



            Thread.sleep(1000)
        }
    }
}