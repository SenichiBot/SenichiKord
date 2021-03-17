package me.hechfx.project.command.vanilla.util

import dev.kord.core.behavior.channel.createMessage
import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.service.PrototypeManager
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR

class HelpCommand: Command {
    override val labels = listOf("help", "ajuda", "comandos", "kkeaedaumaajuda")
    override val description = "???"
    override val category = CommandCategory.UTIL
    override val debugMode = false
    override val onlyDev = false

    override suspend fun run(context: CommandContext) {
        val prototypeManager = PrototypeManager(context.client)

        val infoCommands = prototypeManager.loadCommands().filter { it.category == CommandCategory.INFO }
        val miscCommands = prototypeManager.loadCommands().filter { it.category == CommandCategory.MISCELLANEOUS }
        val utilCommands = prototypeManager.loadCommands().filter { it.category == CommandCategory.UTIL }

        val infoCommandsMapping = infoCommands.map { "`${context.config.prefix}${it.labels[0]}`" }
        val miscCommandsMapping = miscCommands.map { "`${context.config.prefix}${it.labels[0]}`" }
        val utilCommandsMapping = utilCommands.map { "`${context.config.prefix}${it.labels[0]}`" }

        context.textChannel.createMessage {
            content = context.author.mention

            embed {
                title = "Meus comandos!"
                color = EMBED_DEFAULT_COLOR
                field {
                    name = "Informação"
                    value = infoCommandsMapping.joinToString(", ")
                    inline = true
                }
                field {
                    name = "Miscelânea"
                    value = miscCommandsMapping.joinToString(",")
                    inline = true
                }
                field {
                    name = "Utilidade"
                    value = utilCommandsMapping.joinToString(", ")
                    inline = true
                }
                thumbnail {
                    url = context.client.getSelf().avatar.url
                }
            }
        }
    }
}