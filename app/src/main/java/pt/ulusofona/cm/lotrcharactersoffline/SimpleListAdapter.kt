package pt.ulusofona.cm.lotrcharactersoffline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.cm.lotrcharactersoffline.databinding.ItemSimpleBinding

class SimpleListAdapter(private val items: List<String>) : RecyclerView.Adapter<SimpleListAdapter.SimpleViewHolder>() {

    class SimpleViewHolder(val binding: ItemSimpleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(
            ItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.binding.content.text = items[position]
    }

    override fun getItemCount() = items.size
}