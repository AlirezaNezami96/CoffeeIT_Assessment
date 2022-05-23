package alireza.nezami.coffeeit_assessment.di

import alireza.nezami.coffeeit_assessment.data.AppDatabase
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LastChoiceDao
import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val HTTP_READ_TIMEOUT_IN_SECONDS = 60
private const val HTTP_CALL_TIMEOUT_IN_SECONDS = 60

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideLastChoiceDao(database: AppDatabase): LastChoiceDao = database.lastChoiceDao()

}