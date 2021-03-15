package me.hechfx.project.api

import com.typesafe.config.ConfigFactory
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.entity.Message
import dev.kord.core.entity.User
import dev.kord.core.entity.channel.TextChannel
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import me.hechfx.project.util.boot.inspect
import me.hechfx.project.util.locale.SenichiConfig

class CommandContext(
    val client: Kord,
    val message: Message,
    val args: List<String>,
    val author: User,
    val textChannel: MessageChannelBehavior
) {
    suspend fun reply(content: String) {
        this.textChannel.createMessage("\uD83D\uDD39 • ${this.author.mention} $content")
    }
}