package amulp.com.afit.models

import amulp.com.afit.db.converters.DayConverter
import amulp.com.afit.db.converters.ExerciseSetConverter
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "routines")
data class Routine(
        @PrimaryKey
        val name:String,

        @TypeConverters(DayConverter::class)
        var days: MutableList<Day> = mutableListOf<Day>(),

        var currDay:Int = 0,

        @TypeConverters(ExerciseSetConverter::class)
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


