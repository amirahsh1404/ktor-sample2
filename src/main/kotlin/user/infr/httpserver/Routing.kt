package user.infr.httpserver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import user.application.UserController
import user.infr.httpserver.model.ResultPackage.UserResult
import user.infr.httpserver.model.ResultPackage.UserSuccess
import user.infr.httpserver.model.ResultPackage.UserSuccessType

fun Application.configureRouting() {
    val userController = UserController()
    routing {


        post("/signup") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()
            val fullName: String = params["fullName"].toString()
            val email: String = params["email"].toString()

            val message: UserResult = userController.createUser(username, password, fullName, email)
            if (message == UserResult.Success(UserSuccess(UserSuccessType.CREATE_SUCCESS))) {
                call.respond(HttpStatusCode.Created, message)
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }

        post("/login") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()

            val message: UserResult = userController.loginUser(username, password)

            if (message == UserResult.Success(UserSuccess(UserSuccessType.LOGIN_SUCCESS))) {
                call.respond(HttpStatusCode.OK, message)
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }


        post("/change-information") {
            val params = call.receiveParameters()
            val username = params["username"].toString()
            val fullName = params["fullName"].toString()
            val email = params["email"].toString()

            val message: UserResult = userController.changeInformation(username, fullName, email)

            if (message == UserResult.Success(UserSuccess(UserSuccessType.CREATE_SUCCESS))) {
                call.respond(HttpStatusCode.Accepted, message)
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }
        }

        post("/delete-user") {
            val params = call.receiveParameters()
            val username: String = params["username"].toString()

            val message: UserResult = userController.deleteUser(username)

            if (message == UserResult.Success(UserSuccess(UserSuccessType.DELETE_SUCCESS))) {
                call.respond(HttpStatusCode.Accepted, message)
            } else {
                call.respond(HttpStatusCode.BadRequest, message)
            }

        }

        get("/users/{username}/{password}") {
            val username = call.parameters["username"].toString()
            val password = call.parameters["password"].toString()

            val userGetInfoResult = userController.getInformation(username, password)

            if (userGetInfoResult.instanceOf(UserResult.Error::class)) {
                call.respond(HttpStatusCode.BadRequest, userGetInfoResult)
            } else {
                //val userJson = UserJson.createJson(userGetInfoResult.user.)
                call.respond(HttpStatusCode.OK, )
            }
        }

    }
}
