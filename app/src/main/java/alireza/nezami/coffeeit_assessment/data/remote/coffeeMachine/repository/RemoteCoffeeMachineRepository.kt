package alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.repository

import alireza.nezami.coffeeit_assessment.data.CoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model.CoffeeMachineResponse
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeMachine
import alireza.nezami.coffeeit_assessment.utils.Result

interface RemoteCoffeeMachineRepository : CoffeeMachineRepository {

    suspend fun getCoffeeMachine(): Result<CoffeeMachineResponse>
}