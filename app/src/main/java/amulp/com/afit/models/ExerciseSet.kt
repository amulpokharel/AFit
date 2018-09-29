package amulp.com.afit.models

import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDateTime

@JsonClass(generateAdapter = true)
data class ExerciseSet(
        val id:Int = 0,

        val weight:Double = 0.0,
        val setNumber:Int = 1,
        val reps:Int = 1,
        val timeDate:LocalDateTime = LocalDateTime.now(),
        val complete:Boolean = reps < 5,
        val amrap:Boolean = reps >= 10
 )