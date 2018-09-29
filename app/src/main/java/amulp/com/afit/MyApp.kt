package amulp.com.afit

import amulp.com.afit.db.ObjectBox
import android.app.Application
import android.content.Context
import android.util.Log
//import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import io.objectbox.android.AndroidObjectBrowser



class MyApp: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null
        @JvmStatic
        fun getAppContext() : Context =  instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = MyApp.getAppContext()
        ObjectBox.build(this)

        if (BuildConfig.DEBUG) {
            val started = AndroidObjectBrowser(ObjectBox.boxStore).start(this)
            Log.i("ObjectBrowser", "Started: $started")
        }
        AndroidThreeTen.init(this)
        //Stetho.initializeWithDefaults(this)
    }
}