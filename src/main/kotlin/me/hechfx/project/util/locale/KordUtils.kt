package me.hechfx.project.util.locale

import dev.kord.rest.builder.message.EmbedBuilder
import kotlinx.serialization.Serializable

@Serializable
data class SenichiConfig(
    val token: String,
    val prefix: String
)

fun embed(block: EmbedBuilder.() -> Unit): EmbedBuilder =
    EmbedBuilder().apply(block)