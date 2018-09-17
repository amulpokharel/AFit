package amulp.com.afit.db.converters

import amulp.com.afit.models.ExerciseSet
import androidx.room.TypeConverter

class ExerciseSetConverter {
    @TypeConverter
    fun toExerciseSetFromString(value: String): ExerciseSet? {
        return null //TODO fix
    }

    @TypeConverter
    fun toStringFromExerciseSet(set: ExerciseSet): String? {
        return "" //TODO fix
    }
}