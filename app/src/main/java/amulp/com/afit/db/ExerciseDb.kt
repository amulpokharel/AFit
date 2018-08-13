package amulp.com.afit.db

import amulp.com.afit.models.Exercise
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDb : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    companion object {
        private var INSTANCE: ExerciseDb? = null

        fun getInstance(context: Context): ExerciseDb? {
            if (INSTANCE == null) {
                synchronized(ExerciseDb::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ExerciseDb::class.java, "exercises.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}