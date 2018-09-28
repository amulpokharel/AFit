package amulp.com.afit.models

import androidx.room.Entity
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDateTime

@Entity
@JsonClass(generateAdapter = true)
data class ExerciseSet(
        val exercise:String = "",
        val weight:Double = 0.0,
        val setNumber:Int = 1,
        val reps:Int = 1,
        val timeDate:LocalDateTime = LocalDateTime.now(),
        val complete:Boolean = reps < 5,
        val amrap:Boolean = reps >= 10
 )