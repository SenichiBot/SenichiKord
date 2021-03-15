package me.hechfx.project.database.table

import org.jetbrains.exposed.dao.id.LongIdTable

object SenichiUsers: LongIdTable() {
    val souls = long("souls")
    val wpm = long("wpm")
}