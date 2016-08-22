package com.markit.interview;

import java.util.HashSet;
import java.util.Set;


public class App 
{
    public static void main( String[] args ) {
        Set<Room> roomList = new HashSet<>();
        roomList.add(new Room(102, 2));
        roomList.add(new Room(101, 1));
        roomList.add(new Room(201, 1));
        roomList.add(new Room(202, 2));

        Hotel hotel = new Hotel(roomList);

    }
}
