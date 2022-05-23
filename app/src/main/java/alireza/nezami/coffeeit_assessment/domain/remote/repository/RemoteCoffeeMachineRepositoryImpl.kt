package alireza.nezami.coffeeit_assessment.domain.remote.repository

import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model.CoffeeMachineResponse
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.repository.RemoteCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.service.CoffeeMachineService
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeMachine
import alireza.nezami.coffeeit_assessment.utils.Constants
import alireza.nezami.coffeeit_assessment.utils.Result
import alireza.nezami.coffeeit_assessment.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RemoteCoffeeMachineRepositoryImpl @Inject constructor(
    private val coffeeMachineService: CoffeeMachineService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteCoffeeMachineRepository {

    override suspend fun getCoffeeMachine(): Result<CoffeeMachineResponse> =
        safeApiCall(dispatcher) {
            coffeeMachineService.getCoffeeMachine(Constants.SAMPLE_COFFEE_MACHINE_ID)
        }
}