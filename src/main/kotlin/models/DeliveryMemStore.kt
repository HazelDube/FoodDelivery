package models

private val logger = KotlinLogging.logger {}
var lastId = 1000

internal fun getId(): Int {
    return lastId++
}

class DeliveryMemStore: DeliveryStore {
    private val customers = ArrayList<DeliveryModel>()

    override fun findAll() : List<DeliveryModel>{
        return customers
    }

    // overrides the findOne abstract function and implement the class function
    override fun findOne(id: Int) : DeliveryModel?{
        return customers.find { m -> m.id == id }
    }

    // overrides the create abstract function and implement the class function
    override fun create(customer: DeliveryModel) {
        customer.id = getId()
        customers.add(customer)
        logAll()
    }

    // overrides the update abstract function and implement the class function
    override fun update(customer: DeliveryModel) {
        val foundCustomer = findOne(customer.id)
        if(foundCustomer!= null){
            foundCustomer.fullName = customer.fullName
            foundCustomer.address = customer.address
            foundCustomer.mobile = customer.mobile
            foundCustomer.email = customer.email
            foundCustomer.gender = customer.gender
        }
    }

    // overrides the delete abstract function and implement the class function
    override fun delete(customer: DeliveryModel) {
        val foundMember = findOne(customer.id)
        if(foundMember != null){
            customers.remove(foundMember)
        }
    }

    // function to display all the members
    internal fun logAll(){
        customers.forEach { logger.info ("$it")}
    }
}