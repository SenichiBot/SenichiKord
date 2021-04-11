package me.hechfx.project.command.vanilla.info

import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.User
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.map
import me.hechfx.project.api.TextCommand
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR
import me.hechfx.project.util.constant.Constants.OWNER_ID
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit

class BotInfoCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options(listOf("botinfo")) {
        override val description = "Bot Informations"
        override val category = CommandCategory.INFO
    }

    @FlowPreview
    override suspend fun run(context: CommandContext) {
        val owner = context.client.getUser(Snowflake(OWNER_ID))!!
        val runtime = Runtime.getRuntime()
        val mb = 1024 * 1024
        val jvm = ManagementFactory.getRuntimeMXBean()
        val uptime = jvm.uptime
        val days = TimeUnit.MILLISECONDS.toDays(uptime)
        val hours = TimeUnit.MILLISECONDS.toHours(uptime) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(uptime) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(uptime) % 60
        val usedRAM = (runtime.totalMemory() - runtime.freeMemory()) / mb
        val availableRAM = (runtime.totalMemory() - usedRAM) / mb
        val cores = runtime.availableProcessors()
        val users: Flow<User> = context.client.guilds.map { it.members }.flattenMerge()

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
                field {
                    name = "Usuários"
                    value = "```${users.count()}```"
                    inline = true
                }
                field {
                    name = "Used RAM / Available RAM"
                    value = "```$usedRAM MB / $availableRAM MB```"
                    inline = true
                }
                field {
                    name = "Cores"
                    value = "```$cores```"
                    inline = true
                }
                field {
                    name = "Uptime"
                    value = "```${days}d, ${hours}h, ${minutes}m, ${seconds}s```"
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