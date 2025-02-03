package user.infr.httpserver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import user.application.UserController
import user.infr.httpserver.model.ResultPackage.UserResult
import user.infr.httpserver.model.UserJson

fun Application.configureRouting2() {
    val userController = UserController()
    routing {

        get("/") {
            call.respond("hello I am test project")
        }

        post("/signup") {

            val params = call.parameters
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()
            val fullName: String = params["fullName"].toString()
            val email: String = params["email"].toString()

            when (val signUpResult =
                userController.createUser(username, password, fullName, email)) {
                is UserResult.Success -> {
                    call.respond(signUpResult.value.userSuccessType.statusCode,
                        signUpResult.value.userSuccessType.message)
                }

                is UserResult.Error<* , *> -> {
                    val exception = signUpResult.exception

                    val status = signUpResult.getStatusCode(exception)
                    val message = signUpResult.getMessage(exception)

                    message?.let { call.respond(status, message) }

                }
            }

        }

        post("/login") {
            val params = call.parameters
            val username: String = params["username"].toString()
            val password: String = params["password"].toString()

            when (val loginResult =
                userController.loginUser(username, password)) {
                is UserResult.Success -> {
                    call.respond(HttpStatusCode.Accepted, loginResult.value.userSuccessType.message)
                }

                is UserResult.Error<*, *> -> {
                    val exception = loginResult.exception
                    val status = loginResult.getStatusCode(exception)
                    val message = loginResult.getMessage(exception)

                    message?.let { call.respond(status, message) }
                }
            }

        }


        post("/change-information") {
            val params = call.parameters
            val username = params["username"].toString()
            val fullName = params["fullName"].toString()
            val email = params["email"].toString()

            when (val changeInfoResult =
                userController.changeInformation(username, fullName, email)) {
                is UserResult.Success -> {
                    call.respond(HttpStatusCode.Accepted, changeInfoResult.value.userSuccessType.message)
                }

                is UserResult.Error<*, *> -> {
                    val exception = changeInfoResult.exception
                    
                    val status = changeInfoResult.getStatusCode(exception)
                    val message = changeInfoResult.getMessage(exception)

                    message?.let { call.respond(status, message) }
                }
            }


        }

        post("/delete-user") {
            val params = call.parameters
            val username: String = params["username"].toString()

            when (val deleteUserResult = userController.deleteUser(username)) {
                is UserResult.Success -> {
                    call.respond(HttpStatusCode.Accepted, deleteUserResult.value.userSuccessType.message)

                }

                is UserResult.Error<*, *> -> {
                    val exception = deleteUserResult.exception
                    val status = deleteUserResult.getStatusCode(exception)
                    val message = deleteUserResult.getMessage(exception)

                    message?.let { call.respond(status, message) }
                }
            }


        }

        get("/users/{username}/{password}") {
            val params = call.parameters
            val username = params["username"].toString()
            val password = params["password"].toString()

            when (val userGetInfoResult = userController.getInformation(username, password)) {
                is UserResult.Success -> {
                    val userJson = UserJson.createJson(userGetInfoResult.value)
                    call.respond(HttpStatusCode.OK, userJson)
                }

                is UserResult.Error<*, *> -> {
                    val exception = userGetInfoResult.exception
                    val status = userGetInfoResult.getStatusCode(exception)
                    val message = userGetInfoResult.getMessage(exception)

                    message?.let { call.respond(status, message) }
                }


            }
        }


    }
}
