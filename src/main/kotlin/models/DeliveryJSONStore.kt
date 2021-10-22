package models

import helpers.exists
import helpers.read
import helpers.write
import java.lang.reflect.Type
import kotlin.random.Random


private val logger = KotlinLogging.logger {}

const val JSON_FILE = "customers.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
val listType: Type? = object : TypeToken<ArrayList<DeliveryModel>>() {}.type

fun generateRandomId(): Int {
    return Random.nextInt()
}


class DeliveryJSONStore : DeliveryStore {
    private var customers = mutableListOf<DeliveryModel>()

    init {
        if(exists(JSON_FILE)){
            deserialize()
        }
    }

    // overrides the findAll abstract function and implement the class function
    override fun findAll(): MutableList<DeliveryModel> {
        return customers
    }

    // overrides the findOne abstract function and implement the class function
    override fun findOne(id: Int): DeliveryModel? {
        return customers.find { m -> m.id == id }
    }

    // overrides the create abstract function and implement the class function
    override fun create(customer: DeliveryModel) {
        customer.id = generateRandomId()
        customers.add(customer)
        serialize()
    }

    // overrides the update abstract function and implement the class function
    override fun update(customer: DeliveryModel) {
        val foundCustomer = findOne(customer.id)
        if(foundCustomer != null){
            foundCustomer.fullName = customer.fullName
            foundCustomer.address = customer.address
            foundCustomer.mobile = customer.mobile
            foundCustomer.email = customer.email
            foundCustomer.gender = customer.gender
        }
        serialize()
    }

    // overrides the delete abstract function and implement the class function
    override fun delete(customer: DeliveryModel) {
        val foundCustomer = findOne(customer.id)
        if(foundCustomer != null){
            customers.remove(foundCustomer)
        }
        serialize()
    }

    // function to display all the members
    internal fun logAll(){
        customers.forEach { logger.info("$it")}
    }

    // function to convert the member type to json format and persist
    private fun serialize(){
        val jsonString = gsonBuilder.toJson(customers, listType)
        write(JSON_FILE, jsonString)
    }

    // function to convert from json format to member type the persisted datta
    private fun deserialize(){
        val jsonString = read(JSON_FILE)
        customers = Gson().fromJson(jsonString, listType)
    }

}