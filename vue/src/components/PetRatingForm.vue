<template>
  <div class="box">

      <form class="ratingform">
          <h3>Rate this pet </h3> 
          <!-- {{alreadyRated}} -->
        <div class="row">
          <div class="rating-form-group">
          <label for="topic" class="topic">Comment </label>
          <input type="text" class="form-control" topic="topic" id="topic" v-model="rating.note">
          </div>
        </div>

        <div class="row">
          <div class="rating-form-group">
          <!-- <label for="message" class="message">Message</label> -->
          <label for="cars">Choose a rating: </label>

<select name="rating" id="rating" v-model="rating.rate">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>
          
          </div>
        </div>
          <button class="btn-submit" v-on:click.prevent="add">Post Message</button>
      </form>
  </div>
</template>

<script>

import RatingService from"../services/RatingService";

export default {
    created (){
        
      RatingService.getPetsRatings(this.$route.params.petId).then(
          (response)=>{
              this.allRatings = response.data
                    for(let i =0;i<this.allRatings.length;i++){
          if(this.$store.state.user.id== this.allRatings[i].raterId){
              this.alreadyRated = true;
              
          }
          
      }
      console.log(this.alreadyRated)

          }
      )

       console.log(this.alreadyRated)
        

    },
methods: {
    add() {
        const newRating = {
            raterId: this.$store.state.user.id,
            notes: this.rating.note,
            raterPetId: this.$route.params.petId,
            rating: this.rating.rate
        };
        if(!this.alreadyRated){
                console.log(newRating)
        RatingService.addRating(newRating).then(
            (response) =>{
                console.log(response.status);
            }
        )

        }else{
            alert("Already Rated this Pal");
            
        }
        this.$router.go();
    
            

        
    }
 },

data() {
    return {
        alreadyRated: false,
        allRatings: [],
        rating: {
            raterId: "",
            raterPetId: "",
            notes: "",
            rating: ""
        },

        user: this.$store.state.user
    }
} 
}
</script>

<style scoped>

h3 {
    color: dimgray;
    font: 18px Poppins, sans-serif;
    Margin: 0px 0px 30px;
    text-align: center;
}

.rating-form-control {
    display: flex;
    flex-direction: column;
    /* margin: 6px 0px 0px; */
    /* padding: 10px; */
}

.ratingForm {
    display: flex;
    flex-direction: column;
}

button {
    background-color: tomato;
    border: none;
    color: white;
    padding: 10px 76px;
    margin-top: 10px;
    
}

.box {
    display: flex;
    justify-content: center;
    border-style: outset;
}

.rating-form-group {
    padding-bottom: 10px;
}
</style>