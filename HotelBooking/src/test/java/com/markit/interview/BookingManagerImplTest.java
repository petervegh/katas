package com.markit.interview;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

public class BookingManagerImplTest {
    private Hotel hotel;

    @Before
    public void init() {
        Set<Room> rooms = new HashSet<>();
        rooms.add(new Room(101, 1));
        rooms.add(new Room(102, 2));
        rooms.add(new Room(201, 1));
        rooms.add(new Room(202, 2));

        hotel = new Hotel(rooms);
    }

    @Test
    public void testIsRoomAvailable_RoomIsNotAvailable() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);

        bookingManager.addBooking("Smith", 101, date);
        assertFalse(bookingManager.isRoomAvailable(101, date));
    }

    @Test
    public void testIsRoomAvailable_NoBookings() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);

        assertTrue(bookingManager.isRoomAvailable(101, date));
    }

    @Test
    public void testIsRoomAvailable_SomeBookings() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);

        bookingManager.addBooking("Smith", 102, date);
        bookingManager.addBooking("Piggot", 201, date);
        assertTrue(bookingManager.isRoomAvailable(101, date));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddBooking_BookingAlreadyExists() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);

        bookingManager.addBooking("Smith", 102, date);
        bookingManager.addBooking("Smith", 102, date);
    }

    @Test
    public void testGetAvailableRooms() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);
        Integer[] expected = {201, 202, 101};

        bookingManager.addBooking("Smith", 102, date);
        List<Integer> availableRooms = (List)bookingManager.getAvailableRooms(date);

        assertArrayEquals(expected, availableRooms.toArray());
    }

    @Test
    public void testGetAvailableRooms_NoBookings() throws Exception {
        Date date = new Date();
        BookingManager bookingManager = new BookingManagerImpl(hotel);
        Set<Integer> expectedRoomNumbers = hotel.getRooms().stream()
                .map(room -> room.getRoomNumber())
                .collect(Collectors.toSet());

        Set<Integer> availableRooms = (HashSet)bookingManager.getAvailableRooms(date);

        assertArrayEquals(expectedRoomNumbers.toArray(), availableRooms.toArray());
    }
}