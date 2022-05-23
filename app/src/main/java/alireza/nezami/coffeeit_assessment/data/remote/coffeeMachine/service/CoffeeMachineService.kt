package alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.service

import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model.CoffeeMachineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeMachineService {

    @GET("/coffee-machine/{id}")
    suspend fun getCoffeeMachine(
        @Path("id") id: String
    ): CoffeeMachineResponse
}