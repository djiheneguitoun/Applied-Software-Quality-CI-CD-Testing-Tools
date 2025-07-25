package com.example.test;

import com.example.dao.*;
import com.example.entity.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDaoTest {

    @Test
    public void reservationTest (){
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
        ReservationStatus status = ReservationStatus.CONFIRMED ;
        reservation.setStatus(status);
        Date startTime = new Date(2024,10,17); // Instancie la date actuelle pour startTime
        Date endTime = new Date(2024,10,18);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);


        reservationDao.insertReservation(reservation);

       Reservation reservation2= reservationDao.getReservationById(1);

       assertEquals(reservation.getReservationId(),reservation2.getReservationId());










    }
}