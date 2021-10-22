package controllers

import models.DeliveryJSONStore
import models.DeliveryModel
import views.DeliveryView


/**
 * @author Hazel Dube
 */
class DeliveryController {

    //val customers = DeliveryMemStore()
    private val customers = DeliveryJSONStore()
    private val deliveryView = DeliveryView()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info {"FoodDash Local to Waterford City"}
        println("Version 1.0")
    }

    // the function that gets called to start the whole program
    fun start(){
        var input : Int

        do {
            input = menu()
            when(input){
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                100 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        }while (input!=-1)
        logger.info("Shutting Down Food Delivery App")
    }

    // the menu function that displays the menu
    private fun menu() : Int {
        return deliveryView.menu()
    }

    // the add function allow the user to add a member
    private fun add(){
        val aCustomer = DeliveryModel()

        if(deliveryView.addCustomerData(aCustomer)) {
            customers.create(aCustomer)
            logger.info("Customer Added Successfully")
        }
        else
            logger.info("Unable to add customer please check all fields are entered correctly")
    }

    // list customers function
    private fun list(){
        deliveryView.listCustomers(customers)
    }

    // update customers function
    private fun update(){
        deliveryView.listCustomers(customers)
        val searchId = deliveryView.getId()
        val aCustomer = search(searchId)

        if(aCustomer != null){
            if(deliveryView.updateCustomerData(aCustomer)){
                customers.update(aCustomer)
                deliveryView.showCustomers(aCustomer)
                logger.info(" customer Updated : [$aCustomer]")
            }else
                logger.info("customer Not Added")
        }else
            println("customer Not Added")
    }

    // delete customers function
    private fun delete(){
        deliveryView.listCustomers(customers)
        val searchId = deliveryView.getId()
        val aCustomer = search(searchId)

        if(aCustomer != null){
            customers.delete(aCustomer)
            println("Customer Deleted....")
            deliveryView.listCustomers(customers)
        }
        else
            println("Customer Not Deleted....")
    }

    // search customers  function
    private fun search(){
        val aCustomer = search(deliveryView.getId())!!
        deliveryView.showCustomers(aCustomer)
    }

    // search customers id function
    private fun search(id: Int) : DeliveryModel?{
        return customers.findOne(id)
    }


    private fun dummyData(){
        customers.create(DeliveryModel(1,"O'Sullivan Sean","Waterford",3,"78945643","Male"))
        customers.create(DeliveryModel(2,"Murphy Sean","Waterford",5,"789446,","Male"))
        customers.create(DeliveryModel(3,"Doyle Erin","Waterford",4,"09438","Female"))
        customers.create(DeliveryModel(4,"O'Connor Rory" ,"Waterford",5,"09128","Male"))
    }

}
