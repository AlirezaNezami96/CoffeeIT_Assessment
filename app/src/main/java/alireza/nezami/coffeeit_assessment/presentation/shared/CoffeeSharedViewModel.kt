package alireza.nezami.coffeeit_assessment.presentation.shared

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LocalCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.*
import alireza.nezami.coffeeit_assessment.presentation.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeSharedViewModel
@Inject constructor(
    private val localCoffeeMachineRepository: LocalCoffeeMachineRepository,
) : BaseViewModel() {

    var coffeeMachine: CoffeeMachine? = null
        private set

    var selectedType: CoffeeTypes? = null
        private set

    var selectedSize: CoffeeSize? = null
        private set

    var firstSelectedExtraSub: SelectedCoffeeExtra? = null
        private set

    var secondSelectedExtraSub: SelectedCoffeeExtra? = null
        private set


    fun setSelectedType(type: CoffeeTypes) {
        selectedType = type
        viewModelScope.launch {
            localCoffeeMachineRepository.updateCoffeeType(selectedType)
        }
    }

    fun setSelectedSize(size: CoffeeSize) {
        selectedSize = size
        viewModelScope.launch {
            localCoffeeMachineRepository.updateCoffeeSize(selectedSize)
        }
    }

    fun addSelectedExtraSub(extra: CoffeeExtra, sub: CoffeeExtraSub) {
        viewModelScope.launch {
            if (firstSelectedExtraSub == null || firstSelectedExtraSub?.extra?.id == extra.id) {
                firstSelectedExtraSub = SelectedCoffeeExtra(sub, extra)
                localCoffeeMachineRepository.updateFirstCoffeeExtra(firstSelectedExtraSub)
            } else {
                secondSelectedExtraSub = SelectedCoffeeExtra(sub, extra)
                localCoffeeMachineRepository.updateSecondCoffeeExtra(secondSelectedExtraSub)
            }
        }
    }

    fun setCoffeeMachine(coffee: CoffeeMachine) {
        coffeeMachine = coffee
    }

    fun getOverviewItem(): SelectedCoffee =
        SelectedCoffee(
            selectedType = selectedType,
            selectedSize = selectedSize,
            firstSelectedExtra = firstSelectedExtraSub,
            secondSelectedExtra = secondSelectedExtraSub
        )

   suspend fun finishOrder() {
        localCoffeeMachineRepository.updateFinished(true)
    }


}