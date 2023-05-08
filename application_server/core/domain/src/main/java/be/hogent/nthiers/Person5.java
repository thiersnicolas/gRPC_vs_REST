package be.hogent.nthiers;

public class Person5 {
    String firstname;
    String lastname;
    int birthYear;
    int birtMonth;
    int birtDay;

    protected Person5() {
    }

    public Person5(String firstname, String lastname, int birthYear, int birtMonth, int birtDay) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthYear = birthYear;
        this.birtMonth = birtMonth;
        this.birtDay = birtDay;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirtMonth() {
        return birtMonth;
    }

    public int getBirtDay() {
        return birtDay;
    }
}
