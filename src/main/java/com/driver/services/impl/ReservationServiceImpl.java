package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user;
        ParkingLot parkingLot;
        try{
            user = userRepository3.findById(userId).get();
        }
        catch (Exception e){
            throw new Exception("Cannot make reservation");
        }
        try{
            parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        }
        catch (Exception e){
            throw new Exception("Cannot make reservation");
        }
        Reservation reservation = new Reservation();
            Spot spot =null;


            List<Spot> list = parkingLot.getSpotList();
            int max = Integer.MAX_VALUE;

            for(Spot spot1: list){
                if(spot1.getOccupied()==true) continue;
                if(numberOfWheels<=2 && spot==null || spot1.getPricePerHour() < max){
                    max = spot1.getPricePerHour();
                    spot = spot1;
                }
                else if(numberOfWheels==4 &&  spot1.getSpotType()!=SpotType.TWO_WHEELER && spot==null || spot1.getPricePerHour() < max ){
                    max = spot1.getPricePerHour();
                    spot = spot1;
                }
                else if(numberOfWheels>4 && spot1.getSpotType()!=SpotType.TWO_WHEELER && spot1.getSpotType()!=SpotType.FOUR_WHEELER && spot ==null ||
                        spot1.getPricePerHour() < max) {
                        max = spot1.getPricePerHour();
                        spot = spot1;
                }
            }
            if(spot == null){
                throw new Exception("Cannot make reservation");
            }
            spot.setOccupied(true);
            reservation.setNumberOfHours(timeInHours);
            reservation.setSpot(spot);
            reservation.setUser(user);

            spot.getReservationList().add(reservation);
            user.getReservationList().add(reservation);

            userRepository3.save(user);
            spotRepository3.save(spot);

        //reservationRepository3.save(reservation);

            return reservation;



    }
}
