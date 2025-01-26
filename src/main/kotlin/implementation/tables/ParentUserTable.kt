package implementation.tables

import UsersTable.insert
import business.dataclasses.ParentUser
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.text.insert


object ParentUserTable : Table("parentuser") {
    open val id = integer("parentid")
    open val username = varchar("username", 30)
    open val password = varchar("password", 200)
    open val name = varchar("name", 50)
    open val email = varchar("email", 50)
    open val phoneNumber = varchar("phonenumber", 15)
    open val money = double("money")


    fun readUserById(userId: Int): ResultRow? {
        return transaction {
            ParentUserTable.selectAll().where { ParentUserTable.id eq userId }.singleOrNull()
        }
    }

    fun readUserByUserName(username: String): ResultRow? {
        return transaction {
            ParentUserTable.select { ParentUserTable.username eq username }.singleOrNull()
        }
    }

    fun readUserByEmail(userId: Int): ResultRow? {
        return transaction {
            ParentUserTable.selectAll().where { ParentUserTable.id eq userId }.singleOrNull()
        }
    }

    fun readUserByPhoneNumber(userId: Int): ResultRow? {
        return transaction {
            ParentUserTable.selectAll().where { ParentUserTable.id eq userId }.singleOrNull()
        }
    }

    fun readUserPassword(username: String): String? {
        val user = readUserByUserName(username)
        if (user != null) {
            return user[ParentUserTable.password]
        } else return null
    }

    fun insertUser(user: ParentUser): ResultRow? {
        return transaction {
            val id = ParentUserTable.insert {
                it[username] = user.username
                it[password] = user.password
                it[name] = user.name
                it[email] = user.email
                it[phoneNumber] = user.phoneNumber
                it[money] = user.money
            } get ParentUserTable.id
            ParentUserTable.select { ParentUserTable.id eq id }.singleOrNull()
        }
    }

    fun transferMoney(username: String, newMoney: Double): Double? {
        val user = readUserByUserName(username)
        if (user != null) {
            val userMoney = user[ParentUserTable.money]
            transaction {


                ParentUserTable.update({ ParentUserTable.id eq user[ParentUserTable.id] }) {

                    it[money] =  userMoney + newMoney
                }
            }
        }
        return newMoney
    }

    fun withdrawMoney(username: String, newMoney: Double) : Double{
        val user = readUserByUserName(username)
        if (user != null) {
            val userMoney = user[ParentUserTable.money]
            transaction {
                ParentUserTable.update({ ParentUserTable.id eq user[ParentUserTable.id] }) {
                    it[money] = userMoney - newMoney
                }
            }
        }
        return newMoney
    }

    fun changeInformation(user: ParentUser) {
        val userInTable = readUserByUserName(user.name)
        return transaction {
            if (userInTable != null) {
                ParentUserTable.update({ ParentUserTable.id eq userInTable[ParentUserTable.id] }) {
                    it[username] = user.username
                    it[password] = user.password
                    it[name] = user.name
                    it[email] = user.email
                    it[phoneNumber] = user.phoneNumber
                }
            }
        }
    }


}