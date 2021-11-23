package customer;

import java.io.Serializable;
import java.util.ArrayList;

// Customers is a repository of customers with various uses inside the program
// Effectively a wrapper for the Arraylist the heart of the class.
public class Customers implements Serializable {
    ArrayList<Customer> customers;
    public Customers(){
        customers = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
}
