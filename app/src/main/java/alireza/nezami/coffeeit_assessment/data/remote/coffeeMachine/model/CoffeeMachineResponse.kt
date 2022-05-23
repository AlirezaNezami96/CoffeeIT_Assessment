package alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model


import alireza.nezami.coffeeit_assessment.R
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtra
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeMachine
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeSize
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeTypes
import alireza.nezami.coffeeit_assessment.utils.dto.toDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoffeeMachineResponse(
    @Json(name = "_id")
    val id: String,
    @Json(name = "types")
    val types: List<Type>,
    @Json(name = "sizes")
    val sizes: List<Size>,
    @Json(name = "extras")
    val extras: List<Extra>
) {


    fun toDomainModel(): CoffeeMachine {
        val coffeeTypes: MutableList<CoffeeTypes> = mutableListOf()
        with(this) {

            val sizeMap: HashMap<String, CoffeeSize> = HashMap()
            sizes.forEach { size ->
                sizeMap[size.id] = CoffeeSize(
                    id = size.id,
                    name = size.name,
                    icon = coffeeSizeIconDecider(size.name)
                )
            }

            val extraMap: HashMap<String, CoffeeExtra> =
                HashMap()
            extras.forEach { extra ->
                extraMap[extra.id] =
                    CoffeeExtra(
                        extra.id,
                        extra.name,
                        icon = coffeeExtraIconDecider(extra.name),
                        extra.subSelections.toDomainModel()
                    )
            }

            types.forEach { type ->
                val sizes = type.sizes.map {
                    sizeMap[it]!!
                }
                val extras = type.extras.map {
                    extraMap[it]!!
                }
                coffeeTypes.add(
                    CoffeeTypes(
                        id = type.id,
                        name = type.name,
                        icon = coffeeTypeIconDecider(type.name),
                        sizes = sizes,
                        extra = extras
                    )
                )
            }
        }
        return CoffeeMachine(coffeeTypes)
    }

    private fun coffeeSizeIconDecider(name: String): Int? {
        return when (name) {
            "Large" -> R.drawable.ic_large
            "Tall" -> R.drawable.ic_medium
            "Venti" -> R.drawable.ic_small
            else -> null
        }
    }

    private fun coffeeTypeIconDecider(name: String): Int? {
        return when (name) {
            "Espresso" -> R.drawable.ic_espresso
            "Cappuccino" -> R.drawable.ic_cappuchino
            "Lungo" -> R.drawable.ic_medium
            else -> null
        }
    }

    private fun coffeeExtraIconDecider(name: String): Int? {
        return when (name) {
            "Select the amount of sugar" -> R.drawable.ic_cappuchino
            "Select type of milk" -> R.drawable.ic_milk
            else -> null
        }
    }
}

@JsonClass(generateAdapter = true)
data class Extra(
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "subselections")
    val subSelections: List<SubSelection>
)

@JsonClass(generateAdapter = true)
data class Size(
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class SubSelection(
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String
)


@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "sizes")
    val sizes: List<String>,
    @Json(name = "extras")
    val extras: List<String>
)