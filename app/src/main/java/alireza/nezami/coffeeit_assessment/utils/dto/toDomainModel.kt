package alireza.nezami.coffeeit_assessment.utils.dto

import alireza.nezami.coffeeit_assessment.data.remote.coffeeMachine.model.SubSelection
import alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine.CoffeeExtraSub

fun List<SubSelection>.toDomainModel(): List<CoffeeExtraSub> {
    return this.map { CoffeeExtraSub(it.id, it.name) }
}
