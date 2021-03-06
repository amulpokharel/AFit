package amulp.com.afit.ui.main

import amulp.com.afit.R
import amulp.com.afit.adapters.RecycleAdapter
import amulp.com.afit.models.Exercise
import amulp.com.afit.utils.parseString
import amulp.com.afit.utils.showKeyboard
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
import org.jetbrains.anko.*


class ListFragment : Fragment(), LifecycleOwner {
    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recycleAdapter: RecycleAdapter
    private lateinit var dialogView: View
    private lateinit var routineName:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getExercisesLive().observe(this@ListFragment, Observer { exercises ->
            recycleAdapter.setData(exercises)
        })

        val args =ListFragmentArgs.fromBundle(arguments!!)
        routineName = args.routineToPass


        doAsync {
            recycleAdapter = RecycleAdapter(viewModel.getAllExercises(), {
                deleteExercise((it as Exercise).name)
            },{
                //TODO add edit function?
            })

            recyclerView.adapter = recycleAdapter
        }

        fab.setOnClickListener { showAddDialog() }

    }

    private fun showAddDialog() {
        val layoutInflater = LayoutInflater.from(context)
        dialogView = layoutInflater.inflate(R.layout.add_exercise_dialog, null)
        val alertBuilder = AlertDialog.Builder(context!!)

        alertBuilder.setView(dialogView)

        alertBuilder
                .setCancelable(false)
                .setPositiveButton("Add") { _, _ ->
                    Log.d("debug", "adding!")
                    doAsync {
                        if (!(dialogView.name.parseString("").isEmpty())) {
                            try {
                                addExercise()
                                recycleAdapter.setData(viewModel.getAllExercises())
                                Log.d("d", "added ${dialogView.name.text}")
                                uiThread { context!!.toast("added ${dialogView.name.text}") }
                            } catch (e: Exception) {
                                uiThread { context!!.toast("didn't add") }
                                Log.d("d", "didn't add")
                            }
                        } else {
                            uiThread { context!!.toast("didn't add") }
                            Log.d("d", "didn't add")
                        }
                    }

                }

                .setNegativeButton("Cancel"
                ) { dialogBox, _ ->
                    dialogBox.cancel()
                }
                .create()
                .show()
        dialogView.showKeyboard()
    }

    private fun deleteExercise(name: String) {
        context!!.alert("Are you sure?", "Deleting exercise") {
            yesButton { viewModel.deleteExercise(name) }
            noButton {}
        }.show()
    }


    private fun addExercise() {
        with(dialogView) {
            viewModel.addExercise(
                    name.parseString(""),
                    reps.parseString("5").toInt(),
                    num_sets.parseString("3").toInt(),
                    upper.isChecked,
                    increment.parseString(when (upper.isChecked) {
                        true -> "5.0"
                        false -> "2.5"
                    }).toDouble(),
                    starting_weight.parseString("100").toDouble()
            )
        }
    }
}
