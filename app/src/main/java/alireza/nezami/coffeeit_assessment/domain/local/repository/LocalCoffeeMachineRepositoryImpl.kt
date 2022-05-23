package alireza.nezami.coffeeit_assessment.domain.local.repository

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LastChoiceDao
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LocalCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeSize
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.SelectedCoffeeExtra
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalCoffeeMachineRepositoryImpl @Inject constructor(
    private val lastChoiceDao: LastChoiceDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val moshi: Moshi
) : LocalCoffeeMachineRepository {

    override suspend fun getLastChoice(): LastChoice? {
        return withContext(dispatcher) {
            lastChoiceDao.getLastChoice()
        }
    }

    override suspend fun updateCoffeeType(type: CoffeeTypes?) {
        withContext(dispatcher) {
            moshi.adapter(CoffeeTypes::class.java).apply {
                lastChoiceDao.updateCoffeeType(toJson(type))
            }
        }
    }

    override suspend fun updateCoffeeSize(size: CoffeeSize?) {
        withContext(dispatcher) {
            moshi.adapter(CoffeeSize::class.java).apply {
                lastChoiceDao.updateCoffeeSize(toJson(size))
            }
        }
    }

    override suspend fun updateFirstCoffeeExtra(extra: SelectedCoffeeExtra?) {
        withContext(dispatcher) {
            moshi.adapter(SelectedCoffeeExtra::class.java).apply {
                lastChoiceDao.updateFirstCoffeeExtra(toJson(extra))
            }
        }
    }

    override suspend fun updateSecondCoffeeExtra(extra: SelectedCoffeeExtra?) {
        withContext(dispatcher) {
            moshi.adapter(SelectedCoffeeExtra::class.java).apply {
                lastChoiceDao.updateSecondCoffeeExtra(toJson(extra))
            }
        }
    }

    override suspend fun updateFinished(finished: Boolean) {
        withContext(dispatcher) {
            lastChoiceDao.updateFinished(finished)
        }
    }

    override suspend fun clearLastChoice() {
        withContext(dispatcher) {
            lastChoiceDao.clearTable()
        }
    }
}