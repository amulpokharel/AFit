package amulp.com.afit.ui.main

import amulp.com.afit.R
import amulp.com.afit.utils.inflate
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.add_exercise_fragment.*
import org.jetbrains.anko.doAsync

class AddExerciseFragment: Fragment() {

    companion object {
        private var fragmentType:String = ""
        fun newInstance() = AddExerciseFragment()
    }
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container!!.inflate(R.layout.add_exercise_fragment)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        add_exercise.setOnClickListener { addExercise() }

        list_exercises.setOnClickListener { logExercises() }
    }

    private fun addExercise(){
        viewModel.addExercise(
                name.text.toString(),
                reps.text.toString().toInt(),
                num_sets.text.toString().toInt(),
                upper.isActivated,
                increment.text.toString().toDouble()
        )

        name.text.clear()
        reps.text.clear()
        num_sets.text.clear()
        upper.isActivated = false
        increment.text.clear()
    }

    private fun logExercises(){
        doAsync {
            Log.d("d", viewModel.getAllExercises().toString())
        }
    }

}