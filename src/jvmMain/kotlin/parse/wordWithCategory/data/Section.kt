package parse.wordWithCategory.data

import parse.styledWordFromPdf.data.WordWithCategory
import parse.styledWordFromPdf.utils.SectionName

class Section(
    val name: SectionName.Names,
    val words: MutableList<WordWithCategory>
)

