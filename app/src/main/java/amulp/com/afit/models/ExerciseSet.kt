package amulp.com.afit.models

import org.threeten.bp.LocalDateTime

data class ExerciseSet(
        val exercise:String,
        val weight:Double,
        val setNumber:Int,
        val reps:Int,
        val timeDate:LocalDateTime = LocalDateTime.now(),
        val complete:Boolean = reps < 5,
        val amrap:Boolean = reps >= 10
 )