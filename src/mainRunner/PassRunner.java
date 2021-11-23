package mainRunner;

import boardingPass.BoardingPass;
import customer.*;
import customer.Gender;
import flight.Flight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PassRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Create new Customers Object.
        Customers customers;
        customers = new TestData().getTestData();
        // Check to see if file exists:
        createCustomerFile("customers.ser");
        // read the data from the file and place in local customers object.
        customers = readCustomerFile();
        // INITIAL LOADING OF FILE COMPLETE ---START USER INPUT--
        boolean quit = false;
        while (!quit) {
            Scanner in = new Scanner(System.in);
            userSelectMenu();
            String selection = in.next();
            System.out.println();
            // Allow the customer to select the mode of operation including 'quit'
            switch (selection) {
                // Create new Customer
                case "1":
                    Customer customer = createCustomer();
                    customers.addCustomer(customer);
                    writeCustomerFile(customers);
                    customers = readCustomerFile();
                    System.out.println("New customer created and saved to file");
                    break;
                // Create new flight-planner
                case "2":
                    pickFlightPlanner(customers);
                    break;
                // Exit
                case "3":
                    System.out.println("\t\t\t\tThank you and have a nice day");
                    quit = true;
            }
        }
    }

    // Function allows for the creation of a new customer and input of all relevant data
    public static Customer createCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("------New Customer---");
        System.out.print("First Name: ");
        String firstName = in.next();
        System.out.print("Last Name: ");
        String lastName = in.next();
        System.out.print("Age: ");
        String age = in.next();
        System.out.print("Gender: ");
        String stringGender = in.next().toUpperCase();
        Gender gender;
        switch (stringGender) {
            case "MALE":
                gender = Gender.MALE;
                break;
            case "FEMALE":
                gender = Gender.FEMALE;
                break;
            case "NONBINARY":
                gender = Gender.NONBINARY;
                break;
            default:
                gender = Gender.NORESPONSE;
                break;
        }
        Customer customer = new Customer(firstName, lastName, age, gender);
        return customer;
    }

    //@TODO clean this output up a bit to be more human readable
    public static void listCustomers(Customers customers) {
        for (Customer customer : customers.getCustomers()) {
            System.out.println(customer);
        }
    }

    // Attempt to create customers.ser file
    public static void createCustomerFile(String str) {
        Path path = Paths.get("src/resources/" + str);
        if (Files.exists(path)) {
            System.out.println(str + " file already exists....loading data");
        } else {
            try {
                Files.createFile(path);
            } catch (Exception err) {
                System.out.println(err.getMessage());
                System.out.println("Unable to create file");
            }
        }
    }

    // Serialize our objects for output to file as objects for retrieval later
    public static void writeCustomerFile(Customers customers) throws IOException {
        ArrayList<Customer> outList = customers.getCustomers();
        FileOutputStream fos = new FileOutputStream("src/resources/customers.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(outList);
        oos.close();
        fos.close();
    }

    // Deserialize the data from the file for usage by the program
    public static Customers readCustomerFile() throws IOException, ClassNotFoundException {
        Customers customers = new Customers();
        var fis = new FileInputStream("src/resources/customers.ser");
        var ois = new ObjectInputStream(fis);
        customers.setCustomers((ArrayList<Customer>) ois.readObject());
        return customers;
    }

    // Display menu method
    public static void userSelectMenu() {
        System.out.println("\t\t\t\tWelcome to Good Enough Airlines");
        System.out.println("\t\t\t\t-------------------------------");
        System.out.println("\t\t\t\t1.       Add New Customer      ");
        System.out.println("\t\t\t\t2.         Pick Flight         ");
        System.out.println("\t\t\t\t3.             Exit            ");
        System.out.println("\t\t\t\t-------------------------------");
        System.out.println();
        System.out.print("\t\t\t\tPlease Choose: (1 - 3 ) : ");
    }

    // This will allow the customer to enter in the values for their relative city
    // and will use this entered and computed data for buildinng flights and boarding
    // passes.
    public static void pickFlightPlanner(Customers customers) throws IOException {
        //Display a list of users for output to the screen
        Scanner in = new Scanner(System.in);
        // Get user data
        System.out.println("\t\t\t\t-----Flight Planner-----");
        System.out.print("\t\t\t\tDeparture City: ");
        String departureCity = in.next();
        System.out.print("\t\t\t\tArrival City: ");
        String arrivalCity = in.next();
        System.out.print("\t\t\t\tMonth: ");
        String month = in.next();
        System.out.print("\t\t\t\tDay: ");
        String day = in.next();
        System.out.print("\t\t\t\tYear: ");
        String year = in.next();
        //@TODO have the departure times as a random list of random length w/random times
        System.out.println();
        System.out.println("\t\t\t\tDeparture Times");
        System.out.println("\t\t\t\t---------------");
        System.out.println("\t\t\t\t1. 0600 local");
        System.out.println("\t\t\t\t2. 1000 local");
        System.out.println("\t\t\t\t3. 1200 local");
        System.out.print("\t\t\t\tTime: (1-3) ");
        String selectedTime = in.next();
        String time;
        switch (selectedTime) {
            case "1":
                time = "0600";
                break;
            case "2":
                time = "1000";
                break;
            case "3":
                time = "1200";
                break;
            default :
                time = "0600";
        }
        // Use the data from above to creat a new flight object
        // the flight object will compute flight times and departure and return flight
        // times for each flight
        Flight flight = new Flight(departureCity, arrivalCity, month, day, year, time);
        System.out.println("\t\t\t\tSelect Customer:  ");
        var count = 1;
        for (Customer customer : customers.getCustomers()) {
            System.out.println(count + ". " + customer.getFirstName() + " " + customer.getLastName());
            count += 1;
        }
        // Once the flight has been chosen, select customer from customer list
        // to build the boarding pass file.
        String selection = in.next();
        Customer selectedCustomer = customers.getCustomers().get(Integer.parseInt(selection) - 1);
        System.out.println("You selected: " + selectedCustomer.getFirstName());
        // Populate the boarding pass with information from the customer
        BoardingPass boardingPass = new BoardingPass(selectedCustomer,flight);
    }

}

