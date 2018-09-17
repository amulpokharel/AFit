package amulp.com.afit.db.converters

import amulp.com.afit.models.Day
import androidx.room.TypeConverter

class DayConverter {
    @TypeConverter
    fun toDayFromString(value: String): Day? {
        return null //TODO fix
    }

    @TypeConverter
    fun toStringFromDay(day:Day): String? {
        return "" //TODO fix
    }
}