package com.markit.interview;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingManagerImpl implements BookingManager {
    private ConcurrentHashMap<Date, List<Booking>> bookingStore = new ConcurrentHashMap<>();
    private final Hotel hotel;

    public BookingManagerImpl(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isRoomAvailable(Integer room, Date date) {
        Room roomToBook = hotel.getRoomBy(room);

        if(bookingStore.get(date) == null) {
            return true;
        }

        return bookingStore.get(date).stream()
                .filter(
                    booking ->
                            booking.getRoom().equals(roomToBook))
                .collect(Collectors.toList())
                .isEmpty();
    }

    public void addBooking(String guest, Integer room, Date date) {
       Room roomToBook = hotel.getRoomBy(room);
        if(roomToBook != null) {
            Booking booking = new Booking(roomToBook, new Guest(guest));
            addBooking(date, booking);
        } else {
            throw new IllegalArgumentException("Invalid room number: " + room);
        }

    }

    private void addBooking(Date date, Booking booking) {
        if(bookingStore.containsKey(date)) {
            List<Booking> bookings =
                    Collections.synchronizedList(bookingStore.get(date));
            if(bookings.contains(booking)) {
                throw new IllegalArgumentException("There is already a booking like this.");
            } else {
                bookings.add(booking);
            }
        } else {
            List<Booking> bookings = new ArrayList<>();
            bookings.add(booking);

            bookingStore.put(date, bookings);
        }
    }

    public Iterable<Integer> getAvailableRooms(Date date) {
        if(bookingStore.containsKey(date)) {
            return hotel.getRooms().stream()
                    .filter(room -> isRoomAvailable(room.getRoomNumber(), date))
                    .map(room -> room.getRoomNumber())
                    .collect(Collectors.toList());
        } else {
            return hotel.getRooms().stream()
                    .map(room -> room.getRoomNumber())
                    .collect(Collectors.toSet());
        }
    }
}