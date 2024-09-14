package com.techelevator.controller;

import com.techelevator.dao.DateDao;

import com.techelevator.model.Date;
import com.techelevator.model.Pet;
import com.techelevator.model.PetDate;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin


public class DateController {

    @Autowired
    DateDao dao;

    @RequestMapping(path = "/allDates", method = RequestMethod.GET)
    public List<Date> getAllDates(){
        List<Date> allDates = dao.getDates();
        return allDates;
    }

    @RequestMapping(path="/addDate", method = RequestMethod.POST)
    public void addDate(@RequestBody Date date){
        System.out.println(date.getDateOfDate());
        dao.saveDate(date);

    }
    @RequestMapping(path="/addPetDate", method = RequestMethod.POST)
    public void addPetToDate(@RequestBody PetDate petDate){

        dao.addPetToDate(petDate);
    }

    @RequestMapping(path="/removePetDate/{petId}/{dateId}", method = RequestMethod.DELETE)
    public void removePetFromDate(@PathVariable long petId, @PathVariable long dateId){
        System.out.println(petId+ " "+ dateId);
        dao.removePetFromDate(petId, dateId);
    }
    @RequestMapping(path="/getDateById/{dateId}", method = RequestMethod.GET)
    public Date getDate (@PathVariable long dateId){

        Date date = dao.getDate(dateId);
        return date;
    }

    @RequestMapping(path="/getUserByDate/{dateId}", method = RequestMethod.GET)
    public List<User> getAttendingUser(@PathVariable long dateId){
        List<User> userList =dao.getAttendingUser(dateId);
        System.out.println("size of list sending" +userList.size());
        return userList;
    }

    @RequestMapping(path="/getDateByCity/{cityString}", method = RequestMethod.GET)
    public List<Date> getDateByCity(@PathVariable String cityString){
        List<Date> dateList = dao.getDatesByCity(cityString);
        System.out.println("list of dates by City" + dateList);
        return dateList;
    }


}
