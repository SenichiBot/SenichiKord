package me.hechfx.project.api

import me.hechfx.project.util.CommandCategory

interface Command {
    val labels: List<String>
    val description: String
    val category: CommandCategory
    val onlyDev: Boolean
    val debugMode: Boolean
    suspend fun run(context: CommandContext)
}