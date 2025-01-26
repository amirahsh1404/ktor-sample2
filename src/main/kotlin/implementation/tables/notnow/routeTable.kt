package implementation.tables.notnow

import org.jetbrains.exposed.sql.Table

object routeTable : Table("route") {
    val id = integer("routid")
    val originCityID = integer("origincity")
    val destinationCityID = integer("destinationcity")
    val priceFactor = double("pricefactor")
    val basePrice = double("baseprice")
    val price = double("price")


}