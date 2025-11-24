package com.example.barpath.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val depthHit: Boolean,
    val depthAngle: Float,
    val heelRise: Boolean,
    val heelAngle: Float
)

@Dao
interface PhotoDao {
    @Insert
    suspend fun insertPhoto(photo: Photo)

    @Delete
    suspend fun deletePhoto(photo: Photo)

    @Query("DELETE FROM photo WHERE id = :id")
    suspend fun deletePhoto(id: Int)

    @Query("SELECT * FROM photo WHERE id = :id")
    suspend fun getPhoto(id: Int): Photo

    @Query("SELECT * FROM photo ORDER BY id DESC")
    fun getAllPhotos(): Flow<List<Photo>>

}

@Database(
    entities = [Photo::class], version = 1, exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getInstance(): PhotoDao
}