<template>
  <div class="individualdate">
    <body>
      <header>
        <h1>Welcome to {{pet.petName}}'s page</h1>
      </header>

      <aside>
        <menu-component />
      </aside>

      <main>
        <div class="petIntro">
          
          <h3>
            {{pet.petName}}'s ratings
          </h3>

           <p>Average Rating: {{aveRating}}</p>

          <div class="ratingTable">
          <table>
            <thead>
              <tr>
                <th>Comment</th>
                <th>Rating</th>
              </tr>
            </thead>
              
                <tr v-for ="review in allRatings" v-bind:key="review.raterId">
                  <td>{{review.notes}}</td>
                  <td>{{review.rating}}</td>
                </tr>                          
          </table>
          </div>
          </div>  

          <pet-rating-form/>
          <button class="btn-goBack" v-on:click.prevent="goBack">Go Back</button>

      </main>
      <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>
    </body>
  </div>
</template>

<script>


import MenuComponent from "../components/MenuComponent.vue";
import RatingService from "../services/RatingService";

// import palDisplay from "../components/PalDisplay.vue";

import PetService from "../services/PetService";
import PetRatingForm from '../components/PetRatingForm.vue';

export default {
  methods:{
goBack(){
  this.$router.go(-1);

},
  },
  created() {
    //   var aveRating =0;
    //   console.log(aveRating)
      PetService.getPet(this.$route.params.petId).then(
          (response) =>{
              this.pet = response.data

          }
      )
    
      RatingService.getPetsRatings(this.$route.params.petId).then(
          (response)=>{
              this.allRatings = response.data;
              var sum=0;
              for(let i =0; i<this.allRatings.length;i++){
                sum+=this.allRatings[i].rating;
              }
              if(this.allRatings.length>0){
                this.aveRating=sum/this.allRatings.length;
              }
              else this.allRatings = 0;
              

              

          }
      )
    //   var sum=0;
    //   for(let i =0;i<this.allRatings.length;i++){
    //       sum = sum+this.allRatings[i].rating;

    //   }
    //   aveRating =sum/this.allRatings.length;
      

  },
  data() {
    return {
      aveRating: "",
        allRatings: [],
        pet: {
            petId: "",
            petName: "",
            breed: "",
            personality: "",
            userId: ""

        },
      user: this.$store.state.user,
    };
  },
  components: {
    // palDisplay,

    MenuComponent,
    PetRatingForm,

  },
  name: "home",
  MenuComponent,
};
</script>

<style scoped>
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
thead {
  font-weight: bold;
  color: #fff;
  background: #b88b5d;
}
table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 80%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;

  margin: 60px;

}
#userPost {
    width: 30%;
}
/* here */
.petIntro {
  display: flex;
  display: inline-block;
  justify-content: center;
  align-items: center;
}

.ratingTable {
  display: flex;
  justify-content: center;
  width: 550px;
}

tr {
  text-align: center;
}

.btn-goBack {
  margin: 20px;
}

h3 {
  text-align: center;
  margin-top: 40px;
}

p {
  text-align: center;
}
/* to here */

body {
  overflow-x: hidden;
  display: grid;
  font-family: Tahoma, sans-serif;
  grid-template-columns: 200px 1fr 1fr 1fr;
  grid-template-areas: 
  "header header header header"
  "aside main main main"
  "aside footer footer footer";
}

header {
  grid-area: header;
  text-align: center;
  font-family: monospace;
  font-size: 23px;
  font-style: italic;
  border-bottom: darkslategray solid;
}

main {
  grid-area: main;
  background: linear-gradient(to right, palegoldenrod, white);

  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

aside {
  grid-area: aside;
  text-align: center;
  border-right: darkslategray solid;
}

footer {
  grid-area: footer;  
  position: bottom;
  bottom: 0;
  width: 100%;
  height: 2.5rem;
  text-align: center;
  padding-top: 100px;
  background: linear-gradient(to right, palegoldenrod, white); 
}

</style>