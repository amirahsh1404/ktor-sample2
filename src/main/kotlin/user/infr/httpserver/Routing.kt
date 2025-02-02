package user.infr.httpserver

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import user.application.UserController

fun Application.configureRouting() {
    val userController = UserController()
    routing {

        staticResources("/content", "mycontent")

        post("/signup") {
            val params = call.receiveParameters()
            val username: String? = params["username"]
            val password: String? = params["password"]
            val fullName: String? = params["fullName"]
            val email: String? = params["email"]

            val message: String = userController.createUser(username, password, fullName, email)

            val userJson: String? = null
            //TODO
//            if (message )
//                userjson =
            //TODO: Front Handles
        }

        post("/login") {
            val params = call.receiveParameters()
            val username: String? = params["username"]
            val password: String? = params["password"]

            val message: String = userController.loginUser(username, password)

            //TODO
//            if (message)
//                userjson =
            //TODO: Front Handles

        }

        post("/change-information") {
            val params = call.receiveParameters()
            val username = params["username"]
            val fullName = params["fullName"]
            val email = params["email"]

            val message : String = userController.changeInformation(username, fullName, email)
            //TODO
//            if (message)
//                userjson =
            //TODO: Front handles
        }

        post("/delete-user") {
            val params = call.receiveParameters()
            val username: String? = params["username"]

            val message : String = userController.deleteUser(username)
            //TODO
//            if (message)
//                userjson =
            //TODO: Front Handles?

        }


    }
}
