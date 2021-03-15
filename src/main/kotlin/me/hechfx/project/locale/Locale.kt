package me.hechfx.project.locale

/**
 *
 */
class Locale {
    val strings = hashMapOf<String, String>()

    operator fun get(path: String): String =
        strings[path] ?: "invalid.string"

}