package com.techelevator.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Date {
    private String city;
    private long dateId;
    private  String address;
    private LocalDate dateOfDate;
    private List<Pet> datingPets= new ArrayList<Pet>();
    private boolean showPets = false;

    public Date(String city, long dateId, String address, LocalDate dateOfDate, List<Pet> datingPets, boolean showPets){
        this.city = city;
        this.dateId = dateId;
        this.address =address;
        this.dateOfDate = dateOfDate;
        this.datingPets = datingPets;
        this.showPets = false;
    }

    public boolean isShowPets() {
        return showPets;
    }

    public List<Pet> getDatingPets() {
        return datingPets;
    }

    public void setDatingPets(List<Pet> datingPets) {
        this.datingPets = datingPets;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getDateId() {
        return dateId;
    }

    public void setDateId(long dateId) {
        this.dateId = dateId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfDate() {
        return dateOfDate;
    }

    public void setDateOfDate(LocalDate dateOfDate) {
        this.dateOfDate = dateOfDate;
    }
}
