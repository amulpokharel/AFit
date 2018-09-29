package amulp.com.afit.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Exercise(
        @Id var id: Long = 0,
        val name:String = "",
        val reps:Int = 5,
        val numSets:Int = 3,
        val upperBody:Boolean = false,
        val increments:Double = when(upperBody){
            true -> 5.0
            false -> 2.5
        },
        var currentWeight:Double = 0.0
){
    fun deload(){
        currentWeight*=0.90
    }

    fun increment(){
        currentWeight+=increments
    }
}