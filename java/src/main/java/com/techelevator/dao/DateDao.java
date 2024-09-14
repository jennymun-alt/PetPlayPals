package com.techelevator.dao;

import com.techelevator.model.Date;
import com.techelevator.model.Pet;
import com.techelevator.model.PetDate;
import com.techelevator.model.User;

import java.util.List;

public interface DateDao {

    public List<Date> getDates();
    public Date getDate(long id);
    public void saveDate(Date dateToSave);
    public void removeDate(int id);
    public void addPetToDate(PetDate petdate);
    public void removePetFromDate(long petId, long dateId);
   public List<Date> getDatesByUserId(long userId);
   public List<User> getAttendingUser(long dateId);
   public List<Date> getDatesByCity(String city);

}
