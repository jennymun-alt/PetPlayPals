package com.techelevator.controller;

import com.techelevator.dao.PetDAO;
import com.techelevator.dao.DateDao;
import com.techelevator.model.Date;
import com.techelevator.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class AppContoller {


    @Autowired
    PetDAO dao;


    @RequestMapping(path="/{id}/addpet", method = RequestMethod.POST)
    public void addById(@PathVariable int id,
                        @RequestBody Pet pet){
        dao.savePet(pet);

    }
    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public  List<Pet>  returnUsersStuff(@PathVariable int id){

        List<Pet> userPets= dao.getPetsByUser(id);


        return userPets;
    }
    @RequestMapping(path="/removepet/{id}", method = RequestMethod.DELETE)
    public void removePet(@PathVariable int id) {

        dao.removePet(id);
    }
    @RequestMapping(path="/editPet", method = RequestMethod.PUT)
    public void editPet(@RequestBody Pet pet){

        dao.editPet(pet);
    }

    @RequestMapping(path="/getPet/{petId}", method =  RequestMethod.GET)
    public Pet getPet(@PathVariable int petId){
        System.out.println("in the contoller");
        Pet pet = dao.getPet(petId);
        return pet;
    }








}
