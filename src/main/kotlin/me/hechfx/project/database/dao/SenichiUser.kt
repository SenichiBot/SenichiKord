package me.hechfx.project.database.dao

import me.hechfx.project.database.table.SenichiUsers
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class SenichiUser(id: EntityID<Long>): LongEntity(id) {

    val idLong: Long = id.value
    var souls: Long by SenichiUsers.souls
    var wpm: Long by SenichiUsers.wpm

}