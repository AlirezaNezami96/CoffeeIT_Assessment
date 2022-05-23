package alireza.nezami.coffeeit_assessment.presentation.adapter

import alireza.nezami.coffeeit_assessment.R
import alireza.nezami.coffeeit_assessment.databinding.ItemCoffeeExtrasBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtra
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtraSub
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ExtrasAdapter(
    private val onItemClickCallback: (sub: CoffeeExtraSub, extra: CoffeeExtra) -> Unit
) : ListAdapter<CoffeeExtra, ExtrasAdapter.ParentViewHolder>(diffCallback) {

    fun onItemClick(sub: CoffeeExtraSub, extra: CoffeeExtra) = onItemClickCallback(sub, extra)

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        with(getItem(position)) {
            this?.let {
                holder.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding =
            ItemCoffeeExtrasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
            .apply {
                with(binding.root) {
                    setOnClickListener {
                        val position =
                            adapterPosition.takeIf { position -> position != RecyclerView.NO_POSITION }
                                ?: return@setOnClickListener
                        val item = getItem(position)
                        item.expanded = !item.expanded
                        notifyItemChanged(position)
                    }
                    parentLayoutResource = R.layout.item_extras_parent
                    secondLayoutResource = R.layout.item_extras_child
                }
            }
    }

    inner class ParentViewHolder(
        private val binding: ItemCoffeeExtrasBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoffeeExtra) {

            with(binding.expandableLayout) {

                if (item.expanded) expand() else collapse()

                parentLayout.findViewById<TextView>(R.id.title_header).text = item.name
                parentLayout.findViewById<ImageView>(R.id.image_coffee_machine)
                    .setImageDrawableResource(item.icon)

                val radioGroup = secondLayout.findViewById<RadioGroup>(R.id.radio_group)

                item.subs.forEachIndexed { index, extraSub ->

                    val button = radioGroup.getChildAt(index)
                    if (button is RadioButton) {
                        button.text = extraSub.name
                        button.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked)
                                onItemClick(
                                    sub = extraSub,
                                    extra = item
                                )
                        }
                    }
                }

            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CoffeeExtra>() {
            override fun areItemsTheSame(oldItem: CoffeeExtra, newItem: CoffeeExtra) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoffeeExtra, newItem: CoffeeExtra) =
                oldItem == newItem
        }
    }
}
