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
) {
    override fun toString(): String {
        return "/*******************************************************/" +
                "Name: $name\n" +
                "FullName: $fullName\n" +
                "function: $function\n" +
                "parametersDescription: $parametersDescription\n" +
                "explanation: $explanation\n" +
                "example: $example\n" +
                "isMultiParameters: $isMultiParameters\n" +
                "/******************************************************/"
    }
}

