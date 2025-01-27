package userandcustomerservice.implementation.tables

import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    Database.connect(
        url = "jdbc:postgresql://127.0.0.1:5432/business",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = ""
    )

}
