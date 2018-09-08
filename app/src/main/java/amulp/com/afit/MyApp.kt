package amulp.com.afit

import amulp.com.afit.db.ExerciseDb
import amulp.com.afit.db.RoutineDb
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen

class MyApp: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null
        var exerciseDb:ExerciseDb? = null
        var routineDb:RoutineDb? = null

        @JvmStatic
        fun getAppContext() : Context =  instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = MyApp.getAppContext()
        exerciseDb = ExerciseDb.getInstance(instance!!.applicationContext)
        routineDb = RoutineDb.getInstance(instance!!.applicationContext)

        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
    }
}