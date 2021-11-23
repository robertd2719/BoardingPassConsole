package boardingPass;

import customer.Customer;
import flight.Flight;

public class BoardingPass {
    Customer customer;
    Flight flight;

    public BoardingPass(){
        this.customer = null;
        this.flight = null;
    }
    public BoardingPass(Customer customer, Flight flight){
        this.customer = customer;
        this.flight = flight;
    }
}
