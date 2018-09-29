package amulp.com.afit.models

data class Routine(
        val name:String = "",

        var days: MutableList<Array<Int>>,
        var currDay:Int = 0
)
{
    fun addDay(arr: Array<Int>){
        days.add(arr)
    }

    fun incrementDay() = currDay++

    fun nextDay() = currDay % days.size
}


