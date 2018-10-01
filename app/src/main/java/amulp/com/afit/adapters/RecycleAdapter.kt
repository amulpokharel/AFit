package amulp.com.afit.adapters

import amulp.com.afit.R
import amulp.com.afit.models.Exercise
import amulp.com.afit.models.Routine
import amulp.com.afit.utils.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.routine_item.view.*
import kotlinx.android.synthetic.main.exercise_item.view.*


class RecycleAdapter(var items: List<Any>, private val deleteListener: (Any) -> Unit, private val editListener: (Any) -> Unit)
    : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(items.first()){
        is Exercise -> ViewHolder(parent.inflate(R.layout.exercise_item))
        is Routine -> ViewHolder(parent.inflate(R.layout.routine_item))
        else -> ViewHolder(parent.inflate(R.layout.exercise_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], deleteListener, editListener)

    override fun getItemCount() = items.size

    fun setData(newData: List<Any>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Any, deleteListener: (Any) -> Unit, editListener: (Any) -> Unit) = with(itemView) {
            if(item is Exercise){
                exerciseFirstLine.text = item.name
                exerciseSecondLine.text = item.toString()
                exerciseDeleteButton.setOnClickListener { deleteListener(item) }
            }
            else if( item is Routine){
                routineFirstLine.text = item.name
                routineSecondLine.text = item.toString()
                routineDeleteButton.setOnClickListener{deleteListener(item)}
                routineEditButton.setOnClickListener {editListener(item)}
            }
        }
    }
}