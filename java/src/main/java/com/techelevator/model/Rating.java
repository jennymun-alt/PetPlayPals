package com.techelevator.model;

public class Rating {
    private long raterId;
    private long raterPetId;
    private String notes;
    private int rating;


    public Rating( long raterId, long raterPetId, String notes, int rating){
        this.raterId=raterId;
        this.raterPetId=raterPetId;
        this.notes = notes;
        this.rating = rating;
    }

    public long getRaterId() {
        return raterId;
    }

    public void setRaterId(long raterId) {
        this.raterId = raterId;
    }

    public long getRaterPetId() {
        return raterPetId;
    }

    public void setRaterPetId(long raterPetId) {
        this.raterPetId = raterPetId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
