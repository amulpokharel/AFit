package amulp.com.afit.db

import amulp.com.afit.models.Routine
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Routine::class], version = 1, exportSchema = false)
abstract class RoutineDb : RoomDatabase() {

    abstract fun routineDao(): RoutineDao

    companion object {
        private var INSTANCE: RoutineDb? = null

        fun getInstance(context: Context): RoutineDb? {
            if (INSTANCE == null) {
                synchronized(RoutineDb::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RoutineDb::class.java, "routines.db")
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