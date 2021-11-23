package customer;

import java.io.Serializable;
import java.util.ArrayList;

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
