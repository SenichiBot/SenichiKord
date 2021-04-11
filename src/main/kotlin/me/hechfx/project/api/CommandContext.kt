package me.hechfx.project.api

import dev.kord.core.Kord
import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.Message
import dev.kord.core.entity.User
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.map
import me.hechfx.project.Senichi
import me.hechfx.project.util.locale.SenichiConfig

@FlowPreview
class CommandContext(
    val client: Kord,
    val message: Message,
    val args: List<String>,
    val guild: Guild?,
    val author: User,
    val member: Member,
    val textChannel: MessageChannelBehavior,
    val users: Flow<Member> = client.guilds.map { it.members }.flattenMerge(),
    val config: SenichiConfig
) {
    suspend fun reply(content: String) {
        this.textChannel.createMessage("\uD83D\uDD39 • ${this.author.mention} $content")
    }
}