package me.hechfx.project.command.vanilla.util

import dev.kord.core.behavior.channel.createMessage
import me.hechfx.project.api.TextCommand
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR

class HelpCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options(listOf("help", "ajuda", "comandos")) {
        override val description = "???"
        override val category = CommandCategory.UTIL
    }

    @ExperimentalStdlibApi
    override suspend fun run(context: CommandContext) {
        context.textChannel.createMessage {
            content = context.author.mention

            embed {
                title = "Meus comandos!"
                color = EMBED_DEFAULT_COLOR
                CommandCategory.values().forEach {
                    field {
                        name = it.categoryName
                        value = "` " + context.senichi.commandManager.commands
                            .filter { command -> command.options.category == it }
                            .joinToString(",", "`", "`") { command -> command.options.labels.first() }
                    }
                }
                thumbnail {
                    url = context.client.getSelf().avatar.url
                }
            }
        }
    }
}