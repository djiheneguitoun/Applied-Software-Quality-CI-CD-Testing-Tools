package com.example.test;

import com.example.dao.*;
import com.example.entity.*;
import com.example.service.IParkingPlaceManager;
import com.example.service.ReservationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReservationManagerTest {
    private static Connection connection;
    private static DatabaseConnection dbconnection;

    @BeforeAll
    static void setUp() {
        dbconnection = new DatabaseConnection("sa", "", "org.h2.Driver", "jdbc:h2:mem:test");
        connection = dbconnection.connect();
        dbconnection.createDb(connection);
}



        @Test
    void createReservation() {

        User user = new User(1, "djihene", "sjds", "46589");
        UserDao userDao = new UserDao();
        userDao.setConn(connection);
        userDao.insertUser(user);

        Parking parking = new Parking(1, 4, "jsfjchds", "jksdjfs");
        ParkingDao parkingDao = new ParkingDao();
        parkingDao.setConn(connection);
        parkingDao.insertParking(parking);

        ParkingPlace parkingPlace = new ParkingPlace(1, "jdfjh", PlaceStatus.AVAILABLE, parking);
        ParkingPlaceDao parkingPlaceDao = new ParkingPlaceDao();
        parkingPlaceDao.setConn(connection);
        parkingPlaceDao.insertParkingPlace(parkingPlace);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.DECEMBER, 4);
        Date startTime = new Date(calendar.getTimeInMillis());
        Date endTime = new Date(startTime.getTime() + 3600000);

        Reservation reservation = new Reservation(1, user, parkingPlace, startTime, endTime, ReservationStatus.CONFIRMED);
        ReservationDao reservationDao = new ReservationDao();
        reservationDao.setConn(connection);

        IParkingPlaceManager iparkingplace = new IParkingPlaceManager() {
            @Override
            public boolean isAvailable(int idPlace, java.util.Date startTime, java.util.Date endTime) {
                return true;
            }

            @Override
            public void updateStatus(int idPlace, PlaceStatus placeStatus) {

            }
        };

        ReservationManager reservationManager = new ReservationManager(reservationDao, iparkingplace);
        reservationManager.createReservation(reservation);

        Reservation insertedReservation = reservationDao.getReservationById(reservation.getReservationId());
        assertNotNull(insertedReservation, "The inserted reservation should not be null.");
        assertEquals(reservation.getParkingPlace().getIdPlace(), insertedReservation.getParkingPlace().getIdPlace());
    }




    @Test
    void cancelReservation() {
      ReservationDao reservationDao=new ReservationDao();
        reservationDao.setConn(connection);
        Reservation existingReservation = reservationDao.getReservationById(1);
        assertNotNull(existingReservation, "Reservation should be created and not null");
        assertEquals(ReservationStatus.CONFIRMED, existingReservation.getStatus(), "Reservation should be in CONFIRMED status");
        IParkingPlaceManager iparkingplace = new IParkingPlaceManager() {
            @Override
            public boolean isAvailable(int idPlace, java.util.Date startTime, java.util.Date endTime) {
                return true;
            }

            @Override
            public void updateStatus(int idPlace, PlaceStatus placeStatus) {
            }
        };

        ReservationManager reservationManager = new ReservationManager(reservationDao, iparkingplace);


        reservationManager.cancelReservation(existingReservation.getReservationId());

        Reservation canceledReservation = reservationDao.getReservationById(existingReservation.getReservationId());
        assertNotNull(canceledReservation, "Canceled reservation should not be null");

        assertEquals(ReservationStatus.CANCELLED, canceledReservation.getStatus(), "Reservation should be CANCELLED");
    }



}
