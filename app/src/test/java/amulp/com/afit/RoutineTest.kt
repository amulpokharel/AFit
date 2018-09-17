package amulp.com.afit

import amulp.com.afit.models.Day
import amulp.com.afit.models.Routine
import org.junit.Test

class RoutineTest {


    @Test
    fun routineTest(){
        val routine = Routine("beginning", mutableListOf())

        routine.addDay(mutableListOf("test", "test2", "test3"))
        routine.addDay(mutableListOf("testi2", "test3", "test4"))

        for(x in 1..11){
            routine.incrementDay()
        }

        print(routine.nextDay())
    }


    @Test
    fun dayTest(){
        val day = Day(1, mutableListOf("derp", "merp", "serp"))

        print(day.toString())
    }
}