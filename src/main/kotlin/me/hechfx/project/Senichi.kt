package me.hechfx.project

import dev.kord.core.Kord
import dev.schlaubi.lavakord.LavaKord
import dev.schlaubi.lavakord.kord.lavakord
import kotlinx.coroutines.FlowPreview
import me.hechfx.project.service.loadCommands
import me.hechfx.project.service.loadEvents
import me.hechfx.project.util.lavakord.Lavakord
import me.hechfx.project.util.locale.SenichiConfig

lateinit var lavakord: LavaKord

class Senichi(private val config: SenichiConfig) {

    @FlowPreview
    @ExperimentalStdlibApi
    suspend fun start() {
        val client = Kord(config.token)
        lavakord = client.lavakord()
        lavakord.addNode("ws://127.0.0.1:1337", "youshallnotpass", "1")
        loadCommands()
        loadEvents(client)

        client.login()
    }
}
