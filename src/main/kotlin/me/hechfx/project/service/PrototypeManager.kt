package me.hechfx.project.service

import dev.kord.core.Kord
import kotlinx.coroutines.Job
import me.hechfx.project.api.Command
import me.hechfx.project.command.vanilla.info.*
import me.hechfx.project.command.vanilla.misc.*
import me.hechfx.project.command.vanilla.util.*
import me.hechfx.project.event.MessageReceivedListener
import me.hechfx.project.event.ReadyAnnounceListener

class PrototypeManager(private val client: Kord) {
    fun loadCommands(): MutableList<Command> {
        val commands = mutableListOf<Command>()
        commands.add(PingCommand())
        commands.add(BotInfoCommand())
        commands.add(InviteCommand())
        commands.add(DiscrimCommand())
        commands.add(HelpCommand())
        return commands
    }

    fun loadEvents(): MutableList<Job> {
        val events = mutableListOf<Job>()
        events.add(MessageReceivedListener(client).init())
        events.add(ReadyAnnounceListener(client).init())
        return events
    }
}