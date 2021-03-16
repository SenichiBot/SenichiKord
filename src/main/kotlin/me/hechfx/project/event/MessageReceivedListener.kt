package me.hechfx.project.event

import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.Job
import me.hechfx.project.api.CommandContext
import me.hechfx.project.api.Event
import me.hechfx.project.service.PrototypeManager
import me.hechfx.project.util.constant.Constants.getConfig

inline class MessageReceivedListener(override val client: Kord): Event {
    override fun init(): Job = client.on<MessageCreateEvent> {
        if (this.message.author?.isBot == true) return@on

        val prefix = getConfig().prefix

        if (!this.message.content.startsWith(prefix)) return@on

        val contentRaw = this.message.content.substring(prefix.length, this.message.content.length).trim().split(Regex(" "))

        val cmd = contentRaw[0]

        val args = this.message.content.substring(prefix.length, this.message.content.length).trim().split(" ").drop(1)

        val context = CommandContext(
            client,
            this.message,
            args,
            this.message.author!!,
            this.message.channel
        )

        val prototypeMgr = PrototypeManager(client)
        val commands = prototypeMgr.loadCommands()

        commands.forEach {
            if (it.labels.contains(cmd)) {
                if (it.debugMode) {
                    context.reply("Este comando está em manutenção!")
                    return@forEach
                }
                if (it.onlyDev) {
                    context.reply("Somente minha equipe de desenvolvimento pode usar este comando!")
                    return@forEach
                }
                it.run(context)
            }
        }
    }
}