package alireza.nezami.coffeeit_assessment.data

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.LastChoiceDao
import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [LastChoice::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lastChoiceDao(): LastChoiceDao
}