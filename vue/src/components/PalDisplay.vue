<template>
  <div class="container">
    <pet-card />

    <form v-on:submit.prevent="submitPet" class="addPalForm">
      <h3>Add a Pal!</h3>
      <h4>Add your Pal and schedule a Playdate!</h4>
      <div class="form-group">
        <input id="name" placeholder="Pet Name" type="text" v-model="pet.petName" />
      </div>

      <div class="form-group">
        <input id="petType" placeholder="Breed" type="text" v-model="pet.breed" />
      </div>

      <div class="form-group">
        <input id="personality" placeholder="Personality" type="text" v-model="pet.personality" />
      </div>

      <button class="btn btn=submit">Add a Pal</button>
      <button
        class="btn btn-cancel"
        v-on:click.prevent="cancelForm"
        type="cancel"
      >
        Cancel
      </button>
    </form>

  </div>
</template>

<script>
import petService from "../services/PetService";
import PetCard from "./PetCard.vue";

export default {
  components: { PetCard },
  data() {
    return {
      pet: {
        userId: "",
        petName: "",
        typeOfPet: "",
        personality: "",
        editing:""
      },

      allPets: [],
    };
  },

  methods: {
    submitPet() {
      const newPet = {
        userId: this.$store.state.user.id,
        petName: this.pet.petName,
        breed: this.pet.breed,
        personality: this.pet.personality,
        editing: false
      };

      if (newPet != null) {
        petService.addPet(newPet).then((response) => {
          if (response.status === 201) {
            //nothing to put yet
            
          }
        });
      }
      this.$router.go()
      this.cancelForm();
    },

    cancelForm() {
      this.pet.petName = "";
      this.pet.breed = "";
      this.pet.personality = "";
    },
  },

  // created() {
  //   // const idPassed = this.$route.params.id;
  //   petService.getAllPets(this.$store.state.user.id).then((response) => {
  //     this.allPets = response.data;
  //   });
  // },
};
</script>

<style>

form {
	max-width:400px;
	width:100%;
	margin:0 auto;
	position:relative;

	background:#F9F9F9;
	padding:25px;

}

form h3 {
	color: #F96;
	display: block;
	font-size: 30px;
	font-weight: 400;
}

form h4 {
	margin: 5px 0 15px;
	display: block;
	font-size:13px;
}

.form-group input[type="text"] {
	width: 94.5%;
	border:1px solid #CCC;
	background:#FFF;
	margin:0 0 10px;
	padding:10px;
}

.btn {
  font-family: Tahoma, sans-serif;
  cursor:pointer;
	width:100%;
	border:none;
	background:grey;
	color:#FFF;
	margin:0 0 5px;
	padding:10px;
	font-size:15px;
}

</style>