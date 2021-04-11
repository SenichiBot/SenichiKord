package me.hechfx.project

import dev.kord.core.Kord
import dev.schlaubi.lavakord.LavaKord
import dev.schlaubi.lavakord.kord.lavakord
import me.hechfx.project.command.handler.CommandHandler
import me.hechfx.project.command.handler.CommandManager
import me.hechfx.project.command.handler.CommandService
import me.hechfx.project.util.locale.SenichiConfig

class Senichi(internal val config: SenichiConfig) {
    lateinit var client: Kord
    lateinit var lavakord: LavaKord

    val commandManager: CommandManager = CommandManager()
    val commandHandler: CommandHandler = CommandHandler(this)

    suspend fun start() {
        client = Kord(config.token)
        lavakord = client.lavakord()
        lavakord.addNode("ws://127.0.0.1:1337", "youshallnotpass", "1")

        CommandService(commandManager).registerCommands()
        commandHandler.startListening()

        client.login()
    }
}