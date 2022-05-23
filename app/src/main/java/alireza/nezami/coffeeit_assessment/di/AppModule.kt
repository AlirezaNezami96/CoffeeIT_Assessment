package alireza.nezami.coffeeit_assessment.di

import alireza.nezami.coffeeit_assessment.MyApp
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideApplication(): Application {
//        return  MyApp()
//    }

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context): Context {
        return app as MyApp
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher =  Dispatchers.IO

}