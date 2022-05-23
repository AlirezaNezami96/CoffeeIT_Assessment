package alireza.nezami.coffeeit_assessment.data.local.lastChoice

import alireza.nezami.coffeeit_assessment.data.CoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model.CoffeeMachineResponse
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeSize
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.SelectedCoffeeExtra
import alireza.nezami.coffeeit_assessment.utils.Result

interface LocalCoffeeMachineRepository : CoffeeMachineRepository {

    suspend fun getLastChoice(): LastChoice?

    suspend fun updateCoffeeType(type: CoffeeTypes?)

    suspend fun updateCoffeeSize(size: CoffeeSize?)

    suspend fun updateFirstCoffeeExtra(extra: SelectedCoffeeExtra?)

    suspend fun updateSecondCoffeeExtra(extra: SelectedCoffeeExtra?)

    suspend fun updateFinished(finished: Boolean)

    suspend fun clearLastChoice()

}