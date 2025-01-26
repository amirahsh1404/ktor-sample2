package implementation.tables.notnow

import org.jetbrains.exposed.sql.Table

object ownertocargoTable : Table("ownertocargo") {
    val ownerID = integer("ownerid")
    val cargoID = integer("cargoid")
}