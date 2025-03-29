package ru.mobile.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(courseEntity: CourseEntity)

    @Delete
    suspend fun delete(courseEntity: CourseEntity)

    @Query("SELECT * FROM CourseEntity")
    fun getAllNotes(): List<CourseEntity>
}