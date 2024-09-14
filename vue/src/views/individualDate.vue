<template>
  <div class="individualdate">
    <body>
      <header>
        <h1>{{ user.username }}, Here are your upcoming Playdates!</h1>
      </header>

      <aside>
        <menu-component />
      </aside>

      <main>
        <div class="dateIntro">
          <p>
            Hope your furrrever pal enjoys their playdate with their new pal!
          </p>
        </div>  

          <date-card/>
          <!-- <pal-display /> -->
          <date-display />

      </main>
      <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>
    </body>
  </div>
</template>

<script>
import DateDisplay from "../components/DateDisplay.vue";
import DateCard from '../components/DateCard.vue';
import MenuComponent from "../components/MenuComponent.vue";
// import palDisplay from "../components/PalDisplay.vue";
import DateService from "../services/DateService";
import PetService from "../services/PetService";

export default {
  created() {
    DateService.getAllDates().then((response) => {
      this.allDates = response.data;
    });
    PetService.getAllPets(this.$store.state.user.id).then((response) => {
      this.allPets = response.data;
      for (let i = 0; i < this.allPets.length; i++) {
        console.log(this.allPets[i]);
      }
    });
  },
  data() {
    return {
      user: this.$store.state.user,
    };
  },
  components: {
    // palDisplay,
    DateDisplay,
    MenuComponent,
    DateCard,
  },
  name: "home",
  MenuComponent,
};
</script>

<style scoped>
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

.dateIntro {
  padding-top: 40px;
  text-align: center;
  font-size: 20px;
}
</style>