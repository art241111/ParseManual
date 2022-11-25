package parse.wordWithCategory

import data.Command
import parse.styledWordFromPdf.data.WordWithCategory
import parse.styledWordFromPdf.utils.SectionName
import parse.wordWithCategory.utils.getSectionText
import parse.wordWithCategory.utils.toCommandInSection

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
                explanation = commandBlocks.getSectionText(SectionName.Names.EXPLANATION)
                        + commandBlocks.getSectionText(SectionName.Names.EXPLANATIONS),
                example = commandBlocks.getSectionText(SectionName.Names.EXAMPLE),
                false
            )
        )
    }

    return commands.filter { it.name.first().isDefined() || !it.name.first().isDigit() } // TODO: Не работает фильтрация
}
