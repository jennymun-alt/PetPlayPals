import axios from 'axios';

export default{

    addDate(date){
        return axios.post('http://localhost:8080/addDate', date);

    },
    addPetToDate(date, pet){
        return axios.post('http://localhost:8080/addPetDate', date, pet);

    },
    removePetFromDate(petDate){
        console.log("deleting pet " + petDate.petId);
        return axios.delete('http://localhost:8080/removePetDate/'+ petDate.petId + 
        '/' + petDate.dateId);
    },
    getAllDates(){
        return axios.get('http://localhost:8080/allDates');
    },

    getDateById(dateId){
        return axios.get('http://localhost:8080/getDateById/'+ dateId);
    },
    getUsersByDate(dateId){
        return axios.get('http://localhost:8080/getUserByDate/'+ dateId);

    },
    getDatesByCity(cityString){
        return axios.get('http://localhost:8080/getDateByCity/'+ cityString); 
    }
    




}