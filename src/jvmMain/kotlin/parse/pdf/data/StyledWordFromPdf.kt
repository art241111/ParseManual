package parse.pdf.data

import com.itextpdf.text.pdf.DocumentFont
import data.Word

data class StyledWordFromPdf(
    override val word: String,
    val coordinate: Pair<Float, Float>,
    val style: DocumentFont
) : Word