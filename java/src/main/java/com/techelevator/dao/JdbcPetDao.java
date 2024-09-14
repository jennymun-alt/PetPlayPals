package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDAO {

    private JdbcTemplate template;

    public JdbcPetDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Pet> getPets() {
                String sqlGetAllPals = "SELECT * FROM pets";
        List<Pet> petList = new ArrayList<Pet>();
        SqlRowSet results = template.queryForRowSet(sqlGetAllPals);
        while(results.next()) {
            String name = results.getString("petname");
            long id = results.getLong("petId");
            String breed = results.getString("breed");
            String personality = results.getString("personality");
            long userId = results.getLong("userId");
            boolean editable = false;
            boolean attending = false;
            Pet pet = new Pet(name, id, breed, personality, userId, editable, attending);
            petList.add(pet);

        }

        return petList;
    }

    @Override
    public Pet getPet(int id) {
        System.out.println("top of get pet");
        String sqlGetPet = "SELECT * FROM pets WHERE petid = ?";
        SqlRowSet results = template.queryForRowSet(sqlGetPet, id);
        System.out.println("after rowset");
        Pet pet = null;
        if (results.next()) {
            System.out.println("in next");
            String name2 = results.getString("petname");
            long id2 = results.getLong("petId");

            String breed2 = results.getString("breed");
            String personality2 = results.getString("personality");
            System.out.println(personality2);
            long userId2 = results.getLong("user_id");
            System.out.println("after userID");
            boolean editable = false;
            boolean attending = false;
            pet = new Pet(name2, id2, breed2, personality2, userId2, editable, attending);
        }
        return pet;
    }

    @Override
    public void savePet(Pet petToSave) {
        String sqlSavePet = "INSERT INTO pets (petname, breed, personality, user_id) VALUES (?, ?, ?, ?)";
        String name = petToSave.getPetName();

        String breed = petToSave.getBreed();
        String personality = petToSave.getPersonality();
        long userId = petToSave.getUserId();
        template.update(sqlSavePet, name, breed, personality, userId);
    }

    @Override
    public void removePet(int id) {
        String sqlRemovePet = "DELETE FROM pets WHERE petid = ?";
        template.update(sqlRemovePet, id);
    }

    @Override
    public void editPet (Pet petToEdit) {
        String sqlEditPet = "UPDATE pets " + "SET petname = ?, breed = ?, personality = ? where petid =?";
        template.update(sqlEditPet, petToEdit.getPetName(), petToEdit.getBreed(), petToEdit.getPersonality(),petToEdit.getPetId());
    }
    @Override
    public List<Pet> getPetsByUser(int id){
        List<Pet> petList = new ArrayList<Pet>();
        String sqlGetPetsByUser ="Select * from pets where user_id = ?";
        SqlRowSet results = template.queryForRowSet(sqlGetPetsByUser, id);
        while(results.next()) {
            String name = results.getString("petname");
            long petId = results.getLong("petId");
            String breed = results.getString("breed");
            String personality = results.getString("personality");
            boolean editable = false;
            boolean attending = false;
            Pet pet = new Pet(name, petId, breed, personality, id,editable, attending);

            petList.add(pet);
        }


        return petList;
    }
}




















