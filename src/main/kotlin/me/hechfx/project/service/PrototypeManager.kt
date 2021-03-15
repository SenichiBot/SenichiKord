package me.hechfx.project.service

import dev.kord.core.Kord
import me.hechfx.project.api.Command
import me.hechfx.project.api.Event
import me.hechfx.project.command.vanilla.info.BotInfoCommand
import me.hechfx.project.command.vanilla.misc.DiscrimCommand
import me.hechfx.project.command.vanilla.misc.PingCommand
import me.hechfx.project.command.vanilla.util.InviteCommand
import me.hechfx.project.event.MessageEvent
import me.hechfx.project.event.ReadyEvent

class PrototypeManager(private val client: Kord) {
    fun loadCommands(): MutableList<Command> {
        val commands = mutableListOf<Command>()
        commands.add(PingCommand())
        commands.add(BotInfoCommand())
        commands.add(InviteCommand())
        commands.add(DiscrimCommand())
        return commands
    }

    fun loadEvents(): MutableList<Event> {
        val events = mutableListOf<Event>()
        events.add(MessageEvent(client))
        events.add(ReadyEvent(client))
        return events
    }
}