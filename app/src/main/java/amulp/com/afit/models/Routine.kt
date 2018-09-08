package amulp.com.afit.models

data class Routine(
        val name:String,
        var days: MutableList<Day>,
        private var currDay:Int = 0,
        val sets:MutableList<ExerciseSet> = mutableListOf()
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