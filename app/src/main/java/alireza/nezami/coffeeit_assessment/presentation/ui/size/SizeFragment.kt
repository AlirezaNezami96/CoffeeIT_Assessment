package alireza.nezami.coffeeit_assessment.presentation.ui.size

import alireza.nezami.coffeeit_assessment.R
import alireza.nezami.coffeeit_assessment.databinding.FragmentSizesBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeSize
import alireza.nezami.coffeeit_assessment.presentation.adapter.SizesAdapter
import alireza.nezami.coffeeit_assessment.presentation.shared.CoffeeSharedViewModel
import alireza.nezami.coffeeit_assessment.presentation.ui.style.StyleFragmentDirections
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class SizeFragment() : Fragment() {

    private lateinit var binding: FragmentSizesBinding
    private val args: SizeFragmentArgs by navArgs()
    private val sharedViewModel: CoffeeSharedViewModel by activityViewModels()

    private val adapterOnItemClickedCallback: (CoffeeSize) -> Unit = { selectedSize ->
        sharedViewModel.setSelectedSize(selectedSize)

        sharedViewModel.selectedType?.let {
            findNavController().navigate(
                SizeFragmentDirections.actionSizeFragmentToExtraFragment(
                    it
                )
            )
        }
    }

    private val adapter = SizesAdapter(adapterOnItemClickedCallback)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSizesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title = getString(R.string.select_your_size)
        setAdapter()
    }


    private fun setAdapter() {
        adapter.submitList(args.type.sizes)
        binding.listItems.adapter = adapter
    }
}