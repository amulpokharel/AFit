package amulp.com.afit.models

data class Routine(
        val name:String,
        private var days: MutableList<Day>,
        var nextDay:Int = 0
)
{
    fun addDay(exercises:MutableList<Exercise>){
        days.add(Day(days.size + 1, exercises))
    }
}