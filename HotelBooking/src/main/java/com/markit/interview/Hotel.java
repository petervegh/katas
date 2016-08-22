package com.markit.interview;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hotel {
    private final Set<Room> rooms;

    public Hotel(Set<Room> rooms) {
        this.rooms = new HashSet<>(rooms);
    }

    public Set<Room> getRooms() {
        return new HashSet<>(rooms);
    }

    public Room getRoomBy(Integer roomNumber) {
        List<Room> result = rooms.stream()
                .filter(
                    (Room room) -> room.getRoomNumber().equals(roomNumber)
                ).collect(Collectors.toList());

        if(result.isEmpty()) {
            throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }

        return new Room(result.get(0));
    }

}
