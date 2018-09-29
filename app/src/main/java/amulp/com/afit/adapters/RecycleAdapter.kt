package amulp.com.afit.adapters

import amulp.com.afit.R
import amulp.com.afit.models.Exercise
import amulp.com.afit.utils.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_item.view.*


class RecycleAdapter(var items: List<Exercise>, val listener: (Exercise) -> Unit)
    : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.exercise_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    fun setData(newData: List<Exercise>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Exercise, listener: (Exercise) -> Unit) = with(itemView) {

            firstLine.text = item.name
            secondLine.text = item.toString()

            deleteButton.setOnClickListener { listener(item) }
        }
    }
}