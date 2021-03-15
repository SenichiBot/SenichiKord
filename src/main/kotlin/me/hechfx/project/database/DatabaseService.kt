package me.hechfx.project.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseService(val server: Server) {

    fun startDatabase() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://${server.host}:${server.port}/${server.database}?useTimezone=true&serverTimezone=UTC"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = server.user
            password = server.password
            maximumPoolSize = 8
        }

        Database.connect(HikariDataSource(config))
    }

    data class Server(
        val host: String,
        val port: String,
        val database: String,
        val user: String,
        val password: String
    )
}