package flight;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    String deptCity;
    String arrivalCity;
    String month;
    String day;
    String year;
    String departureTime;
    int flightHours;
    int flightMinutes;
    int arrivalHours;
    int arrivalMinutes;
    LocalDateTime departureLocalDateTime;
    LocalDateTime arrivalLocalDateTime;
    String carrier;
    // no args constructor for specialized use cases not utilized in this project
    public Flight() {
        this.deptCity = "";
        this.arrivalCity = "";
        this.month = "";
        this.day = "";
        this.year = "";
        this.departureTime = departureTime;
    }

    // Constructor that will take in user entered data and store/compute all necesssary fields
    // for flight information
    public Flight(String deptCity, String arrivalCity, String month, String day, String year, String time) {
        System.out.println("New flight was created");
        this.deptCity = deptCity;
        this.arrivalCity = arrivalCity;
        this.month = month;
        this.day = day;
        this.year = year;
        this.departureTime = time;
        this.carrier = new Carrier().getCarrier();
        // this will look up the values for distances in the .csv file
        this.computeFlightTime(deptCity, arrivalCity);
        System.out.println("ETA: " + this.flightHours + " hours " + this.flightMinutes+ " minutes"  );
        this.computeArrivalTime(departureTime);
        System.out.println("Dept: "+this.getDepartureLocalDateTime());
        System.out.println("Arrival: "+this.getArrivalLocalDateTime());
        System.out.println("Carrier: "+this.carrier);
    }

    // function to parse departure time and use this as the basis to compute a new date-time group
    public void computeArrivalTime(String departureTime) {
        // LocalDateTime.parse("2015-02-20T06:30:00");
        String deptHours = Character.toString(this.departureTime.charAt(0)) + this.departureTime.charAt(1);
        String deptMins = Character.toString(this.departureTime.charAt(2)) + this.departureTime.charAt(3);
        this.departureLocalDateTime = LocalDateTime.parse(this.year + "-" + this.month + "-" + this.day + "T" +deptHours+
                ":"+deptMins+":"+"00");
        this.arrivalLocalDateTime = departureLocalDateTime.plusHours(this.flightHours).plusMinutes(this.flightMinutes);

    }

    // method uses an example .csv file to parse expected flight times
    public void computeFlightTime(String deptCity, String arrivalCity) {
        Path path = Paths.get("src/resources/distance.csv");

        try {
            var inArray = Files.lines(path)
                    .map(e -> List.of(e.split(","))
                            .stream().collect(Collectors.toCollection(ArrayList::new)))
                    .collect(Collectors.toCollection(ArrayList::new));
            // all flights other than those in the csv will default to this flight time
            this.flightHours = 2;
            this.flightMinutes = 30;
            for (ArrayList<String> array : inArray) {
                if (array.get(0).equals(deptCity) && array.get(1).equals(arrivalCity)) {
                    System.out.println("Flight time in database: ");
                    this.flightHours = Integer.parseInt(array.get(2));
                    this.flightMinutes = Integer.parseInt(array.get(3));
                }
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.out.println("Unable to read flight data");
        }
    }

    // GETTERS AND SETTERS------------------------------------------------
    public String getDeptCity() {
        return deptCity;
    }

    public void setDeptCity(String deptCity) {
        this.deptCity = deptCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public int getFlightMinutes() {
        return flightMinutes;
    }

    public void setFlightMinutes(int flightMinutes) {
        this.flightMinutes = flightMinutes;
    }

    public int getArrivalHours() {
        return arrivalHours;
    }

    public void setArrivalHours(int arrivalHours) {
        this.arrivalHours = arrivalHours;
    }

    public int getArrivalMinutes() {
        return arrivalMinutes;
    }

    public void setArrivalMinutes(int arrivalMinutes) {
        this.arrivalMinutes = arrivalMinutes;
    }

    public LocalDateTime getDepartureLocalDateTime() {
        return departureLocalDateTime;
    }

    public void setDepartureLocalDateTime(LocalDateTime departureLocalDateTime) {
        this.departureLocalDateTime = departureLocalDateTime;
    }

    public LocalDateTime getArrivalLocalDateTime() {
        return arrivalLocalDateTime;
    }

    public void setArrivalLocalDateTime(LocalDateTime arrivalLocalDateTime) {
        this.arrivalLocalDateTime = arrivalLocalDateTime;
    }

    public String getCarrier() {
        return carrier;
    }
}
