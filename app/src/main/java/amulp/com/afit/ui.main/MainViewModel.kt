package amulp.com.afit.ui.main

import amulp.com.afit.MyApp
import amulp.com.afit.models.Exercise
import amulp.com.afit.models.Routine
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync

class MainViewModel : ViewModel() {
    private val exerciseDao = MyApp.exerciseDb!!.exerciseDao()
    private val routineDao  = MyApp.routineDb!!.routineDao()
    val exercises:LiveData<List<Exercise>>
    val routines:LiveData<List<Routine>>

    init {
        //TODO set up DAOs later?
        exercises = exerciseDao.getAll()
        routines = routineDao.getAll()
    }

    fun addExercise(name:String, reps:Int, numSets:Int, upperBody:Boolean,increments:Double, startingWeight:Double){
        doAsync {
            exerciseDao.insert(Exercise(name, reps, numSets, upperBody, increments, startingWeight))
        }
    }

    fun addRoutine(name:String){
        doAsync {
            routineDao.insert(Routine(name))
        }
    }

    fun deleteExercise(exerciseName:String) {
        doAsync { exerciseDao.deleteExercise(exerciseName) }
    }

    fun deleteRoutine(routineName:String){
        doAsync { routineDao.deleteRoutine(routineName) }
    }

    //TODO stop calling dbs too often?
    fun getExercisesLive() = exerciseDao.getAll()

    fun getRoutineLive() = routineDao.getAll()

    fun getExercise(name:String) = exerciseDao.getExercise(name)

    fun getRoutine(name:String) = routineDao.getRoutine(name)

    fun getAllExercises():List<Exercise> = exerciseDao.getAllList()

    fun getAllRoutines():List<Routine> = routineDao.getAllList()
}