package parse.pdf

import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import parse.pdf.data.StyledWordFromPdf
import strateg.Strateg
import java.io.File


fun File.parseToStyledWord(): List<StyledWordFromPdf> {
    val reader = PdfReader(this.absolutePath)

    val words = mutableListOf<StyledWordFromPdf>()

    // не забываем, что нумерация страниц в PDF начинается с единицы.
    for (i in 1..reader.numberOfPages) {
        val strategy = Strateg()
        PdfTextExtractor.getTextFromPage(reader, i, strategy)
        words.addAll(strategy.words)
    }

    // убираем за собой
    reader.close()
    return words
}

