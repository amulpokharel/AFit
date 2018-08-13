package amulp.com.afit.ui.main

import amulp.com.afit.R
import amulp.com.afit.utils.inflate
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

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
    }
}