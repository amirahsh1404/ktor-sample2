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
                    call.respond(HttpStatusCode.Accepted, signUpResult.value.userSuccessType.message)
                }

                is UserResult.Error<*, *> -> {

                    signUpResult.exception.message?.let { message ->
                        call.respond(HttpStatusCode.BadRequest, message)
                    }
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
                    loginResult.exception.message?.let { message ->
                        call.respond(HttpStatusCode.BadRequest, message)
                    }
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
                    changeInfoResult.exception.message?.let { message ->
                        call.respond(HttpStatusCode.BadRequest, message)
                    }
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
                    deleteUserResult.exception.message?.let { message ->
                        call.respond(HttpStatusCode.BadRequest, message)
                    }
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
                    userGetInfoResult.exception.message?.let { message ->
                        call.respond(HttpStatusCode.BadRequest, message)
                    }
                }


            }
        }


    }
}
