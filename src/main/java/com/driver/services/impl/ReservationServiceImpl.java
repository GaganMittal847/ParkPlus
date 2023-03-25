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
            User user = userRepository3.findById(userId).get();

            ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();

            Spot spot =null;


            List<Spot> list = parkingLot.getSpotList();

            for(Spot spot1: list){
                if(spot1.getOccupied()==true) continue;
                if(2>=numberOfWheels && spot==null || spot1.getPricePerHour() < spot.getPricePerHour()){
                    spot = spot1;
                }
                else if(4==numberOfWheels &&  spot1.getSpotType()!=SpotType.TWO_WHEELER && spot==null || spot1.getPricePerHour() < spot.getPricePerHour() ){
                    spot = spot1;
                }
                else if(4>numberOfWheels && spot1.getSpotType()!=SpotType.TWO_WHEELER && spot1.getSpotType()!=SpotType.FOUR_WHEELER && spot ==null ||
                        spot1.getPricePerHour() < spot.getPricePerHour()) {
                    spot = spot1;
                }
            }
            spot.setOccupied(true);
            Reservation reservation = new Reservation();
            reservation.setNumberOfHours(timeInHours);
            reservation.setSpot(spot);
            reservation.setUser(user);

            reservationRepository3.save(reservation);

            return reservation;



    }
}
