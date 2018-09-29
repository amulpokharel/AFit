package amulp.com.afit.ui.main

import amulp.com.afit.db.ObjectBox
import amulp.com.afit.models.*
import android.util.Log
import androidx.lifecycle.ViewModel
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.exception.UniqueViolationException
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import org.jetbrains.anko.doAsync


class MainViewModel : ViewModel() {

    private var exerciseBox: Box<Exercise> = ObjectBox.boxStore.boxFor()
    private var routineBox: Box<Routine> = ObjectBox.boxStore.boxFor()
    private var dayBox: Box<Day> = ObjectBox.boxStore.boxFor()

    private var exerciseQuery: Query<Exercise> = exerciseBox.query { order(Exercise_.name) }
    private var routeQuery: Query<Routine> = routineBox.query { order(Routine_.name) }
    private var dayQuery: Query<Day> = dayBox.query { order(Day_.id) }

    private var exerciseLiveData: ObjectBoxLiveData<Exercise>? = null

    init {

    }

    @Throws(UniqueViolationException::class)
    fun addExercise(name: String, reps: Int, numSets: Int, upperBody: Boolean, increments: Double, startingWeight: Double) {
        doAsync {
            val exercise = Exercise(0, name, reps, numSets, upperBody, increments, startingWeight)
            exerciseBox.put(exercise)
            Log.d("added", "Exercise id is $exercise.id")
        }
    }

    fun addRoutine(name: String) {
        doAsync {
            //routineDao.insert(Routine(name))
        }
    }

    fun deleteExercise(exerciseName: String) {
        doAsync { exerciseBox.query().equal(Exercise_.name, exerciseName).build().remove() }
    }

    fun deleteRoutine(routineName: String) {
        //doAsync { routineDao.deleteRoutine(routineName) }
    }

    //TODO stop calling dbs too often?
    fun getExercisesLive(): ObjectBoxLiveData<Exercise> {
        if (exerciseLiveData == null) {
            exerciseLiveData = ObjectBoxLiveData(exerciseBox.query().order(Exercise_.name).build())
        }

        return exerciseLiveData!!
    }

    fun getAllExercises(): List<Exercise> {
        return exerciseBox.query().order(Exercise_.name).build().find()
    }


/*    fun getRoutineLive() = routineDao.getAll()

    fun getExercise(name:String) = exerciseDao.getExercise(name)

    fun getRoutine(name:String) = routineDao.getRoutine(name)


    fun getAllRoutines():List<Routine> = routineDao.getAllList()*/
}