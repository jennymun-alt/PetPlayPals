<template>
  <div class='cards'>  
     <!-- {{allPets}}   -->
    
    <article class='card' v-bind:key="pet.petId" v-for="pet in allPets">
     <br />
      <div v-show="!pet.petEditable">

        <img class='headshot' src ="../assets/dogHeadshot.jpg" />
        <br />
        {{ pet.petName }}       
        <br />
        {{ pet.breed }} 
        <br />
        {{ pet.personality }}
        <br />
        
      </div>

      <div v-show="pet.petEditable">
        <input type="text" name="" id="" placeholder="New Pet Name" v-model="newName"><br>
        <input type="text" name="" id="" placeholder="New Pet Breed/Species" v-model="newBreed"><br>
        <input type="text" name="" id="" placeholder="New Pet Personality" v-model="newPersonality"><br>
        
        <br>
      </div>
     <br>

      <button v-show="!pet.petEditable" v-on:click.prevent="editPet(pet)">Edit Pet</button>
      <button v-show="pet.petEditable" v-on:click.prevent="submitEdit(pet)">Submit</button>
      
      <button v-on:click.prevent="removePet(pet)">Remove Pet</button>
      <br/>
    </article>

  </div>
</template>

<script>
import PetService from "../services/PetService";

export default {
  methods:{
    editPet(pet){
      console.log("peteditable =" +pet.petEditable)
      for(let i=0; i<this.allPets.length;i++){
        if(this.allPets[i].petId== pet.petId){
          console.log(this.allPets[i].petId)
          this.allPets[i].petEditable=true;
        }
      }
     
      
      
    },
    submitEdit(pet){


    //follow block checks if values are null and then sets them to the orginal info if they are null
      if(this.newName != null) this.changePetName =this.newName;
      else this.changePetName=pet.petName;
      console.log(" name now " + this.changePetName)
      if(this.newBreed != null) this.changePetBreed =this.newBreed;
      else this.changePetBreed=pet.breed;
      if(this.newPersonality != null) this.changePetPersonality =this.newPersonality;
      else this.changePetPersonality=pet.personality;
     
      //pet object we are sending over  
      const petChange ={
        petId: pet.petId,
        
        petName: this.changePetName,
        breed: this.changePetBreed,
        personality: this.changePetPersonality

      }
      
      console.log("pets stuff" +petChange.petName)
      PetService.editPet(petChange).then(
        () =>{
          window.alert("Your Pal's info is updated.");
           this.$router.go();
        }

      )

    },
    removePet(pet){
        const deadPet = {
           petId: pet.petId
      };
      
      if(deadPet!=null){
        PetService.removePet(deadPet).then(
          (response)=>{
            if(response.status ===201){
              //this.$router.go()
            }
          }
        )
      }
      this.$router.go();
      
    }

  },
  data() {
    return {
      
      allPets: [],
      editPetArr: [],
      changePetName: "",
      changePetBreed: "",
      changePetPersonality: ""     
      
    };
  },
  created() {
    PetService.getAllPets(this.$store.state.user.id).then((response) => {
      this.allPets = response.data;
      for(let i =0; i<this.allPets.length;i++){
        console.log(this.allPets[i])
        
      }
    });
  },
};
</script>

<style scoped>
input[type="text"]:focus {
  color: black;
  background-color: white;
}
.cards {
  display: grid;
  grid-template-columns: repeat(3, 32%);
  justify-content: space-between;
  
  padding: 75px;
  padding-top: 20px;
}

.card {
  display: inline-block;
  grid-template-rows: max-content 200px 1fr;

  border: 2px solid gray;
  border-radius: 15px;
  background-image: url('../assets/petCard.jpg');
  padding: 5px;
  margin: 30px;
  text-align: center;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.card img {
  object-fit: cover;
  width: 60%;
  height: 100%;
  margin-bottom: 10px;
}

button {
  font-family: Tahoma, sans-serif;
  cursor:pointer;
	width:60%;
	border:none;
	background:rgb(146, 152, 158);
	color:#FFF;
	margin:0 0 5px;
	padding:10px;
	font-size:15px;
}

</style>