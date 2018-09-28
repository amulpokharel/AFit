package amulp.com.afit.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine(
        @PrimaryKey
        val name:String = "",

        @Embedded
        var days: MutableList<Day> = mutableListOf<Day>(),

        var currDay:Int = 0,

        @Embedded
        val sets:MutableList<ExerciseSet> = mutableListOf<ExerciseSet>()
)
{
    fun addDay(exercises:MutableList<String>){
        days.add(Day(days.size + 1, exercises))
    }

    fun addSet(exerciseSet: ExerciseSet){
        sets.add(exerciseSet)
        currDay++
    }

    fun incrementDay() = currDay++

    fun nextDay() = currDay % days.size
}


