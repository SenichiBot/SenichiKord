package me.hechfx.project.command.vanilla.misc

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.User
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import me.hechfx.project.api.TextCommand
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR

class DiscrimCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options(listOf("discrim", "discriminator")) {
        override val category = CommandCategory.MISCELLANEOUS
        override val description = "???"
    }

    @FlowPreview
    override suspend fun run(context: CommandContext) {
        val allUsers: Flow<User> = context.client.guilds.map { it.members }.flattenMerge()
        val discriminator = if (context.args.isEmpty()) context.author.discriminator else context.args[0]

        val users = allUsers.filter { discriminator == it.discriminator }
        val mappedUsers = users.map { it.username + "#" + it.discriminator}
        val listedUsers = mappedUsers.toList()
        val usersString = listedUsers.joinToString("\n")

        if (listedUsers.isEmpty()) {
            return context.reply("Aparentemente nenhum usuário que eu conheça tem $discriminator como discriminator!")
        }

        context.textChannel.createMessage {
            content = context.author.mention

            embed {
                title = "Usuários com discriminator: $discriminator"
                description = usersString
                color = EMBED_DEFAULT_COLOR
            }
        }
    }
}