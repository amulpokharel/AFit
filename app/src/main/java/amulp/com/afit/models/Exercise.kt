package amulp.com.afit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
        @PrimaryKey
        val name:String,

        val reps:Int = 5,
        val numSets:Int = 3,
        val upperBody:Boolean = false,
        val increments:Double = when(upperBody){
            true -> 5.0
            false -> 2.5
        }
)