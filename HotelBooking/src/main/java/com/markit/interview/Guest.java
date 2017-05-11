package com.markit.interview;


public class Guest {
    private final String firstName;
    private final String lastName;

    public Guest(String lastName) {
        this.lastName = lastName;
        this.firstName = "";
    }

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Guest(Guest guest) {
        this.firstName = guest.firstName;
        this.lastName = guest.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName()  {
        return lastName;
    }

    @Override
    public boolean equals(Object other) {
        Guest otherGuest = null;
        if(other instanceof Guest) {
            otherGuest = (Guest)other;
        } else {
            return false;
        }

        return (otherGuest.getFirstName().equalsIgnoreCase(firstName) &&
                otherGuest.getLastName().equalsIgnoreCase(lastName));
    }
}
