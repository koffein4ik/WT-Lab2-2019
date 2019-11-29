package CarRentMigration.Beans;

import java.util.Objects;

public class Admin extends Human implements Comparable<Admin> {
    private String nickname;
    private int userRequestsAnswered;
    private int salary;
    private int accessLevel;

    public Admin() { };

    public Admin(String name, String surname, String phoneNumber, String passportNumber, String nickname, int userRequestsAnswered, int salary, int accessLevel) {
        super(name, surname, phoneNumber, passportNumber);
        this.nickname = nickname;
        this.userRequestsAnswered = userRequestsAnswered;
        this.salary = salary;
        this.accessLevel = accessLevel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUserRequestsAnswered() {
        return userRequestsAnswered;
    }

    public void setUserRequestsAnswered(int userRequestsAnswered) {
        this.userRequestsAnswered = userRequestsAnswered;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return userRequestsAnswered == admin.userRequestsAnswered &&
                salary == admin.salary &&
                accessLevel == admin.accessLevel &&
                nickname.equals(admin.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickname, userRequestsAnswered, salary, accessLevel);
    }

    @Override
    public int compareTo(Admin o) {
        return nickname.compareTo(o.nickname);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", nickname='" + nickname + '\'' +
                ", userRequestsAnswered=" + userRequestsAnswered +
                ", salary=" + salary +
                ", accessLevel=" + accessLevel;
    }
}
