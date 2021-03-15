package me.hechfx.project

import dev.kord.core.Kord
import me.hechfx.project.service.PrototypeManager
import me.hechfx.project.util.locale.SenichiConfig

class Senichi(private val config: SenichiConfig) {

    suspend fun start() {
        val client = Kord(config.token)

        PrototypeManager(client).let {
            it.loadEvents()
            it.loadCommands()
        }

        client.login()
    }
}