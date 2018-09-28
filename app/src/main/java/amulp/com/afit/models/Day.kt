package amulp.com.afit.models

import amulp.com.afit.db.converters.DayConverter
import androidx.room.Entity
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Day(
        val number:Int = 0,
        var exercises: MutableList<String> = mutableListOf()
)