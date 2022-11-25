package parse.styledWordFromPdf

import parse.pdf.data.StyledWordFromPdf
import parse.styledWordFromPdf.data.WordCategory
import parse.styledWordFromPdf.data.WordWithCategory
import parse.styledWordFromPdf.utils.SectionName

fun List<StyledWordFromPdf>.parseToWordWithCategory(): List<WordWithCategory> =
    map { styledWord ->
        // If text not Bold
        if (!styledWord.style.fullFontName[0][3].contains("Bold")) {
            WordWithCategory(
                word = styledWord.word,
                coordinate = styledWord.coordinate,
                category = WordCategory.TEXT
            )
        } else {
            // If text is section name
            if (SectionName.isSectionName(styledWord.word)) {
                WordWithCategory(
                    word = styledWord.word,
                    coordinate = styledWord.coordinate,
                    category = WordCategory.PARAMETERS_NAME
                )
            } else { // Else it is a command name
                WordWithCategory(
                    word = styledWord.word,
                    coordinate = styledWord.coordinate,
                    category = WordCategory.COMMAND_NAME
                )
            }
        }
    }

