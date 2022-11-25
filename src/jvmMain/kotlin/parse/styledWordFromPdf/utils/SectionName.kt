package parse.styledWordFromPdf.utils

import java.util.*

object SectionName {
    enum class Names {
        FUNCTION, PARAMETER, PARAMETERS, EXPLANATION, EXPLANATIONS, NOTE, EXAMPLE
    }

    fun isSectionName(text: String): Boolean =
        Names.values().map { it.name }.contains(text.trim().uppercase(Locale.getDefault()))

    fun getNames(text: String): Names = Names.valueOf(text.trim().uppercase())
}