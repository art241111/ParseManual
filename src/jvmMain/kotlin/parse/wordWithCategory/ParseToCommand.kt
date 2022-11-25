package parse.wordWithCategory

import data.Command
import parse.styledWordFromPdf.data.WordCategory
import parse.styledWordFromPdf.data.WordWithCategory
import parse.styledWordFromPdf.utils.SectionName
import parse.wordWithCategory.data.CommandInSection
import parse.wordWithCategory.data.Section

fun List<WordWithCategory>.parseToCommand(): List<Command> {
    val commands = mutableListOf<Command>()
    val commandsBlocks = this.toCommandInSection()

    commandsBlocks.forEach { commandBlocks ->
        val fullName = commandBlocks.name.joinToString("")

        commands.add(
            Command(
                name = fullName.split(" ")[0].trim(),
                fullName = fullName.trim(),
                function = commandBlocks.getSectionText(SectionName.Names.FUNCTION),
                parametersDescription = commandBlocks.getSectionText(SectionName.Names.PARAMETER)
                        + commandBlocks.getSectionText(SectionName.Names.PARAMETERS),
                explanation = commandBlocks.getSectionText(SectionName.Names.EXPLANATION),
                example = commandBlocks.getSectionText(SectionName.Names.EXAMPLE),
                false
            )
        )
    }
    return commands
}

private fun CommandInSection.getSectionText(sectionName: SectionName.Names): String =
    sections.filter { it.name == sectionName }
        .map { it.words.joinToString(" ").trim() }
        .getOrElse(0) { "" }

private fun List<WordWithCategory>.toCommandInSection(): MutableList<CommandInSection> {
    val wordsCommand = mutableListOf<WordWithCategory>()
    val sections = mutableListOf<Section>()
    val commandsInSection = mutableListOf<CommandInSection>()
    val name = mutableListOf<WordWithCategory>()
    var sectionName = ""
    this.forEach { word ->
        if (
            word.category == WordCategory.COMMAND_NAME &&
            wordsCommand.last().category == WordCategory.TEXT &&
            wordsCommand.filter { it.category == WordCategory.TEXT }.size > 3
        ) {
            if (sectionName != "") {
                sections.add(
                    Section(
                        name = SectionName.getNames(sectionName),
                        words = wordsCommand.toMutableList() // toMutableList() - to assign a value, not a link)
                    )
                )
            }
            commandsInSection.add(
                CommandInSection(
                    name = name.toMutableList(),
                    sections = sections.toMutableList()
                )
            )
            wordsCommand.clear()
            name.clear()
            sections.clear()
            sectionName = ""
        }
        if (word.category == WordCategory.COMMAND_NAME) {
            name.add(word)
        }
        if (
            word.category == WordCategory.SECTION_NAME &&
            wordsCommand.last().category != WordCategory.SECTION_NAME
        ) {

            sectionName = if (sectionName == "") {
                word.word
            } else {
                val words = wordsCommand.toMutableList() // toMutableList() - to assign a value, not a link)
                words.removeFirstOrNull()
                sections.add(
                    Section(
                        name = SectionName.getNames(sectionName),
                        words = words
                    )
                )
                word.word
            }
            wordsCommand.clear()
        }

        wordsCommand.add(word)
    }

    if (sections.isNotEmpty()) {
        commandsInSection.add(
            CommandInSection(
                name = name.toMutableList(),
                sections = sections.toMutableList()
            )
        )
    }

    commandsInSection.removeAt(0)
    return commandsInSection
}
