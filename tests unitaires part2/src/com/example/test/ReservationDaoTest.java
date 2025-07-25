package com.example.test;

import com.example.dao.*;
import com.example.entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.Date;

class ReservationDaoTest {

@Test
    public void testReservation (){
    DatabaseConnection dbconnection=new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
    Connection connection=dbconnection.connect();
    dbconnection.createDb(connection);
    User user=new User(1,"djihene","sjds","46589");
    UserDao userDao=new UserDao();
    userDao.setConn(connection);
    userDao.insertUser(user);

    Parking parking=new Parking(1,4,"jsfjchds","jksdjfs");
    ParkingDao parkingDao=new ParkingDao();
    parkingDao.setConn(connection);
    parkingDao.insertParking(parking);

    ParkingPlace parkingPlace=new ParkingPlace(1,"jdfjh", PlaceStatus.AVAILABLE,parking);
    ParkingPlaceDao parkingPlaceDao=new ParkingPlaceDao();
    parkingPlaceDao.setConn(connection);
    parkingPlaceDao.insertParkingPlace(parkingPlace);
    Date startTime = new Date(2000,12,4);  // Current time as start time
    Date endTime = new Date(startTime.getTime() + 3600000);
    Reservation reservation=new Reservation(1,user,parkingPlace,startTime,endTime,ReservationStatus.CONFIRMED);
    ReservationDao reservationDao=new ReservationDao();
    reservationDao.setConn(connection);
    reservationDao.insertReservation(reservation);
    assertEquals(1,reservationDao.getReservationById(1).getReservationId());
}


}