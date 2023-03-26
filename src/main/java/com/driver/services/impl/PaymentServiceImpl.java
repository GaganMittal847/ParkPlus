package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation = reservationRepository2.findById(reservationId).get();


        int resPrice = reservation.getSpot().getPricePerHour();

        int time = reservation.getNumberOfHours();

       int totalBill = resPrice*time;

        if(totalBill>amountSent){
            throw new Exception("Insufficient Amount");
        }

        Payment payment = new Payment();

        if(mode.equalsIgnoreCase(PaymentMode.CARD.toString())){

            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.CARD);
            payment.setReservation(reservation);

        }
        else if(mode.equalsIgnoreCase(PaymentMode.CASH.toString())){
            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.CASH);
            payment.setReservation(reservation);

        }
        else if(mode.equalsIgnoreCase(PaymentMode.UPI.toString())) {

            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.UPI);
            payment.setReservation(reservation);

        }
        else{
            throw new Exception("Payment mode not detected");
        }
        reservationRepository2.save(reservation);

        return payment;

    }
}
