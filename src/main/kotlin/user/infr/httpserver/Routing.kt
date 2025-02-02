package user.infr.httpserver

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import user.application.UserController
import user.application.UserControllerRepo

fun Application.configureRouting() {
    val userController: UserControllerRepo = UserController()
    routing {

        staticResources("/content", "mycontent")

        post("/signup") {
            val params = call.receiveParameters()
            val username: String? = params["username"]
            val password: String? = params["password"]
            val fullName: String? = params["fullName"]
            val email: String? = params["email"]

            val message: Boolean = userController.createUser(username, password, fullName, email)

            val userJson: String? = null
            //TODO
//            if (message)
//                userjson =
            //TODO: Front Handles
        }

        post("/login") {
            val params = call.receiveParameters()
            val username: String? = params["username"]
            val password: String? = params["password"]

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

            //TODO
//            if (message)
//                userjson =
            //TODO: Front handles
        }

        post("/delete-user") {
            val params = call.receiveParameters()
            val username: String? = params["username"]

            //TODO
//            if (message)
//                userjson =
            //TODO: Front Handles?

        }


    }
}
