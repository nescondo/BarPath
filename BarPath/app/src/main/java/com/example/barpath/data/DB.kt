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
data class WorkoutSet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val totalReps: Int,
    val duration: Float,
    val avgSpeed: Float,
    val lowestDepth: Float
)

@Dao
interface WorkoutSetDao {
    @Insert
    suspend fun insertWorkoutSet(workoutSet: WorkoutSet)

    @Delete
    suspend fun deleteWorkoutSet(workoutSet: WorkoutSet)

    @Query("DELETE FROM workoutSet WHERE id = :id")
    suspend fun deleteWorkoutSet(id: Int)

    @Query("SELECT * FROM workoutSet WHERE id = :id")
    suspend fun getWorkoutSet(id: Int): WorkoutSet

    @Query("SELECT * FROM workoutset ORDER BY id DESC")
    fun getAllWorkoutSets(): Flow<List<WorkoutSet>>

    @Query("DELETE FROM workoutSet")
    suspend fun deleteAllWorkoutSets()
}

@Database(
    entities = [WorkoutSet::class], version = 1, exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getInstance(): WorkoutSetDao
}