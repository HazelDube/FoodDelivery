package models

// abstract class
interface DeliveryStore {
    fun findAll(): List<DeliveryModel>
    fun findOne(id: Int) : DeliveryModel?
    fun create(customer: DeliveryModel)
    fun update(customer: DeliveryModel)
    fun delete(customer: DeliveryModel)

}