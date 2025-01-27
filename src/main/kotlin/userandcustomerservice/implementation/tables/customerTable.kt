package userandcustomerservice.implementation.tables

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import userandcustomerservice.business.dataclasses.ParentUser

object customerTable : Table("customer") {
    val id = integer("customerid")
    val nationalCode = varchar("nationalcode",15)

    fun insertCustomer(user : ParentUser, nationalCodeUser: String) : ResultRow? {
        return transaction {
            val idOfUser : Int? = ParentUserTable.getUserID(user.username)
            val idNew = customerTable.insert {
                if (idOfUser != null) {
                    it[id] = idOfUser
                }
                it[nationalCode] = nationalCodeUser
            }  get customerTable.id
            customerTable.select { customerTable.id eq idNew }.singleOrNull()
        }
    }

    fun readUserById(userId: Int): ResultRow? {
        return transaction {
            customerTable.selectAll().where { customerTable.id eq userId }.singleOrNull()
        }
    }


}