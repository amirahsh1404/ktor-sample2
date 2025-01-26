package implementation.tables.notnow

import org.jetbrains.exposed.sql.Table

object cargoTable : Table("cargo") {
    val id = integer("cargoid")
    val name = varchar("name",40)
    val description = varchar("description",400)
    val route = integer("route")
    val cargoServicerID = integer("cargoservicerid")
    val ownerID = integer("ownerid")
}