package com.example.entity;

public class ParkingPlace {

    private int idPlace;

    private String placeName;

    private PlaceStatus placeStatus;

    private Parking parking;
    public ParkingPlace() {

    }

    public ParkingPlace(int idPlace, String placeName, PlaceStatus placeStatus, Parking parking) {
        this.idPlace = idPlace;
        this.placeName = placeName;
        this.placeStatus = placeStatus;
        this.parking = parking;
    }


    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
