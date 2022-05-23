package alireza.nezami.coffeeit_assessment.presentation.adapter

import alireza.nezami.coffeeit_assessment.databinding.ItemCoffeeTypesBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TypesAdapter(
    private val onItemClickCallback: (CoffeeTypes) -> Unit
) : ListAdapter<CoffeeTypes, TypesAdapter.ConnectionViewHolder>(diffCallback) {

    fun onItemClick(item: CoffeeTypes) = onItemClickCallback(item)

    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {
        with(getItem(position)) {
            this?.let {
                holder.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionViewHolder {
        val binding =
            ItemCoffeeTypesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConnectionViewHolder(binding)
    }

    inner class ConnectionViewHolder(
        private val binding: ItemCoffeeTypesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoffeeTypes) {
            binding.item = item
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CoffeeTypes>() {
            override fun areItemsTheSame(oldItem: CoffeeTypes, newItem: CoffeeTypes) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoffeeTypes, newItem: CoffeeTypes) =
                oldItem == newItem
        }
    }
}
