package me.hechfx.project.service

import dev.kord.core.Kord
import kotlinx.coroutines.FlowPreview
import me.hechfx.project.command.vanilla.info.*
import me.hechfx.project.command.vanilla.misc.*
import me.hechfx.project.command.vanilla.music.*
import me.hechfx.project.command.vanilla.util.*
import me.hechfx.project.event.*

@ExperimentalStdlibApi
fun loadCommands() = buildList {
    add(PingCommand())
    add(BotInfoCommand())
    add(InviteCommand())
    add(HelpCommand())
    add(PlayCommand())
    add(ConnectCommand())
}

@FlowPreview
@ExperimentalStdlibApi
fun loadEvents(client: Kord) = buildList {
    add(MessageReceivedListener(client).init())
    add(ReadyAnnounceListener(client).init())
}
