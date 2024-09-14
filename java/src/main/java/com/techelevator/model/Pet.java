package com.techelevator.model;

public class Pet {
    private String petName;
    private long petId;
    private String breed;
    private String personality;
    private long userId;
    private boolean editable = false;
    private  boolean attending =false;

    public Pet(String petName, long petId, String breed, String personality, long userId, boolean editable, boolean attending) {
        this.petName = petName;
        this.petId = petId;
        this.breed = breed;
        this.personality = personality;
        this.userId = userId;
        this.editable = false;
        this.attending=false;
    }

    public boolean isAttending() {
        return attending;
    }

    public boolean getPetEditable(){return this.editable;}

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
