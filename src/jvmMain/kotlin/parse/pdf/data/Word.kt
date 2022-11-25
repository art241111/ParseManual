package parse.pdf.data

import com.itextpdf.text.pdf.DocumentFont

data class Word(
    val word: String,
    val coordinate: Pair<Float, Float>,
    val style: DocumentFont
)