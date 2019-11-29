package CarRentMigration.Beans;

import java.util.Objects;

public abstract class Vehicle {
    private int maxSpeed;
    private int weight;
    private int driveRange;
    private String brand;
    private String number;
    private String model;
    private String color;
    private int paymentPerMinute;

    private int parkingId;

    public Vehicle() {

    }

    public Vehicle(int maxSpeed, int weight, int driveRange, String brand, String number, String model, String color, int paymentPerMinute) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.driveRange = driveRange;
        this.brand = brand;
        this.number = number;
        this.model = model;
        this.color = color;
        this.paymentPerMinute = paymentPerMinute;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public int getPaymentPerMinute() {
        return paymentPerMinute;
    }

    public void setPaymentPerMinute(int paymentPerMinute) {
        this.paymentPerMinute = paymentPerMinute;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDriveRange() {
        return driveRange;
    }

    public void setDriveRange(int driveRange) {
        this.driveRange = driveRange;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return maxSpeed == vehicle.maxSpeed &&
                weight == vehicle.weight &&
                driveRange == vehicle.driveRange &&
                paymentPerMinute == vehicle.paymentPerMinute &&
                brand.equals(vehicle.brand) &&
                number.equals(vehicle.number) &&
                model.equals(vehicle.model) &&
                Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, number, model, color);
    }

    @Override
    public String toString() {
        return "MaxSpeed=" + maxSpeed +
                ", weight=" + weight +
                ", driveRange=" + driveRange +
                ", brand='" + brand + '\'' +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", paymentPerMinute=" + paymentPerMinute;
    }
}
