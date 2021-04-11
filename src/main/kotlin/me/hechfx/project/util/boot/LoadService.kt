@file:OptIn(ExperimentalSerializationApi::class)

package me.hechfx.project.util.boot

import com.typesafe.config.ConfigFactory
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import me.hechfx.project.Senichi
import me.hechfx.project.util.locale.SenichiConfig
import java.io.File

object LoadService {
    @FlowPreview
    @JvmStatic
    @ExperimentalStdlibApi
    fun main(args: Array<String>) {
        val config = Hocon {}.decodeFromConfig<SenichiConfig>(ConfigFactory.parseFile(inspect("config.conf")))

        runBlocking {
            Senichi(config).start()
        }
    }
}
fun inspect(fileName: String, createIfNotExists: Boolean = true): File {
    val file = File(fileName)

    if (file.exists().not()) {
        if (createIfNotExists) {
            file.createNewFile()
            file.writeBytes(Senichi::class.java.getResourceAsStream("/$fileName").readAllBytes())
        }
        error("There wasn't an existent config, so I created one for you! Try customizing the config at the **ROOT** folder, not the one at resources.")
    }

    return file
}