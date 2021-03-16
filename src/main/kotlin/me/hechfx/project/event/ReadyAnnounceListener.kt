package me.hechfx.project.event

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import kotlinx.coroutines.Job
import me.hechfx.project.api.Event

inline class ReadyAnnounceListener(override val client: Kord): Event {
    override fun init(): Job = client.on<ReadyEvent> {
        println("${this.self.tag} has started!")
    }
}
