package me.hechfx.project.command.vanilla.music

import dev.schlaubi.lavakord.audio.Link
import dev.schlaubi.lavakord.rest.TrackResponse
import dev.schlaubi.lavakord.rest.loadItem
import kotlinx.coroutines.FlowPreview
import me.hechfx.project.api.TextCommand
import me.hechfx.project.api.CommandContext
import me.hechfx.project.util.CommandCategory
import me.hechfx.project.util.lavakord.Lavakord

class PlayCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options(listOf("play, tocar")) {
        override val description = "???"
        override val category = CommandCategory.MUSIC
    }

    @ExperimentalStdlibApi
    @FlowPreview
    override suspend fun run(context: CommandContext) {
        val lavakord = Lavakord(context)

        val query = context.args.joinToString(" ")

        val search = if (query.startsWith("http")) {
            query
        } else {
            "ytsearch: $query"
        }

        if (lavakord.link.state != Link.State.CONNECTED) {
            context.reply("use `>!conectar` antes de usar o `>!play`")
            return
        }

        val item = lavakord.link.loadItem(search)

        when (item.loadType) {
            TrackResponse.LoadType.TRACK_LOADED -> lavakord.player.playTrack(item.tracks.first())
            TrackResponse.LoadType.PLAYLIST_LOADED -> lavakord.player.playTrack(item.tracks.first())
            TrackResponse.LoadType.SEARCH_RESULT -> lavakord.player.playTrack(item.tracks.first())
            TrackResponse.LoadType.NO_MATCHES -> context.reply("No matches")
            TrackResponse.LoadType.LOAD_FAILED -> context.reply(
                item.exception?.message ?: "Error"
            )
        }
    }
}