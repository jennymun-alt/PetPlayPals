<!-- I don't know what this file is doing, will be deleted later when we figure things out. -->

<template>
  <div id="main">
    <div id="input-section">
      <form v-on:submit.prevent="savePet">
        Name: <input id="pet-name" v-model="pet.name" /> <br /><br />
        Breed-Species: <input id="pet-breed" v-model="pet.breed" /> <br /><br />
        Personality: <input id="pet-personality" v-model="pet.personality" />
        <br /><br />
        <br />
        <br />

        <button id="dog-add-btn">Add A Pal!</button>
      </form>
    </div>
  </div>
</template>

<script>
import puppyService from "@/services/PuppyService";
import pupPicService from "@/services/PupPicService";

export default {
  data() {
    return {
      pet: {
        name: "",
        breed: "",
        personality: "",
        // image: "",
      },
      // pupImage: "",
    };
  },
  methods: {
    savePet() {
      const newPet = {
        name: this.pet.name,
        breed: this.pet.weight,
        personality: this.pet.personality,
      };
      petService.create(newPet).then((response) => {
        if (response.status === 201) {
          alert("Pal Added!");
          this.$router.push("/display");
        }
      });
    },
  },
};
</script>

<style>
#input-section {
  margin: 20px;
  padding: 20px;
  width: 20%;
}
</style>