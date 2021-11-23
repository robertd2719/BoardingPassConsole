package boardingPass;

import customer.Customer;
import flight.Flight;

// Boarding pass will hold information from both the flight and customer data
public class BoardingPass {
    Customer customer;
    Flight flight;

    // No-Args constructor
    public BoardingPass(){
        this.customer = null;
        this.flight = null;
    }
    // Constructor for creation of Boarding pass will also compute basic
    // price with tax and discounts.
    public BoardingPass(Customer customer, Flight flight){
        System.out.println("New Boarding pass was created");
        this.customer = customer;
        this.flight = flight;

    }
}
