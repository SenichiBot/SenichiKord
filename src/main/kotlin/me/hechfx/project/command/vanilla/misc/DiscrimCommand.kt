package me.hechfx.project.command.vanilla.misc

import dev.kord.core.behavior.channel.createMessage
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.constant.Constants.EMBED_DEFAULT_COLOR

class DiscrimCommand: Command {
    override val labels = listOf("discrim", "discriminator")
    override val category = CommandCategory.MISCELLANEOUS
    override val description = "???"
    override val debugMode = false
    override val onlyDev = false

    override suspend fun run(context: CommandContext) {

        if (context.args.isEmpty()) {
            val discrim = context.author.discriminator
            val users = context.users.filter { discrim == it.discriminator }
            val mappedUsers = users.map { it.username + "#" + it.discriminator}
            val listedUsers = mappedUsers.toList()
            val usersString = listedUsers.joinToString("\n")

            context.textChannel.createMessage {
                content = context.author.mention

                embed {
                    title = "Usuários com discriminator: $discrim"
                    description = usersString
                    color = EMBED_DEFAULT_COLOR
                }
            }
        } else if (context.args.isNotEmpty()){
            val discrim = context.args[0]
            val users = context.users.filter { discrim == it.discriminator }
            val mappedUsers = users.map { it.username + "#" + it.discriminator}
            val listedUsers = mappedUsers.toList()
            val usersString = listedUsers.joinToString("\n")

            if (listedUsers.isEmpty()) {
                return context.reply("Aparentemente nenhum usuário que eu conheça tem ${discrim} como discriminator!")
            }

            context.textChannel.createMessage {
                content = context.author.mention

                embed {
                    title = "Usuários com discriminator: $discrim"
                    description = usersString
                    color = EMBED_DEFAULT_COLOR
                }
            }
        }
    }
}