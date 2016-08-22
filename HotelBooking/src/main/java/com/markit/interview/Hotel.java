package com.markit.interview;


import java.util.HashSet;
import java.util.Optional;
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

    public Optional<Room> getRoomBy(Integer roomNumber) {
        return rooms.stream()
                    .filter(
                            (Room room) -> room.getRoomNumber().equals(roomNumber)
                    ).findFirst();
    }

}
