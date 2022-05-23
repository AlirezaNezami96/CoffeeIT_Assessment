package alireza.nezami.coffeeit_assessment.presentation.ui.style

import alireza.nezami.coffeeit_assessment.R
import alireza.nezami.coffeeit_assessment.databinding.FragmentStylesBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import alireza.nezami.coffeeit_assessment.presentation.adapter.TypesAdapter
import alireza.nezami.coffeeit_assessment.presentation.shared.CoffeeSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StyleFragment() : Fragment() {

    private val sharedViewModel: CoffeeSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentStylesBinding
    private val args: StyleFragmentArgs by navArgs()

    private val adapterOnItemClickedCallback: (CoffeeTypes) -> Unit = { selectedType ->
        sharedViewModel.setSelectedType(selectedType)

        findNavController().navigate(
            StyleFragmentDirections.actionStyleFragmentToSizeFragment(selectedType)
        )
    }

    private val typesAdapter = TypesAdapter(adapterOnItemClickedCallback)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStylesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title = getString(R.string.select_your_style)
        sharedViewModel.setCoffeeMachine(args.coffee)
        setAdapter()
    }


    private fun setAdapter() {
        typesAdapter.submitList(args.coffee.types)
        binding.listItems.adapter = typesAdapter
    }
}