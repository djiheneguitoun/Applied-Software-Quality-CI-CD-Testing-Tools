package com.example.test;

import com.example.dao.*;
import com.example.entity.*;
import com.example.service.IParkingPlaceManager;
import com.example.service.ReservationManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservationManagerTest{
    @Test
    public void createReservationTest (){
        Reservation reservation = new Reservation();
        Parking parking = new Parking();
        parking.setParkingId(1);
        parking.setAddress("alger");
        parking.setCapacity(10);
        parking.setName("sss");


        ParkingPlace parkingPlace = new ParkingPlace();
        parkingPlace.setIdPlace(1);
        PlaceStatus placeStatus = PlaceStatus.AVAILABLE ;
        parkingPlace.setPlaceStatus(placeStatus);
        parkingPlace.setPlaceName("pppp");
        parkingPlace.setParking(parking);

        User user = new User();
        user.setUserId(1);
        user.setName("nesrine");
        user.setEmail("nesrine@gmail.com");
        user.setPhone("0612345678");

        reservation.setReservationId(1);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);
        ReservationStatus status = ReservationStatus.CONFIRMED ;
        reservation.setStatus(status);
        Date startTime = new Date(2024,10,17);
        Date endTime = new Date(2024,10,18);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);







        ReservationDao reservationDao = Mockito.mock(ReservationDao.class);

        IParkingPlaceManager iplaceManager = Mockito.mock(IParkingPlaceManager.class);
        when(iplaceManager.isAvailable(parkingPlace.getIdPlace(),startTime,endTime)).thenReturn(true);

        ReservationManager reservationManager = new ReservationManager();
        reservationManager.setiPlaceManager(iplaceManager);
        reservationManager.setReservationDao(reservationDao);
        reservationManager.createReservation(reservation);

        verify(reservationDao).insertReservation(reservation);
        verify(iplaceManager).updateStatus(1, PlaceStatus.RESERVED);
    }

    @Test
    public void cancelReservationTest (){
        Reservation reservation = new Reservation();
        Parking parking = new Parking();
        parking.setParkingId(1);
        parking.setAddress("alger");
        parking.setCapacity(10);
        parking.setName("sss");


        ParkingPlace parkingPlace = new ParkingPlace();
        parkingPlace.setIdPlace(1);
        PlaceStatus placeStatus = PlaceStatus.AVAILABLE ;
        parkingPlace.setPlaceStatus(placeStatus);
        parkingPlace.setPlaceName("pppp");
        parkingPlace.setParking(parking);

        User user = new User();
        user.setUserId(1);
        user.setName("nesrine");
        user.setEmail("nesrine@gmail.com");
        user.setPhone("0612345678");

        reservation.setReservationId(1);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);
        ReservationStatus status = ReservationStatus.PENDING ;
        reservation.setStatus(status);
        Date startTime = new Date(2024,10,17);
        Date endTime = new Date(2024,10,18);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        
        ReservationDao reservationDao = Mockito.mock(ReservationDao.class);
        when(reservationDao.getReservationById(1)).thenReturn(reservation);
        ReservationManager reservationManager = new ReservationManager();
        reservationManager.setReservationDao(reservationDao);
        reservationManager.cancelReservation(1);
        verify(reservationDao).updateReservationStatus(1,ReservationStatus.CANCELLED);

    }


    // test d'integration
    @Test
    public void reservationCancelledtest() {
        //ouverture de connexion
        DatabaseConnection c = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        Connection connection = c.connect();
        c.createDb(connection);


        Parking parking = new Parking();
        parking.setParkingId(1);
        parking.setAddress("alger");
        parking.setCapacity(10);
        parking.setName("sss");

        ParkingDao parkingDao = new ParkingDao();
        parkingDao.setConn(connection);
        parkingDao.insertParking(parking);


        ParkingPlaceDao  parkingPlaceDao = new ParkingPlaceDao();
        parkingPlaceDao.setConn(connection);
        ParkingPlace parkingPlace = new ParkingPlace();
        parkingPlace.setIdPlace(1);
        PlaceStatus placeStatus = PlaceStatus.AVAILABLE ;
        parkingPlace.setPlaceStatus(placeStatus);
        parkingPlace.setPlaceName("pppp");
        parkingPlace.setParking(parking);


        parkingPlaceDao.insertParkingPlace(parkingPlace);

        User user = new User();
        user.setUserId(1);
        user.setName("nesrine");
        user.setEmail("nesrine@gmail.com");
        user.setPhone("0612345678");
        UserDao userDao = new UserDao();
        userDao.setConn(connection);
        userDao.insertUser(user);


        ReservationDao reservationDao = new ReservationDao();
        reservationDao.setConn(connection);
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setUser(user);
        reservation.setParkingPlace(parkingPlace);
        ReservationStatus status = ReservationStatus.PENDING ;
        reservation.setStatus(status);
        Date startTime = new Date(2024,10,17); // Instancie la date actuelle pour startTime
        Date endTime = new Date(2024,10,18);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);


        reservationDao.insertReservation(reservation);

        ReservationManager reservationManager = new ReservationManager();
        reservationManager.setReservationDao(reservationDao);
        reservationManager.cancelReservation(1);
        assertEquals(reservationDao.getReservationById(1).getStatus(), ReservationStatus.CANCELLED);
    }

}
