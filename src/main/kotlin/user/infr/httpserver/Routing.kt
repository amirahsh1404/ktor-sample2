package user.infr.httpserver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import user.application.UserController
import user.application.failure.*
import user.application.params.*
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.User
import user.infr.httpserver.libraryFiles.MessageLoader
import user.infr.httpserver.model.UserJson

fun Application.configureRouting2() {
    val userController = UserController()
    val causeString =
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
                    userController.createUser(params = CreateUserParams(username, password, fullName, email))) {


                    is UserResult.success<*, *> -> {
                        call.respond(HttpStatusCode.Created, "User successfully created")
                    }

                    is UserResult.failure<*, *> -> {
                        val failure = signUpResult.failure
                        val statusCode = when (failure) {
                            is CreateUserFailure.InvalidParamsFailure -> HttpStatusCode.BadRequest
                            is CreateUserFailure.UserExistsFailure -> HttpStatusCode.Conflict
                            is CreateUserFailure.EmailExistsFailure -> HttpStatusCode.Conflict
                            else -> HttpStatusCode.InternalServerError
                        }
                        call.respond(statusCode, MessageLoader.createMessage(failure.myFailure))
                    }
                }

            }

            post("/login") {
                val params = call.parameters
                val username: String = params["username"].toString()
                val password: String = params["password"].toString()

                when (val loginResult =
                    userController.loginUser(params = LoginUserParams(username, password))) {
                    is UserResult.success<*, *> -> {
                        call.respond(HttpStatusCode.Accepted, "User logged in successfully")
                    }

                    is UserResult.failure<*, *> -> {
                        val failure = loginResult.failure
                        val statusCode = when (failure) {
                            is LoginUserFailure.InvalidParamsFailure -> HttpStatusCode.BadRequest
                            is LoginUserFailure.UserNotFound -> HttpStatusCode.NotFound
                            is LoginUserFailure.PasswordWrongFailure -> HttpStatusCode.NotAcceptable
                            else -> HttpStatusCode.InternalServerError
                        }
                        call.respond(statusCode, MessageLoader.createMessage(failure.myFailure) )
                    }
                }

            }


            post("/change-information") {
                val params = call.parameters
                val username = params["username"].toString()
                val fullName = params["fullName"].toString()
                val email = params["email"].toString()

                when (val changeInfoResult =
                    userController.changeInformation(params = ChangeInformationParams(username, fullName, email))) {
                    is UserResult.success<*, *> -> {
                        call.respond(HttpStatusCode.Accepted, "Changed Info Successfully")
                    }

                    is UserResult.failure<*, *> -> {
                        val failure = changeInfoResult.failure
                        val statusCode = when (failure) {
                            is ChangeInformationFailure.InvalidParamsFailure -> HttpStatusCode.BadRequest
                            is ChangeInformationFailure.UserNotFoundFailure -> HttpStatusCode.NotFound
                            is ChangeInformationFailure.EmailExistsFailure -> HttpStatusCode.Conflict
                            else -> HttpStatusCode.InternalServerError
                        }

                        call.respond(statusCode, MessageLoader.createMessage(failure.myFailure) )
                    }
                }


            }

            post("/delete-user") {
                val params = call.parameters
                val username: String = params["username"].toString()

                when (val deleteUserResult = userController.deleteUser(DeleteUserParams(username))) {
                    is UserResult.success<*, *> -> {
                        call.respond(HttpStatusCode.Accepted, "user deleted successfully")
                    }

                    is UserResult.failure<*, *> -> {
                        val failure = deleteUserResult.failure
                        val statusCode = when (failure) {
                            is DeleteUserFailure.InvalidParamsFailure -> HttpStatusCode.BadRequest
                            is DeleteUserFailure.UserNotFoundFailure -> HttpStatusCode.NotFound
                            else -> HttpStatusCode.InternalServerError
                        }

                        call.respond(statusCode, MessageLoader.createMessage(failure.myFailure))
                    }
                }


            }

            get("/users/{username}/{password}") {
                val params = call.parameters
                val username = params["username"].toString()
                val password = params["password"].toString()

                when (val userGetInfoResult = userController.getInformation(GetInformationParams(username, password))) {
                    is UserResult.success<*, *> -> {
                        val userJson = UserJson.createJson(userGetInfoResult.value as User)
                        call.respond(HttpStatusCode.OK, userJson)
                    }

                    is UserResult.failure<*, *> -> {
                        val failure = userGetInfoResult.failure
                        val statusCode = when (failure) {
                            is GetInformationFailure.InvalidParamsFailure -> HttpStatusCode.BadRequest
                            is GetInformationFailure.UserNotFound -> HttpStatusCode.NotFound
                            is GetInformationFailure.PasswordWrongFailure -> HttpStatusCode.NotAcceptable
                            else -> HttpStatusCode.InternalServerError
                        }

                        call.respond(statusCode, MessageLoader.createMessage(failure.myFailure))
                    }
                }
            }
        }
}
