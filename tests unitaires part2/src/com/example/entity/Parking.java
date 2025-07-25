package com.example.entity;

public class Parking  {

    private int parkingId;

    private String name;

    private String address;

    private int capacity;

    public Parking(int parkingId, int capacity, String address, String name) {
        this.parkingId = parkingId;
        this.capacity = capacity;
        this.address = address;
        this.name = name;
    }

    public Parking() {

    }


    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
