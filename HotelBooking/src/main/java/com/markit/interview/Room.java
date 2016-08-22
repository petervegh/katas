package com.markit.interview;


public class Room {
    private final Integer roomNumber;
    private final Integer numOfBeds;

    public Room(Integer roomNumber, Integer numOfBeds) {
        this.roomNumber = roomNumber;
        this.numOfBeds = numOfBeds;
    }

    public Room(Room room) {
        this.roomNumber = room.getRoomNumber();
        this.numOfBeds = room.getNumOfBeds();
    }

    public Integer getRoomNumber() {
        return new Integer(roomNumber);
    }

    public Integer getNumOfBeds() {
        return new Integer(numOfBeds);
    }

    public boolean equals(Object other) {
        Room otherRoom = null;
        if(other instanceof Room) {
            otherRoom = (Room)other;
        } else {
            return false;
        }

        return (otherRoom.getNumOfBeds().equals(numOfBeds) &&
                otherRoom.getRoomNumber().equals(roomNumber));
    }
}
