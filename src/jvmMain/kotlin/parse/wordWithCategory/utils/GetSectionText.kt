package parse.wordWithCategory.utils

import parse.styledWordFromPdf.utils.SectionName
import parse.wordWithCategory.data.CommandInSection

/**
 * Selecting text from a specific section.
 *
 * @param sectionName - The name of the section whose text you want to select
 * @return text of section.
 *
 * @author Gerasimov Artem (gerasimov.av.dev@gmail.com)
 */
internal fun CommandInSection.getSectionText(sectionName: SectionName.Names): String =
    sections.filter { it.name == sectionName }
        .map { it.words.joinToString(" ").trim() }
        .getOrElse(0) { "" }