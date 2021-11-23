package boardingPass;

import customer.Customer;
import flight.Flight;

// Boarding pass will hold information from both the flight and customer data
public class BoardingPass {
    Customer customer;
    Flight flight;

    // No-Args constructor for later use with javafx and TableView elements
    public BoardingPass(){
        this.customer = null;
        this.flight = null;
    }
    // Constructor for creation of Boarding pass
    public BoardingPass(Customer customer, Flight flight){
        this.customer = customer;
        this.flight = flight;
    }
}
