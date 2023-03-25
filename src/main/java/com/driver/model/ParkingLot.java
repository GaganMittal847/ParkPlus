package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String address;

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL)
    List<Spot>  spot = new ArrayList<>();

    public ParkingLot() {
    }

    public ParkingLot(int id, String name, String address, List<Spot> spot) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.spot = spot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Spot> getSpot() {
        return spot;
    }

    public void setSpot(List<Spot> spot) {
        this.spot = spot;
    }
}