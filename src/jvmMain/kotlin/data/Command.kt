package data

data class Command(
    val name: String,
    val fullName: String,
//    val superstructure: List<Superstructure> = listOf(),
//    val parameters: List<Parameter> = listOf(),
//    val assignable: List<Parameter> = listOf(),
    val function: String = "",
    val parametersDescription: String = "",
    val explanation: String = "",
    val example: String = "",
    val isMultiParameters: Boolean = false
)

