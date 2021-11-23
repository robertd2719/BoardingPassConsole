package boardingPass;

import customer.Customer;
import flight.Flight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.UUID;

// Boarding pass will hold information from both the flight and customer data
public class BoardingPass {
    Customer customer;
    Flight flight;
    double subtotalFlightTime;
    double discount;
    double taxes;
    double fees;
    double total;

    // No-Args constructor
    public BoardingPass() throws IOException {
        this.customer = null;
        this.flight = null;
    }

    // Constructor for creation of Boarding pass will also compute basic
    // price with tax and discounts.
    public BoardingPass(Customer customer, Flight flight) throws IOException {
        System.out.println("New Boarding pass was created");
        this.customer = customer;
        this.flight = flight;
        // determine if there is a discount
        this.computeTotal();
        this.writeBoardingPass();
    }

    public void computeTotal() {
        double hourPrice = this.flight.getFlightHours() * 230.00;
        double minutePrice = this.flight.getFlightMinutes() * (230.0 / 60);
        this.subtotalFlightTime = hourPrice + minutePrice;
        this.discount = this.subtotalFlightTime * this.customer.getDiscount();
        this.taxes = this.subtotalFlightTime * 0.07;
        this.fees = 30.00;
        this.total = this.subtotalFlightTime - this.discount + taxes + fees;
    }

    public void writeBoardingPass() {
        UUID bpUUID = UUID.randomUUID();
        String boardNum = bpUUID.toString();

        DecimalFormat f = new DecimalFormat("##.00");
        //   System.out.println(f.format(d));
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/resources/pass" + boardNum));
            pw.println("-------------------Customer Information-------------------------");
            pw.println("First Name:      " + this.customer.getFirstName());
            pw.println("Last Name:       " + this.customer.getLastName());
            pw.println("Phone:           (910)778-9011");
            pw.println("Email:           ultra@aol.com");
            pw.println("Age              " + this.customer.getAge());
            pw.println("Gender           " + this.customer.getGender());
            pw.println("--------------------Flight Information---------------------------");
            pw.println("Boarding pass Number" + boardNum);
            pw.println("Carrier:         " + this.flight.getCarrier());
            pw.println("Departure:       " + this.flight.getDeptCity());
            pw.println("Dept Time:       " + this.flight.getDepartureLocalDateTime());
            pw.println("Arriving:        " + this.flight.getArrivalCity());
            pw.println("Arrival Time:    " + this.flight.getArrivalLocalDateTime());
            pw.println("------------------------------------------------------------------");
            pw.println("Flight hours:    " + this.flight.getFlightHours());
            pw.println("Flight mins:     " + this.flight.getFlightMinutes());
            pw.println("Flight charges:  " + f.format(this.subtotalFlightTime));
            pw.println("Discount:        " + f.format(this.discount));
            pw.println("Taxes:           " + f.format(this.taxes));
            pw.println("Fees:            " + f.format(this.fees));
            pw.println("Total:           " + f.format(this.total));
            pw.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.out.println("Could not open file");
        }
    }
}
