package com.techelevator.controller;

import com.techelevator.dao.RatingDao;
import com.techelevator.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class RatingController {


    @Autowired
    RatingDao dao;

    @RequestMapping(path="/rating/{petId}", method =  RequestMethod.GET)
    public List<Rating> getPetsRatings(@PathVariable long petId){
        List<Rating> petsRatings = dao.getPetsRatings((petId));
        return  petsRatings;
    }

    @RequestMapping(path="/addRating", method =  RequestMethod.POST)
    public void addRating(@RequestBody Rating rating){
        System.out.println("controller");
        System.out.println(rating.getRaterPetId());
        dao.addRating(rating);
    }
}
