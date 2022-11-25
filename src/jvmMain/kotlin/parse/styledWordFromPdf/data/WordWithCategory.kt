package parse.styledWordFromPdf.data

import data.Word

data class WordWithCategory(
    override val word: String,
    val coordinate: Pair<Float, Float>,
    val category: WordCategory
) : Word