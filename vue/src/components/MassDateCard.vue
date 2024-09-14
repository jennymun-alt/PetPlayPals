<template>
  <div>
    <div class="search">
    <form class="search-container">
    <input
      v-model="cityFilter"
      class="searchTerm" id="search-bar"
      type="text"
      placeholder="Search for a playdate near your city"
    /><a href="#"><img class="search-icon" 
    src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"></a>
    </form>
    </div>

    <div class="cards">
      <article
        class="card"
        v-bind:key="date.dateId"
        v-for="date in filteredList"
      >
        <br />
        <div class="textInCard">City</div>
        {{ date.city }}
        <br />
        <br />
        <div class="textInCard">Address</div>
        {{ date.address }}
        <br />
        <br />
        <div class="textInCard">Available Date 📅</div>
        {{ date.dateOfDate }}
        <br />
        <br />
        <button v-show="!date.showPets" v-on:click.prevent="goTo(date)">
          View Playdate Details
        </button>

        <div
          v-show="date.showPets"
          v-for="pet in allPets"
          v-bind:key="pet.petId"
        >
          {{ pet.petName }}
          <button
            v-show="!pet.attending"
            v-on:click.prevent="addPetToDate(pet, date)"
          >
            Add Pet to This Date
          </button>
          <button
            v-show="pet.attending"
            v-on:click.prevent="removePetFromDate(pet, date)"
          >
            Remove Pet From This Date
          </button>
        </div>
        <button v-show="date.showPets" v-on:click.prevent="cancelShow(date)">
          Cancel
        </button>
      </article>
    </div>
  </div>
</template>

<script>
import DateService from "../services/DateService";
import PetService from "../services/PetService";

export default {
  computed: {
    filteredList() {
      let filteredDates = this.allDates;
      if (this.cityFilter != "") {
        let filteredDates = this.allDates.filter((date) =>
          date.city.toLowerCase().includes(this.cityFilter.toLowerCase())
        );
        return filteredDates;
      }
      return filteredDates;
    },
  },

  created() {
    DateService.getAllDates().then((response) => {
      this.allDates = response.data;
      console.log("cheese" + this.allDates);
      console.log(this.allDates[0]);
    });
  },
  methods: {
    goTo(date) {
      this.$router.push(`/dates/${date.dateId}`);
    },

    removePetFromDate(pet, date) {
      const ditchingPet = {
        petId: pet.petId,
        dateId: date.dateId,
      };
      console.log(ditchingPet.petId + " " + ditchingPet.dateId);
      DateService.removePetFromDate(ditchingPet).then(() => {});
      this.$router.go();
    },
    addPetToDate(pet, date) {
      console.log("petid " + pet.petId);
      console.log("date id" + date.dateId);
      const petDate = {
        petId: pet.petId,
        dateId: date.dateId,
      };
      DateService.addPetToDate(petDate).then((response) => {
        if (response.status === 201) {
          window.alert("your Pet was Added to the date");
        }
      });
      this.$router.go();
    },
    cancelShow(date) {
      for (let i = 0; i < this.allDates.length; i++) {
        if (this.allDates[i].dateId == date.dateId) {
          this.allDates[i].showPets = false;
        }
      }
    },
    showAllPets(date) {
      PetService.getAllPets(this.$store.state.user.id).then((response) => {
        this.allPets = response.data;
        for (let i = 0; i < this.allPets.length; i++) {
          for (let j = 0; j < date.datingPets.length; j++) {
            console.log("inner for" + date.datingPets[j].petId);

            if (this.allPets[i].petId == date.datingPets[j].petId) {
              this.allPets[i].attending = true;
            }
          }
        }
      });

      for (let i = 0; i < this.allDates.length; i++) {
        if (this.allDates[i].dateId == date.dateId) {
          this.allDates[i].showPets = true;
        }
      }
    },
  },
  data() {
    return {
      allDates: [],
      allPets: [],
      cityFilter: "",
    };
  },
};
</script>

<style scoped>
.cards {
  display: grid;
  grid-template-columns: repeat(3, 32%);
  justify-content: space-between;

  padding-top: 0px;
  padding-left: 75px;
  padding-right: 75px;
}

.card {
  display: inline-block;
  grid-template-rows: max-content 200px 1fr;

  border: 2px solid gray;
  border-radius: 15px;
  background-image: url("../assets/dateCard.jpg");
  padding: 5px;
  margin: 30px;
  text-align: center;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

button {
  font-family: Tahoma, sans-serif;
  cursor: pointer;
  width: 60%;
  border: none;
  background: rgb(146, 152, 158);
  color: #fff;
  margin: 0 0 5px;
  padding: 10px;
  font-size: 15px;
}

.textInCard {
  color: darkolivegreen;
}

.removeAttendingBtn {
  color: red;
}

.search-container {
  width: 490px;
  display: block;
  margin: 0 auto;
  background: transparent;

  padding-bottom: 0px;
}

input#search-bar {
  margin: 0 auto;
  width: 100%;
  height: 45px;
  padding: 0 20px;
  font-size: 1rem;
  border: 1px solid #D0CFCE;
  outline: none;
 }
 
.search-icon {
  position: relative;
  float: right;
  width: 75px;
  height: 75px;
  top: -62px;
  right: -45px;
}


.search {
  display: flex;
  padding-top: 20px;
}


</style>