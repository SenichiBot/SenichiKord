package me.hechfx.project.command.vanilla.misc

import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory

class DiscrimCommand: Command {
    override val labels = listOf("discrim", "discriminator")
    override val category = CommandCategory.MISCELLANEOUS
    override val description = "???"
    override val debugMode = true
    override val onlyDev = false

    override suspend fun run(context: CommandContext) {

    }
}