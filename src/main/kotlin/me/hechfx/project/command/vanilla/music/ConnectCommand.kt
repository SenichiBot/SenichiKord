package me.hechfx.project.command.vanilla.music

import kotlinx.coroutines.FlowPreview
import me.hechfx.project.api.Command
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.lavakord.Lavakord

class ConnectCommand: Command {
    override val category = CommandCategory.MUSIC
    override val debugMode = false
    override val description = "???"
    override val labels = listOf("conectar")
    override val onlyDev = false

    @ExperimentalStdlibApi
    @FlowPreview
    override suspend fun run(context: CommandContext) {
        val voiceState = context.member.getVoiceState()

        if (voiceState.channelId == null) return context.reply("se conecta em um canal de voz pls")

        val lavakord = Lavakord(context)

        lavakord.link.connectAudio(voiceState.channelId!!.value)
    }
}