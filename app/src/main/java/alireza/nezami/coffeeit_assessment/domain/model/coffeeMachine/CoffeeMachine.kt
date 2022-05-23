package alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoffeeMachine(
    val types: List<CoffeeTypes>
) : Parcelable

@Parcelize
data class CoffeeExtra(
    val id: String,
    val name: String,
    val icon: Int? = null,
    val subs: List<CoffeeExtraSub>,
    var expanded: Boolean = false

) : Parcelable

@Parcelize
data class CoffeeExtraSub(
    val id: String,
    val name: String,
) : Parcelable

@Parcelize
data class CoffeeTypes(
    val id: String,
    val name: String,
    val icon: Int? = null,
    val sizes: List<CoffeeSize>,
    val extra: List<CoffeeExtra>,
) : Parcelable

@Parcelize
data class CoffeeSize(
    val id: String,
    val name: String,
    val icon: Int? = null
) : Parcelable