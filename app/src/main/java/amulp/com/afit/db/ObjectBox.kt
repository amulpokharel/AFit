package amulp.com.afit.db

import amulp.com.afit.models.MyObjectBox
import android.content.Context
import io.objectbox.BoxStore

object ObjectBox {

    lateinit var boxStore: BoxStore
        private set

    fun build(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()
    }
}