package me.hechfx.project.util.lavakord

import dev.schlaubi.lavakord.audio.Link
import dev.schlaubi.lavakord.audio.TrackEndEvent
import dev.schlaubi.lavakord.audio.TrackEvent
import dev.schlaubi.lavakord.audio.on
import dev.schlaubi.lavakord.audio.player.Player
import dev.schlaubi.lavakord.rest.TrackResponse
import kotlinx.coroutines.FlowPreview
import me.hechfx.project.api.CommandContext

@FlowPreview
@ExperimentalStdlibApi
class Lavakord(private val context: CommandContext) {
    companion object {
        val queue = mutableListOf<TrackResponse.PartialTrack>()
    }

    val link: Link
        get() = context.guild?.let { context.senichi.lavakord.getLink(it.id.value) } ?: error("Missing guild")
    val player: Player
        get() = link.player

    suspend fun play(track: TrackResponse.PartialTrack) {
        val voiceState = context.member.getVoiceState()

        if (link.state == Link.State.NOT_CONNECTED) {
            if (voiceState.channelId == null) return context.reply("Você não está conectado à um canal de voz!")
            link.connectAudio(voiceState.channelId!!.value)
        }

        if (queue.size == 0) return _play(track)
        else {
            queue.add(track)
            context.reply("`${track.info.title}` foi adicionada à lista")
        }
    }

    suspend fun _play(track: TrackResponse.PartialTrack) {
        context.reply("Tocando agora: `${track.info.title}`")

        player.on<TrackEvent, TrackEndEvent> {
            finish(player)
        }
    }

    private suspend fun finish(player: Player) {
        val q = queue.removeAt(0)

        if (queue.size == 0) {
            _play(q)
        } else {
            link.destroy()
        }
    }
}