package user.infr.httpserver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import user.application.UserController
import user.application.params.*
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.User
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
                userController.createUser(params = CreateUserParams(username, password, fullName, email))) {
                is UserResult.success<*, *> -> {
                    call.respond(HttpStatusCode.Created,"User Created Successfully")
                }

                is UserResult.failure<* , *> -> {
                    val failure = signUpResult.failure.myFailure

                    val status = failure.statusCode
                    val message = failure.message

                    call.respond(status, message)

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
                    val failure = loginResult.failure.myFailure
                    val status = failure.statusCode
                    val message = failure.message

                    call.respond(status, message)
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
                    val failure = changeInfoResult.failure.myFailure

                    val status = failure.statusCode
                    val message = failure.message

                    call.respond(status, message)
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
                    val failure = deleteUserResult.failure.myFailure

                    val status = failure.statusCode
                    val message = failure.message

                    call.respond(status, message)
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
                    val failure = userGetInfoResult.failure.myFailure

                    val status = failure.statusCode
                    val message = failure.message

                    call.respond(status, message)
                }


            }
        }


    }
}
