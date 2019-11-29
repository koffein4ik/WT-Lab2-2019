package CarRentMigration.Beans;

import java.util.Objects;

public class User extends Human implements Comparable<User> {
    private String nickname;
    private int moneyOnBalance;
    private int tripsCompleted;

    public User() {
    }

    public User(String name, String surname, String phoneNumber, String passportNumber, String nickname, int moneyOnBalance, int tripsCompleted) {
        super(name, surname, phoneNumber, passportNumber);
        this.nickname = nickname;
        this.moneyOnBalance = moneyOnBalance;
        this.tripsCompleted = tripsCompleted;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getMoneyOnBalance() {
        return moneyOnBalance;
    }

    public void setMoneyOnBalance(int moneyOnBalance) {
        this.moneyOnBalance = moneyOnBalance;
    }

    public int getTripsCompleted() {
        return tripsCompleted;
    }

    public void setTripsCompleted(int tripsCompleted) {
        this.tripsCompleted = tripsCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return moneyOnBalance == user.moneyOnBalance &&
                tripsCompleted == user.tripsCompleted &&
                nickname.equals(user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickname, moneyOnBalance, tripsCompleted);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", nickname='" + nickname + '\'' +
                ", moneyOnBalance=" + moneyOnBalance +
                ", tripsCompleted=" + tripsCompleted;
    }

    @Override
    public int compareTo(User o) {
        return nickname.compareTo(o.nickname);
    }
}
