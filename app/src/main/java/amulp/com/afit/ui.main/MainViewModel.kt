package amulp.com.afit.ui.main

import amulp.com.afit.MyApp
import amulp.com.afit.models.Exercise
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync

class MainViewModel : ViewModel() {
    private val exerciseDao = MyApp.exerciseDb!!.exerciseDao()
    var exercises:LiveData<List<Exercise>>

    init {
        exercises = exerciseDao.getAll()
    }

    fun addExercise(name:String, reps:Int, numSets:Int, upperBody:Boolean,increments:Double, startingWeight:Double){
        doAsync {
            exerciseDao.insert(Exercise(name, reps, numSets, upperBody, increments, startingWeight))
        }
    }

    fun deleteExercise(exerciseName:String) {
        doAsync { exerciseDao.deleteExercise(exerciseName) }
    }

    fun getExercisesLive() = exerciseDao.getAll()

    fun getExercise(name:String) = exerciseDao.getExercise(name)

    fun getAllExercises():List<Exercise> = exerciseDao.getAllList()
}