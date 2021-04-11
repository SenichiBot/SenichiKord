package me.hechfx.project.command.vanilla.misc

import dev.kord.core.behavior.edit
import me.hechfx.project.api.TextCommand
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory

class PingCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options(listOf("ping", "latency", "latencia")) {
        override val category = CommandCategory.MISCELLANEOUS
        override val description = "Ping pong!"
    }

    override suspend fun run(context: CommandContext) {
        val now = System.currentTimeMillis()
        val gatewayPing = context.client.gateway.averagePing
        val message = context.textChannel.createMessage("\uD83D\uDD39 • ${context.author.mention} API: `...` | Gateway: `${gatewayPing}`")

        message.edit {
            val diff = System.currentTimeMillis() - now
            content = "\uD83D\uDD39 • ${context.author.mention} API: `${diff}ms` | Gateway: `${gatewayPing}`"
        }
    }
}