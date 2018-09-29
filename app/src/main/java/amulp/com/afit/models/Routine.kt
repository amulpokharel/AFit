package amulp.com.afit.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique
import io.objectbox.relation.ToMany

@Entity
data class Routine(
        @Id var id:Long = 0,
        @Unique val name:String = "",

        var currDay:Int = 0
)
{
    var days:ToMany<Day>? = null

    fun getNumDays():Int{
        if (days.isNullOrEmpty())
            return days!!.size
        return 0
    }

}


