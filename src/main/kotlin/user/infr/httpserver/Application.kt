package user.infr.httpserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import user.application.UserController
import user.infr.httpserver.model.ResultPackage.UserResult
import user.infr.httpserver.model.UserJson

fun main(args: Array<String>) {
    val db = Database.connect(
        url = "jdbc:postgresql://localhost:5432/my_user",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "root"
    )

    transaction(db) {

        val username = "ahshj"
        val password = "@Ahsh88"
        val userController = UserController()
        val userGetInfoResult = userController.getInformation(username, password)

        when (userGetInfoResult) {
            is UserResult.Error<*, *> -> {
                print( userGetInfoResult.exception.message)
            }
            is UserResult.Success -> {
                val userJson = UserJson.createJson(userGetInfoResult.value)
                print(userJson)
            }

        }

    }


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
