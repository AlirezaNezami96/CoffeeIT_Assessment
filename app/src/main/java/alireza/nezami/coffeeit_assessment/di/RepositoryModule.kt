package alireza.nezami.coffeeit_assessment.di

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LastChoiceDao
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LocalCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.repository.RemoteCoffeeMachineRepository
import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.service.CoffeeMachineService
import alireza.nezami.coffeeit_assessment.domain.local.repository.LocalCoffeeMachineRepositoryImpl
import alireza.nezami.coffeeit_assessment.domain.remote.repository.RemoteCoffeeMachineRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, AppModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteCoffeeMachineRepository(
        coffeeMachineService: CoffeeMachineService,
        dispatcher: CoroutineDispatcher
    ): RemoteCoffeeMachineRepository =
        RemoteCoffeeMachineRepositoryImpl(
            coffeeMachineService,
            dispatcher
        )

    @Singleton
    @Provides
    fun provideLocalCoffeeMachineRepository(
        lastChoiceDao: LastChoiceDao,
        dispatcher: CoroutineDispatcher,
        moshi: Moshi
    ): LocalCoffeeMachineRepository =
        LocalCoffeeMachineRepositoryImpl(
            lastChoiceDao,
            dispatcher,
            moshi
        )

}