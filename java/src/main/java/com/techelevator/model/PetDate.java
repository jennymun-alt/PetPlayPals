package com.techelevator.model;

public class PetDate {
    private long petId;
    private long dateId;

    public PetDate(long petId, long dateId){
        this.petId = petId;
        this.dateId = dateId;

    }

    public long getPetId() {
        return petId;
    }

    public long getDateId() {
        return dateId;
    }
}
