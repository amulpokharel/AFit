package amulp.com.afit.ui.main

import amulp.com.afit.R
import amulp.com.afit.adapters.RecycleAdapter
import amulp.com.afit.models.Routine
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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.add_exercise_dialog.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import org.jetbrains.anko.*

class CreateRoutineFragment : Fragment(), LifecycleOwner {
    companion object {
        fun newInstance() = CreateRoutineFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recycleAdapter: RecycleAdapter
    private lateinit var dialogView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_routine_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getRoutineLive().observe(this@CreateRoutineFragment, Observer { routines ->
            recycleAdapter.setData(routines)
        })

        doAsync {
            recycleAdapter = RecycleAdapter(viewModel.getAllRoutines(), {
                deleteRoutine((it as Routine).name)
            },{
                editRoutine((it as Routine).name)
            })

            recyclerView.adapter = recycleAdapter
        }

        fab.setOnClickListener { showAddDialog() }

    }

    private fun editRoutine(name: String){
        val directions = CreateRoutineFragmentDirections.actionCreateRoutineFragmentToListFragment()
        directions.routineToPass = name
        findNavController().navigate(directions)
        context!!.toast("clicked $name")
    }

    private fun showAddDialog() {
        val layoutInflater = LayoutInflater.from(context)
        dialogView = layoutInflater.inflate(R.layout.add_routine_dialog, null)
        val alertBuilder = AlertDialog.Builder(context!!)

        alertBuilder.setView(dialogView)

        alertBuilder
                .setCancelable(false)
                .setPositiveButton("Add") { _, _ ->
                    Log.d("debug", "adding!")
                    doAsync {
                        if (!(dialogView.name.parseString("").isEmpty())) {
                            addRoutine()
                            recycleAdapter.setData(viewModel.getAllRoutines())
                            Log.d("d", "added ${dialogView.name.text}")
                        } else {
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

    private fun deleteRoutine(name: String) {
        context!!.alert("Are you sure?", "Deleting routine") {
            yesButton { viewModel.deleteRoutine(name) }
            noButton {}
        }.show()
    }


    private fun addRoutine() {
        with(dialogView) {
            viewModel.addRoutine(
                    name.parseString("")
            )
        }
    }
}