package CarRentMigration.Beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parking implements Comparable<Parking> {
    private String location;
    private int carCapacity;
    private int truckCapacity;
    private int parkingId;
    private List<Vehicle> vehiclesOnParking = new ArrayList<>();

    public Parking() { };

    public Parking(String location, int carCapacity, int truckCapacity, int parkingId) {
        this.location = location;
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        this.parkingId = parkingId;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public int getTruckCapacity() {
        return truckCapacity;
    }

    public void setTruckCapacity(int truckCapacity) {
        this.truckCapacity = truckCapacity;
    }

    public List<Vehicle> getVehiclesOnParking() {
        return vehiclesOnParking;
    }

    public void setVehiclesOnParking(List<Vehicle> vehiclesOnParking) {
        this.vehiclesOnParking = vehiclesOnParking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return carCapacity == parking.carCapacity &&
                truckCapacity == parking.truckCapacity &&
                location.equals(parking.location) &&
                Objects.equals(vehiclesOnParking, parking.vehiclesOnParking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, carCapacity, truckCapacity, vehiclesOnParking);
    }

    @Override
    public int compareTo(Parking o) {
        return location.compareTo(o.getLocation());
    }

    @Override
    public String toString() {
        return "Location='" + location + '\'' +
                ", carCapacity=" + carCapacity +
                ", truckCapacity=" + truckCapacity;
    }
}
