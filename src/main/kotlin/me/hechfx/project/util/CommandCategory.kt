package me.hechfx.project.util

/**
 * Class used to separate commands by
 * their respective category based on what they do.
 *
 * @param categoryName The locale path used to find the name.
 */
enum class CommandCategory(val categoryName: String) {
    MISCELLANEOUS("Miscelânea"),
    SOCIAL("Social"),
    ECONOMY("Economia"),
    ADMIN("Administração"),
    UTIL("Utilidade"),
    MUSIC("Música"),
    INFO("Informação")
}