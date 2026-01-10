/*
 * File: Human.java
 * Path: ./etc/
 *
 * Date: 2026-01-02
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Human class
 */

package etc;

import java.time.LocalDate;
import java.util.Optional;

public class Human {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    private String name;
    private String lastname;
    private SEX sex = SEX.NOT_SPECIFIED;
    private int yearOfBirth;
    private String townOfBirth;

    public enum SEX {
        MAN,
        WOMAN,
        NOT_SPECIFIED
    }

    public Human(String name, String lastname) {
        this.setName(name);
        this.setLastname(lastname);
    }

    public Human(String name, String lastname, SEX sex) {
        this(name, lastname);
        this.setSex(sex);
    }

    public Human(String name, String lastname, SEX sex, int yearOfBirth) {
        this(name, lastname, sex);
        this.setYearOfBirth(yearOfBirth);
    }

    public Human(
            String name,
            String lastname,
            SEX sex,
            int yearOfBirth,
            String townOfBirth) {
        this(name, lastname, sex, yearOfBirth);
        this.setTownOfBirth(townOfBirth);
    }

    public void setName(String name) {
        this.name = Optional.of(name).get();
    }

    public void setLastname(String lastname) {
        this.lastname = Optional.of(lastname).get();
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public void setYearOfBirth(int yearOfBirth) {
        if ((yearOfBirth >= 1901) && (yearOfBirth <= LocalDate.now().getYear()))
            this.yearOfBirth = yearOfBirth;
    }

    public void setTownOfBirth(String townOfBirth) {
        this.townOfBirth = Optional.of(townOfBirth).get();
    }

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public SEX getSex() {
        return this.sex;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }

    public String getTownOfBirth() {
        return this.townOfBirth;
    }

    public int getAge() {
        if (this.yearOfBirth < 1901) return 0;
        return LocalDate.now().getYear() - this.yearOfBirth;
    }

    @Override
    public String toString() {
        if (name.isEmpty()) return "";

        return  "Full name: " + this.getName() + " " + this.getLastname() + "\n" +
                "Sex: " + this.getSex() + "\n" +
                "Year of birth: " + this.getYearOfBirth() + "\n" +
                "Town of birth: " + this.getTownOfBirth() + "\n" +
                "Age: " + this.getAge();
    }
}
