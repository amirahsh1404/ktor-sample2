import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        val part1 = """
            <html>
                <body>
                <h1>Users Information</h1>
                <pre>
        """.trimIndent()
        val part2 = """
            </pre>
                </body>
                </html>
        """.trimIndent()
        staticResources("/content", "mycontent")

        post("/signup1") {
            val params = call.receiveParameters()
            val username = params["username"] ?: throw IllegalArgumentException("Username is required")
            val nationalCode =
                params["nationalCode"]?.toLongOrNull() ?: throw IllegalArgumentException("Invalid National Code")
            val birthDate = params["birthDate"] ?: throw IllegalArgumentException("Birth Date is required")
            val active = params["active"]?.toBoolean() ?: false

            val user = UserInit(username, nationalCode, birthDate, active)
            val userString = UsersTable.insert(user)
            val text: String = part1 + userString + part2
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
            //call.respondRedirect("content/signed_in.html")
        }

        post("/signin1") {
            val params = call.receiveParameters()
            val id = params["id"]?.toIntOrNull() ?: throw IllegalArgumentException("ID is required")
            val user = UsersTable.getUserById(id)

            val text: String = part1 + user + part2
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }

        post("/get-users-by-id1") {
            val params = call.receiveParameters()
            val id = params["id"]?.toIntOrNull() ?: throw IllegalArgumentException("ID is required")
            val user = UsersTable.getUserById(id)

            val text: String = part1 + user + part2
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }

        post("/change-active-mod1") {
            val params = call.receiveParameters()
            val id = params["id"]?.toIntOrNull() ?: throw IllegalArgumentException("ID is required")
            val text: String = "<h1>" + UsersTable.changeActivation(id) + "</h1>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }

        post("/get-actives1") {

            val activeUsers = UsersTable.activeUsers(true)

            val text: String = part1 + activeUsers + part2
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }

        post("/get-not-actives1") {
            val notActiveUsers = UsersTable.activeUsers(false)

            val text: String = part1 + notActiveUsers + part2
            val type = ContentType.parse("text/html")
            call.respondText(text, type)

        }

        get("/") {
            call.respondRedirect("content/sign_up_page.html")
        }

    }
}
