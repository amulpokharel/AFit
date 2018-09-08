package amulp.com.afit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine(
        @PrimaryKey
        val name:String,

        var days: MutableList<Day> = mutableListOf<Day>(),
        private var currDay:Int = 0,
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