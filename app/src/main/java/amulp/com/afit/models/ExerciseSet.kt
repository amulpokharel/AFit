package amulp.com.afit.models

import amulp.com.afit.db.converters.ExerciseSetConverter
import androidx.room.Entity
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDateTime

@Entity
@JsonClass(generateAdapter = true)
data class ExerciseSet(
        val exercise:String,
        val weight:Double,
        val setNumber:Int,
        val reps:Int,
        val timeDate:LocalDateTime = LocalDateTime.now(),
        val complete:Boolean = reps < 5,
        val amrap:Boolean = reps >= 10
 )