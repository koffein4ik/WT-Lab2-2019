package CarRentMigration.Beans;

import java.util.Objects;

public abstract class Human {
    private String name;
    private String surname;
    private String phoneNumber;
    private String passportNumber;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Human() {

    }

    public Human(String name, String surname, String phoneNumber, String passportNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return name.equals(human.name) &&
                surname.equals(human.surname) &&
                Objects.equals(phoneNumber, human.phoneNumber) &&
                passportNumber.equals(human.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phoneNumber, passportNumber);
    }

    @Override
    public String toString() {
        return
                "Name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'';
    }
}
