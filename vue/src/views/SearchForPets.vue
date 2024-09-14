<template>
  <div class="searchForPets">
    <body>
      <header>
        <h1>Schedule your Playdate!</h1>
      </header>

      <aside>
        <menu-component />
      </aside>

      <main>


        <date-display />

        <div class="temp">See Available dates below 🐶</div>

        <mass-date-card />
      </main>

      <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>
    </body>
  </div>
</template>

<script>
import DateDisplay from "../components/DateDisplay.vue";
// import DateCard from '../components/DateCard.vue';
import MenuComponent from "../components/MenuComponent.vue";
import MassDateCard from "../components/MassDateCard.vue";

export default {
  components: {
    DateDisplay,
    // DateCard,
    MenuComponent,
    MassDateCard,
  },
};
</script>

<style scoped>
body {
  overflow-x: hidden;
  display: grid;
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

.temp {
  text-align: center;
  font-size: 25px;
  /* padding-bottom: 10px; */
}


</style>