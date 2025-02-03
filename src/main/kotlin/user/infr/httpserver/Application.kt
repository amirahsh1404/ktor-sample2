package user.infr.httpserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    val db = Database.connect(
        url = "jdbc:postgresql://localhost:5432/my_user",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "root"
    )


    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting2()


}
