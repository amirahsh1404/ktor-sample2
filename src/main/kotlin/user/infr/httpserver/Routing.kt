package user.infr.httpserver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import user.application.UserController

fun Application.configureRouting() {
    val userController = UserController()
    routing {


        post("/signup") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()
            val fullName: String = params["fullName"].toString()
            val email: String = params["email"].toString()

            val message: String = userController.createUser(username, password, fullName, email)
            if (message == "created") {
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }

        post("/login") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()

            val message: String = userController.loginUser(username, password)

            if (message == "logged in") {
                call.respond(HttpStatusCode.OK, "user logged in")
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }
        get("/users/{username}/{password}") {
            val username = call.parameters["username"].toString()
            val password = call.parameters["password"].toString()

            val user = userController.getInformation(username, password)

            if (output.startsWith("Error")) {
                call.respond(HttpStatusCode.BadRequest, output)
            } else {
                call.respond(HttpStatusCode.OK, output)
            }
        }

        post("/change-information") {
            val params = call.receiveParameters()
            val username = params["username"].toString()
            val fullName = params["fullName"].toString()
            val email = params["email"].toString()

            val message: String = userController.changeInformation(username, fullName, email)

            if (message == "changed") {
                call.respond(HttpStatusCode.Accepted, "user information changed")
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }
        }

        post("/delete-user") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()

            val message: String = userController.deleteUser(username)

            if (message == "deleted") {
                call.respond(HttpStatusCode.Accepted, "deleted user")
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }


    }
}
