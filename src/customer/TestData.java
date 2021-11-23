package customer;

public class TestData {
    Customers customers;
    public Customers getTestData(){
        customers = new Customers();
        customers.addCustomer(new Customer("Larry","Jenkins","33",Gender.MALE));
        customers.addCustomer(new Customer("Mary","Jane","8",Gender.FEMALE));
        return this.customers;
    }
}
