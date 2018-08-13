package amulp.com.afit.db

import amulp.com.afit.models.Exercise
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAll(): List<Exercise>

    @Query("SELECT * FROM exercises WHERE name = :getName ")
    fun getExercise(getName:String) : Exercise

    @Insert(onConflict = REPLACE)
    fun insert(exercise: Exercise)

    @Insert(onConflict = REPLACE)
    fun insertList(exercise:List<Exercise>)

    @Query("DELETE from exercises")
    fun deleteAll()
}