package CarRentMigration.Beans;

import java.util.Objects;

public class Driver extends Human implements Comparable<Driver>{
    private int salary;
    private int hoursWorkedThisMonth;

    public Driver() { };

    public Driver(String name, String surname, String phoneNumber, String passportNumber, int salary, int hoursWorkedThisMonth) {
        super(name, surname, phoneNumber, passportNumber);
        this.salary = salary;
        this.hoursWorkedThisMonth = hoursWorkedThisMonth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getHoursWorkedThisMonth() {
        return hoursWorkedThisMonth;
    }

    public void setHoursWorkedThisMonth(int hoursWorkedThisMonth) {
        this.hoursWorkedThisMonth = hoursWorkedThisMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return salary == driver.salary &&
                hoursWorkedThisMonth == driver.hoursWorkedThisMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, hoursWorkedThisMonth);
    }

    @Override
    public int compareTo(Driver o) {
        return getSurname().compareTo(o.getSurname());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary +
                ", hoursWorkedThisMonth=" + hoursWorkedThisMonth;
    }
}
