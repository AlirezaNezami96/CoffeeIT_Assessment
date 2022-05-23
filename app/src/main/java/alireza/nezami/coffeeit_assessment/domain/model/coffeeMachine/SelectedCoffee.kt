package alireza.nezami.coffeeit_assessment.domain.model.coffeeMachine

data class SelectedCoffee(
    val selectedType: CoffeeTypes?,
    val selectedSize: CoffeeSize?,
    val firstSelectedExtra: SelectedCoffeeExtra?,
    val secondSelectedExtra: SelectedCoffeeExtra?,
)

data class SelectedCoffeeExtra(
    val sub: CoffeeExtraSub,
    val extra: CoffeeExtra
)
