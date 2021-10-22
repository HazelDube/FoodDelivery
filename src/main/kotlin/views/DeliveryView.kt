package views

import models.DeliveryJSONStore
import models.DeliveryModel
import java.util.regex.Pattern


class DeliveryView {
    // menu function interact with the user
    fun menu(): Int{
        var option : Int
        var input: String?

        println("FOOD DASH MENU")
        println(" 1. Add Customer")
        println(" 2. Update Customer Details")
        println(" 3. List All Customers")
        println(" 4. Search Customer")
        println(" 5. Delete Customer")
        println(" -99. Dummy Data")
        println(" 100. Exit")
        println()
        print("Enter an Option : ")
        input = readLine()!!
        option = if(input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            100
        return option
    }




    // list function displays the members list  persisited
    fun listCustomers(customers : DeliveryJSONStore){
        println("List All Members")
        println()
        customers .logAll()
        println()
    }

    // lsit function allows you to add a member
    fun addCustomerData(customer : DeliveryModel) : Boolean{
        println()
        print("Enter your full name : ")
        customer.fullName = readLine()!!
        print("Enter your Address: ")
        customer.address = readLine()!!
        //print("mobile number : ")
       // customer.mobile = readLine()!!
        print("Enter Your Email: ")
        customer.email = readLine()!!
        print("Enter Your Gender (Male/Female): ")
        customer.gender = readLine()!!
        return customer.fullName.isNotEmpty() &&
                customer.address.isNotEmpty() &&
               // customer.mobile.isNotEmpty() &&
                customer.email.isEmailValid()
    }

    // this function displays the customer details
    fun showCustomers(customer : DeliveryModel){
        println("Customer Details [$customer]")
    }

    // this functions enables the customer to update details
    fun updateCustomerData(customer: DeliveryModel) : Boolean{
        var tempFName : String?
        var tempaddress : String?
       // var tempmobile : int?
        var tempEmail : String?
        var tempGender : String?

        print("Enter a new full name for [ " + customer.fullName + " ] : ")
        tempFName = readLine()!!
        print("Enter a new address for [ " + customer.address + " ] : ")
        tempaddress = readLine()!!
       // print("Enter mobile number [ " + customer.mobile + " ] : ")
       // tempmobile = readLine()!!
        print("Enter a new email for [ " + customer.email + " ] : ")
        tempEmail = readLine()!!
        print("Enter a new Gender for [ " + customer.gender + " ] : ")
        tempGender = readLine()!!

        if(!tempFName.isNullOrEmpty() && !tempEmail.isNullOrEmpty()){
            customer.fullName = tempFName
            customer.address = tempaddress
         //   customer.mobile = tempmobile
            customer.email = tempEmail
            customer.gender = tempGender
            return true
        }
        return false
    }

    // function used to get an id used for searching and deleting
    fun getId() : Int{
        var strId : String?     // string to hold user input
        var searchId : Int     // Long to hold converted id
        print("Enter Id to search/update : ")
        strId = readLine()!!
        searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toInt()
        else
            -9
        return searchId
    }

    // function used to validate the email pattern. if not valid member wont be added
    fun String.isEmailValid(): Boolean{
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}