package amulp.com.afit.ui.main

import amulp.com.afit.R
import amulp.com.afit.adapters.RecycleAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.add_exercise_dialog.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast


class ListFragment : Fragment(), LifecycleOwner{
    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recycleAdapter:RecycleAdapter
    private lateinit var dialogView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getExercisesLive().observe(this@ListFragment, Observer { exercises ->
            recycleAdapter.setData(exercises)  } )

        doAsync {
            recycleAdapter = RecycleAdapter(viewModel.getAllExercises()) {
                context!!.toast(it.name + " clicked")
            }
            recyclerView.adapter = recycleAdapter
        }

        fab.setOnClickListener { showAddDialog() }
    }

    private fun showAddDialog(){
        val layoutInflater = LayoutInflater.from(context)
        dialogView = layoutInflater.inflate(R.layout.add_exercise_dialog, null)

        val alertBuilder = AlertDialog.Builder(context!!)

        alertBuilder.setView(dialogView)

        alertBuilder
                .setCancelable(false)
                .setPositiveButton("Add") { _, _ ->
                    Log.d("debug", "adding!")
                    doAsync {
                        if (viewModel.getExercise(dialogView.name.text.toString()) == null) {
                            addExercise()
                            recycleAdapter.setData(viewModel.getAllExercises())
                            Log.d("d", "added ${dialogView.name.text}")
                        } else
                            Log.d("d", "didn't add")
                    }

                }

                .setNegativeButton("Cancel"
                ) {
                    dialogBox, _ -> dialogBox.cancel()
                }
                .create()
                .show()
    }

    private fun addExercise(){
        with(dialogView){
            viewModel.addExercise(
                    name.text.toString(),
                    reps.text.toString().toInt(),
                    num_sets.text.toString().toInt(),
                    upper.isActivated,
                    increment.text.toString().toDouble()
            )
        }
    }
}
