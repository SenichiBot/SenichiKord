package me.hechfx.project.api

import dev.kord.core.Kord
import kotlinx.coroutines.Job

interface Event {
    val client: Kord
    fun init(): Job
}