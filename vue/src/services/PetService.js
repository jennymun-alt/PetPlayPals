import axios from 'axios';

// use axios instead of http
export default {
    addPet(pet) {
        console.log(pet.userId);
        console.log(pet.petName + " " + pet.breed);
        return axios.post('http://localhost:8080/'+ pet.userId + '/addpet', pet);
    },

    getAllPets(userId) {
        return axios.get('http://localhost:8080/' + userId);
    },
    removePet(pet){
        return axios.delete('http://localhost:8080/removepet/' + pet.petId)
    },
    editPet(pet){
        return axios.put('http://localhost:8080/editPet', pet)

    },

    getPet(petId){
        return axios.get('http://localhost:8080/getPet/' + petId)
    }

}