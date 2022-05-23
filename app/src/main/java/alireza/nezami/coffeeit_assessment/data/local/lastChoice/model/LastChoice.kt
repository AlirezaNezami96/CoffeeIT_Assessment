package alireza.nezami.coffeeit_assessment.data.local.lastChoice.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(
    tableName = "last_choice",
)
data class LastChoice(

    // it should be only one row
    @PrimaryKey val id: Int = 1,

    val isComplete: Boolean = false,
    val step: Int = ORDER_STEP_STYLE,
    val selectedType: String,
    val selectedSize: String,
    val firstSelectedExtra: String? = null,
    val secondSelectedExtra: String? = null
) {
    companion object {
        const val ORDER_STEP_STYLE = 1
        const val ORDER_STEP_SIZE = 2
        const val ORDER_STEP_EXTRA = 3
        const val ORDER_STEP_OVERVIEW = 4
    }
}