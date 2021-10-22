package models

// this is the data class
data class DeliveryModel(
    var id: Int = 0,
    var fullName: String = "",
    var address: String = "",
    var mobile: Int = 0,
    var email: String = "",
    var gender: String = ""
)