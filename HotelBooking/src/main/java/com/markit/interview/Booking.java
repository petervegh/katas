package com.markit.interview;

public class Booking {
    private final Room room;
    private final Guest guest;

    public Booking(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
    }

    public Room getRoom() {
        return new Room(room);
    }

    public Guest getGuest() {
        return new Guest(guest);
    }

    @Override
    public boolean equals(Object other) {
        Booking otherBooking = null;
        if(other instanceof Booking) {
            otherBooking = (Booking)other;
        } else {
            return false;
        }

        return (otherBooking.getGuest().equals(guest) &&
                otherBooking.getRoom().equals(room));
    }
}
