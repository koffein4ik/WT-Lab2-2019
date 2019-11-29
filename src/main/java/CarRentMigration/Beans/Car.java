package CarRentMigration.Beans;

import java.util.Objects;

public class Car extends Vehicle implements Comparable<Car>, Cloneable {
    private int PassengerCapacity;
    private int accelerationTime;

    public Car() { };

    public Car(int maxSpeed, int weight, int driveRange, String brand, String number, String model, String color, int paymentPerMinute, int passengerCapacity, int accelerationTime) {
        super(maxSpeed, weight, driveRange, brand, number, model, color, paymentPerMinute);
        PassengerCapacity = passengerCapacity;
        this.accelerationTime = accelerationTime;
    }

    public int getPassengerCapacity() {
        return PassengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        PassengerCapacity = passengerCapacity;
    }

    public int getAccelerationTime() {
        return accelerationTime;
    }

    public void setAccelerationTime(int accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return PassengerCapacity == car.PassengerCapacity &&
                accelerationTime == car.accelerationTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), PassengerCapacity, accelerationTime);
    }

    @Override
    public int compareTo(Car o) {
        return getBrand().compareTo(o.getBrand());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", passengerCapacity=" + PassengerCapacity +
                ", accelerationTime=" + accelerationTime;
    }
}
