package business

import com.example.module
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

class testPart {
    fun main(args: Array<String>) {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/test",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = ""
        )

        embeddedServer(
            Netty,
            port = 9292,
            host = "0.0.0.0",
            module = Application::module
        ).start(wait = true)
        io.ktor.server.netty.EngineMain.main(args)
    }

}