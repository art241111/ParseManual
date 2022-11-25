package strateg

import com.itextpdf.text.pdf.parser.ImageRenderInfo
import com.itextpdf.text.pdf.parser.TextExtractionStrategy
import com.itextpdf.text.pdf.parser.TextRenderInfo
import com.itextpdf.text.pdf.parser.Vector
import parse.pdf.data.StyledWordFromPdf


class Strateg() : TextExtractionStrategy {
    var string: TextRenderInfo? = null
    private val _words: MutableList<StyledWordFromPdf> = mutableListOf()
    val words: List<StyledWordFromPdf> = _words

    private var _editWord: MutableList<StyledWordFromPdf> = mutableListOf()
    override fun beginTextBlock() {}

    // при вызове getTextFromPage эта функция вызывается при каждой команде, отображающей текст.
    // В TextRenderInfo хранится вся необходимая информация: текст, шрифт, координаты
    override fun renderText(renderInfo: TextRenderInfo?) {
// вытаскиваем координаты
        if (renderInfo != null) {
            string = renderInfo

            val x = renderInfo.baseline.startPoint[Vector.I1]
            val y = renderInfo.baseline.startPoint[Vector.I2]
            val letter = renderInfo.text
            val font = renderInfo.font

            if (letter == " ") {
                var word = ""
                _editWord.forEach { word += it.word }

                if (
                    _words.isNotEmpty() && _words.last().coordinate.second != y &&
                    word.isNotEmpty() && word.first().isUpperCase()
                ) {
                    word = "\n" + word
                }

                _words.add(
                    StyledWordFromPdf(
                        word = word + letter,
                        coordinate = if (_editWord.isNotEmpty())
                            Pair(
                                _editWord[0].coordinate.first,
                                _editWord[0].coordinate.second,
                            ) else
                            Pair(x, y),
                        style = font
                    )
                )
                _editWord = mutableListOf()
            } else {
                _editWord.add(
                    StyledWordFromPdf(
                        word = letter,
                        coordinate = Pair(x, y),
                        style = font
                    )
                )
            }

        }
    }

    override fun endTextBlock() {
    }

    override fun renderImage(renderInfo: ImageRenderInfo?) {}

    // эта функция вызывается перед окончанием getTextFromPage и ее результат вернется пользователю.
    override fun getResultantText(): String {
        var text = ""
        words.forEach {
            text += it.word + "\n"
        }
        return text
    }
}