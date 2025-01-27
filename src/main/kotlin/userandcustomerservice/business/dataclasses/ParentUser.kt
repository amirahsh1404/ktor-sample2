package userandcustomerservice.business.dataclasses

open class ParentUser(
    open var username: String,
    open var password: String,
    open var name : String,
    open var email: String,
    open var phoneNumber: String,
    open var money: Double,
) {

}