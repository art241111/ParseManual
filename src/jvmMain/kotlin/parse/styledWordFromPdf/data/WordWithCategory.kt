package parse.styledWordFromPdf.data

import data.Word

class WordWithCategory(
    override val word: String,
    val coordinate: Pair<Float, Float>,
    val category: WordCategory
) : Word {
    override fun toString(): String {
        return word
    }
}