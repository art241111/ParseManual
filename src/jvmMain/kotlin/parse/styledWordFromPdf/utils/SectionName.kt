package parse.styledWordFromPdf.utils

import java.util.*

object SectionName {
    private val parametersName = mutableListOf<String>(
        "function",
        "parameter",
        "explanation",
        "example",
        "note"
    )

    fun isSectionName(text: String): Boolean =
        parametersName.contains(text.trim().lowercase(Locale.getDefault()))
}