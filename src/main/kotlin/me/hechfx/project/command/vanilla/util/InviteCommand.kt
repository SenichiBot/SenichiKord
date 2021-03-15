package me.hechfx.project.command.vanilla.util

import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.behavior.channel.createMessage
import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR

class InviteCommand: Command {
    override val labels = listOf("invite", "convidar", "adicionar")
    override val category = CommandCategory.UTIL
    override val description = "???"
    override val onlyDev = false
    override val debugMode = false

    override suspend fun run(context: CommandContext) {
        context.textChannel.createMessage {
            content = context.author.mention

            embed {
                title = "Obrigado por querer me adicionar!"
                description = "[Clique aqui](https://discord.com/api/oauth2/authorize?client_id=758128536908988436&permissions=37080128&scope=bot) para poder me adicionar no servidor que quiser! (Você não tem ideia de como isso me ajuda \uD83D\uDE47)"
                color = EMBED_DEFAULT_COLOR
                thumbnail {
                    url = context.client.getSelf().avatar.url
                }
            }
        }
    }
}