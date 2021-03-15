package me.hechfx.project.util.constant

import com.typesafe.config.ConfigFactory
import dev.kord.common.Color
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import me.hechfx.project.util.boot.inspect
import me.hechfx.project.util.locale.SenichiConfig

object Constants {
    val EMBED_DEFAULT_COLOR = Color(252, 123, 3)
    val OWNER_ID = "236167700777271297"
    fun getConfig(): SenichiConfig {
        val config = Hocon {}.decodeFromConfig<SenichiConfig>(ConfigFactory.parseFile(inspect("config.conf")))

        return config
    }
}