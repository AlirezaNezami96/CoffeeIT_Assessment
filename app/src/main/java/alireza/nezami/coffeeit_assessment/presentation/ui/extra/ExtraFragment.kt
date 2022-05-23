package alireza.nezami.coffeeit_assessment.presentation.ui.extra

import alireza.nezami.coffeeit_assessment.R
import alireza.nezami.coffeeit_assessment.databinding.FragmentExtrasBinding
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtra
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtraSub
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import alireza.nezami.coffeeit_assessment.presentation.adapter.ExtrasAdapter
import alireza.nezami.coffeeit_assessment.presentation.shared.CoffeeSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ExtraFragment() : Fragment() {
    private lateinit var binding: FragmentExtrasBinding
    private val args: ExtraFragmentArgs by navArgs()
    private val sharedViewModel: CoffeeSharedViewModel by activityViewModels()

    private val adapterOnItemClickedCallback: (sub: CoffeeExtraSub, extra: CoffeeExtra) -> Unit =
        { sub, extra ->
            sharedViewModel.addSelectedExtraSub(extra, sub)
        }

    private val adapter = ExtrasAdapter(adapterOnItemClickedCallback)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExtrasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title = getString(R.string.select_your_extra)
        setAdapter()
        setListeners()
    }


    private fun setAdapter() {
        adapter.submitList(args.type.extra)
        binding.listItems.adapter = adapter
    }

    private fun setListeners() {
        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(ExtraFragmentDirections.actionExtraFragmentToOverviewFragment())
        }
    }
}