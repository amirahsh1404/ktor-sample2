package user.infr

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import user.bl.entity.User


object UserTable : Table("user") {
    val id = integer("user_id")
    val username = varchar("username", 30)
    val password = varchar("password", 30)
    val fullName = varchar("fullname", 50)
    val email = varchar("email", 50)


    fun readUserByUserName(username: String): ResultRow? {
        return transaction {
            UserTable.select { UserTable.username eq username }.singleOrNull()
        }
    }

    fun readUserByEmail(email: String): ResultRow? {
        return transaction {
            UserTable.selectAll().where { UserTable.email eq email }.singleOrNull()
        }
    }

    fun readUserPassword(username: String): String? {
        val user = readUserByUserName(username)
        return if (user != null) {
            user[UserTable.password]
        } else null
    }

    fun insertUser(
        usernameEntered: String,
        passwordEntered: String,
        fullNameEntered: String,
        emailEntered: String
    ): Boolean {
        var row: ResultRow? = null
        transaction {
            val id = UserTable.insert {
                it[username] = usernameEntered
                it[password] = passwordEntered
                it[fullName] = fullNameEntered
                it[email] = emailEntered
            } get UserTable.id
            row = UserTable.select { UserTable.id eq id }.singleOrNull()
        }
        if (row == null) {
            return false
        }
        return true
    }


    fun update(
        usernameEntered: String,
        fullNameEntered: String,
        emailEntered: String
    ): Boolean {
        val userInTable = readUserByUserName(usernameEntered)
        transaction {
            if (userInTable != null) {
                UserTable.update({ UserTable.username eq usernameEntered }) {
                    it[fullName] = fullNameEntered
                    it[email] = emailEntered
                }
            }
        }
        if (userInTable == null) {
            return false
        }
        return true
    }

    fun delete(usernameEntered: String): Boolean {
        transaction {
                UserTable.deleteWhere { UserTable.username eq usernameEntered }
            }
        return true
        }
    }


