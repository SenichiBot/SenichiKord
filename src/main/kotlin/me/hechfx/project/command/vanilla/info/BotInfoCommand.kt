package me.hechfx.project.command.vanilla.info

import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.createMessage
import kotlinx.coroutines.flow.count
import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR
import me.hechfx.project.util.constant.Constants.OWNER_ID

class BotInfoCommand: Command {
    override val labels = listOf("botinfo")
    override val description = "Bot Informations"
    override val category = CommandCategory.INFO
    override val onlyDev = false
    override val debugMode = false

    override suspend fun run(context: CommandContext) {
        val owner = context.client.getUser(Snowflake(OWNER_ID))!!

        context.message.getChannel().createMessage {
            content = context.author.mention

            embed {
                title = "Olá! \uD83D\uDC4B"
                description = "Meu nome é Sen'ichi, tenho várias funções para alegrar seu servidor! Fui feito em [Kotlin](https://pt.wikipedia.org/wiki/Kotlin) usando [Kord](https://github.com/kordlib/kord)! (Uma biblioteca que ainda está em desenvolvimento porém é muito boa!)"
                color = EMBED_DEFAULT_COLOR
                thumbnail {
                    url = context.client.getSelf().avatar.url
                }
                field {
                    name = "Meu Criador"
                    value = "```${owner.tag}```"
                    inline = true
                }
                field {
                    name = "Servidores"
                    value = "```${context.client.guilds.count()}```"
                    inline = true
                }
                footer {
                    icon = "https://cdn.discordapp.com/emojis/735135307212783668.gif?v=1"
                    text = "Se você quiser me adicionar, use `>!invite`"
                }
            }
        }
    }
}