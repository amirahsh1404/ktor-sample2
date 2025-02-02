package user.infr.repo.pm

import org.jetbrains.exposed.sql.*


object UserTable : Table("user") {
    val id = integer("user_id")
    val username = varchar("username", 30)
    val password = varchar("password", 30)
    val fullName = varchar("fullname", 50)
    val email = varchar("email", 50)

}


