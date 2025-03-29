package ru.mobile.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CourseEntity::class], version = 1)
abstract class MainDB: RoomDatabase() {
    abstract val dao:DAO
    companion object{
        fun createDB(context: Context):MainDB{
            return Room.databaseBuilder(context, MainDB::class.java, "Notes.db").build()
        }
    }
}