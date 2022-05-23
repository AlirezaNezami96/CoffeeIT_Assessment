package alireza.nezami.coffeeit_assessment.data.local.lastChoice

import alireza.nezami.coffeeit_assessment.data.local.lastChoice.model.LastChoice
import androidx.room.Dao
import androidx.room.Query

@Dao
interface LastChoiceDao {

    @Query("SELECT * FROM last_choice ")
    suspend fun getLastChoice(): LastChoice?

    @Query("UPDATE last_choice SET selectedType = :type, step = :step WHERE id = 1")
    suspend fun updateCoffeeType(type: String, step: Int = LastChoice.ORDER_STEP_SIZE)

    @Query("UPDATE last_choice SET selectedSize = :size, step = :step WHERE id = 1")
    suspend fun updateCoffeeSize(size: String, step: Int = LastChoice.ORDER_STEP_EXTRA)

    @Query("UPDATE last_choice SET firstSelectedExtra = :extra, step = :step WHERE id = 1")
    suspend fun updateFirstCoffeeExtra(extra: String, step: Int = LastChoice.ORDER_STEP_OVERVIEW)

    @Query("UPDATE last_choice SET secondSelectedExtra = :extra, step = :step WHERE id = 1")
    suspend fun updateSecondCoffeeExtra(extra: String, step: Int = LastChoice.ORDER_STEP_OVERVIEW)

    @Query("UPDATE last_choice SET isComplete = :finished WHERE id = 1")
    suspend fun updateFinished(finished: Boolean)

    @Query("DELETE FROM last_choice")
    fun clearTable()

}