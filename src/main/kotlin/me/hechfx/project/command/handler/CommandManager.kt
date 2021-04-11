package me.hechfx.project.command.handler

import me.hechfx.project.api.TextCommand

class CommandManager {
    val commands = hashSetOf<TextCommand>()

    fun add(command: TextCommand) =
        commands.add(command)

    operator fun get(label: String) =
        commands.firstOrNull { it.options.labels.contains(label.toLowerCase()) }
}