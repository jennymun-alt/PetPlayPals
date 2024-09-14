import axios from 'axios';

export default {
    addRating(rating){
        return axios.post('http://localhost:8080/addRating', rating);
    },
    getPetsRatings(petId){
        return axios.get('http://localhost:8080/rating/' + petId)
    }
}