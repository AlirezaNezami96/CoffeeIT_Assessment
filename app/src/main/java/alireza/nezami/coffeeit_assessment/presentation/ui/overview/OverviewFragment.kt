package alireza.nezami.coffeeit_assessment.presentation.ui.overview

import alireza.nezami.coffeeit_assessment.databinding.FragmentOverviewBinding
import alireza.nezami.coffeeit_assessment.presentation.shared.CoffeeSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class OverviewFragment() : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private val sharedViewModel: CoffeeSharedViewModel by activityViewModels()
    private val overviewViewModel: OverviewViewModel by viewModels()

    private val onStyleClickedCallback: () -> Unit = {
        sharedViewModel.coffeeMachine?.let { coffeeMachine ->
            findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragmentToStyleFragment(coffeeMachine)
            )
        }
    }

    private val onSizeClickedCallback: () -> Unit = {
        sharedViewModel.selectedType?.let { selectedType ->
            findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragmentToSizeFragment(selectedType)
            )
        }
    }

    private val onExtraClickedCallback: () -> Unit = {
        sharedViewModel.selectedType?.let { selectedType ->
            findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragmentToExtraFragment(selectedType)
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = overviewViewModel
        binding.item = sharedViewModel.getOverviewItem()
        setListeners()
    }


    private fun setListeners() {
        binding.buttonFinish.setOnClickListener {
            lifecycleScope.launch {
                sharedViewModel.finishOrder()
            }
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToNFCFragment())
        }
        binding.textEditStyle.setOnClickListener {
            onStyleClickedCallback.invoke()
        }
        binding.textEditSize.setOnClickListener {
            onSizeClickedCallback.invoke()
        }

    }
}