package amulp.com.afit.db

import amulp.com.afit.models.Routine
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routines")
    fun getAll(): LiveData<List<Routine>>

    @Query("SELECT * FROM routines")
    fun getAllList(): List<Routine>

    @Query("SELECT * FROM routines WHERE name = :getName ")
    fun getRoutine(getName:String) : Routine

    @Insert(onConflict = REPLACE)
    fun insert(routine: Routine)

    @Insert(onConflict = REPLACE)
    fun insertList(routine:List<Routine>)

    @Query("DELETE FROM routines WHERE name = :routineName")
    fun deleteRoutine(routineName:String)

    @Query("DELETE from routines")
    fun deleteAll()
}