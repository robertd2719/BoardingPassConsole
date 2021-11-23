package customer;

import java.io.Serializable;

public class Customer implements Serializable {
    String firstName;
    String lastName;
    String age;
    Gender gender;
    double discount;

    public Customer() {
        System.out.println("New Customer was created");
        this.firstName = "";
        this.lastName = "";
        this.age = "";
        this.gender = null;
        double discount = 0.0;
    }

    public Customer(String firstName, String lastName, String age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        int intAge = Integer.parseInt(age);
        if (intAge > 60) {
            discount = 0.60;
        } else if (intAge <= 12) {
            discount = 0.5;
        } else if (gender == Gender.FEMALE) {
            discount = 0.25;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", gender=" + gender +
                ", discount=" + discount +
                '}';
    }
}
