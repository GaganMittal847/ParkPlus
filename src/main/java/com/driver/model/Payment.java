package com.driver.model;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Boolean paymentCompleted;

    PaymentMode paymentMode;

    public Payment(int id, Boolean paymentCompleted, PaymentMode paymentMode, Reservation reservation) {
        this.id = id;
        this.paymentCompleted = paymentCompleted;
        this.paymentMode = paymentMode;
        this.reservation = reservation;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    @OneToOne
    @JoinColumn
    Reservation reservation;

    public Payment(int id, Boolean paymentCompleted, Reservation reservation) {
        this.id = id;
        this.paymentCompleted = paymentCompleted;
        this.reservation = reservation;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
