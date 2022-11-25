package parse.wordWithCategory.data

import parse.styledWordFromPdf.data.WordWithCategory

class CommandInSection(
    val name: List<WordWithCategory>,
    val sections: List<Section>
)