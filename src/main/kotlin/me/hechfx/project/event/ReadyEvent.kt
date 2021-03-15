package me.hechfx.project.event

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import me.hechfx.project.api.Event

class ReadyEvent(val client: Kord): Event {
    init {
        client.on<ReadyEvent> {
            println("${client.getSelf().tag} initialized")
        }
    }
}