package alireza.nezami.coffeeit_assessment.presentation.adapter

import alireza.nezami.coffeeit_assessment.databinding.ItemCoffeeSizesBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeSize
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SizesAdapter(
    private val onItemClickCallback: (CoffeeSize) -> Unit
) : ListAdapter<CoffeeSize, SizesAdapter.ConnectionViewHolder>(diffCallback) {

    fun onItemClick(item: CoffeeSize) = onItemClickCallback(item)

    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {
        with(getItem(position)) {
            this?.let {
                holder.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionViewHolder {
        val binding =
            ItemCoffeeSizesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConnectionViewHolder(binding)
    }

    inner class ConnectionViewHolder(
        private val binding: ItemCoffeeSizesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoffeeSize) {
            binding.item = item
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CoffeeSize>() {
            override fun areItemsTheSame(oldItem: CoffeeSize, newItem: CoffeeSize) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoffeeSize, newItem: CoffeeSize) =
                oldItem == newItem
        }
    }
}
