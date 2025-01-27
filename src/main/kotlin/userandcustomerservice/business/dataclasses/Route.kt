package userandcustomerservice.business.dataclasses

class Route (var origin : City, var destination : City, var priceFactor: Double, var basePrice : Double) {
    var price : Double = origin.priceFactor * destination.priceFactor * priceFactor * basePrice
}