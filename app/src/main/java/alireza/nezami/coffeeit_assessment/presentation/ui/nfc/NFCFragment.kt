package alireza.nezami.coffeeit_assessment.presentation.ui.nfc

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import alireza.nezami.coffeeit_assessment.databinding.FragmentNfcBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NFCFragment() : Fragment() {

    private lateinit var binding: FragmentNfcBinding
    private val viewModel: NFCViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNfcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCoffeeMachineFromServer()
        viewModel.getLastChoiceFromDb()
    }

    private fun setListeners() {
//        binding.buttonLastChoice.setOnClickListener {
//            if (viewModel.coffeeMachineData.value == null) viewModel.getCoffeeMachineFromServer()
//            else {
//                with(viewModel.lastChoice.value) {
//                    when (this?.step) {
//                        LastChoice.ORDER_STEP_STYLE -> {
//                            findNavController().navigate(
//                                NFCFragmentDirections.actionNFCFragmentToStyleFragment(viewModel.coffeeMachineData.value!!)
//                            )
//                        }
//                        LastChoice.ORDER_STEP_SIZE -> {
//                            findNavController().navigate(
//                                NFCFragmentDirections.actionNFCFragmentToSizeFragment(
//
//                                )
//                            )
//                        }
//                        LastChoice.ORDER_STEP_EXTRA -> {
//                            findNavController().navigate(
//                                NFCFragmentDirections.actionNFCFragmentToExtraFragment()
//                            )
//                        }
//                        LastChoice.ORDER_STEP_OVERVIEW -> {
//                            findNavController().navigate(
//                                NFCFragmentDirections.actionNFCFragmentToOverviewFragment()
//                            )
//                        }
//                        else -> {
//                            findNavController().navigate(
//                                NFCFragmentDirections.actionNFCFragmentToStyleFragment(viewModel.coffeeMachineData.value!!)
//                            )
//                        }
//
//                    }
//
//                }
//            }
//        }

        binding.buttonCreateNew.setOnClickListener {
            viewModel.coffeeMachineData.observe(viewLifecycleOwner) {
                it?.let { coffeeMachine ->
                    findNavController().navigate(
                        NFCFragmentDirections.actionNFCFragmentToStyleFragment(coffeeMachine)
                    )
                } ?: viewModel.getCoffeeMachineFromServer()
            }
        }
    }

}