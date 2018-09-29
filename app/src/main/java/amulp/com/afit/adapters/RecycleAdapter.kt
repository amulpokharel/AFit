package amulp.com.afit.adapters

import amulp.com.afit.R
import amulp.com.afit.models.Exercise
import amulp.com.afit.models.Routine
import amulp.com.afit.utils.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_routine_dialog.view.*
import kotlinx.android.synthetic.main.exercise_item.view.*


class RecycleAdapter(var items: List<Any>, val listener: (Any) -> Unit)
    : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.exercise_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    fun setData(newData: List<Any>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Any, listener: (Any) -> Unit) = with(itemView) {
            if(item is Exercise){
                firstLine.text = item.name
                secondLine.text = item.toString()
                deleteButton.setOnClickListener { listener(item) }
            }
            else if( item is Routine){
                firstLine.text = item.name
                secondLine.text = item.toString()

                deleteButton.setOnClickListener{listener(item)}
            }
        }
    }
}