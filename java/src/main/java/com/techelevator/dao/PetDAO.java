package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDAO {

public List<Pet> getPets();
public Pet getPet(int petId);
public void savePet(Pet petToSave);
public void removePet(int id);
public void editPet(Pet petToEdit);
public List<Pet> getPetsByUser(int id);


}
