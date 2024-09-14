<template>
  <div class="home">
    <body>
     <header>
        <h1>{{user.username}}, welcome to your 'Pet Play Pal' profile!</h1>
     </header>
    
     <aside>
        <menu-component />
     </aside>

     <main>
       <div class='textPlaceHolder'>
         <div>Hey Pet Pal! </div>
         <p>
         Please take a look below at your Pet Play Pal information! We are so happy you created a profile with us, furrrever grateful for our friendship! 
        <br/>
        <br/>
        Add your pet pal in the box below so they can find their purrfect play-date!
        <br/>
        <br/>
        The puggle is real when your pal isn't pampurred, spoil them with a play-date! It's the purrfect oppawtunity!
        </p>
       </div>
          <h3>List of Your Current Pets</h3>
      <pal-display/>    
      <!-- <date-display/> -->

     </main>

     <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>
    </body>
  </div>
</template>

<script>
import palDisplay from "../components/PalDisplay.vue"
// import DateDisplay from '../components/DateDisplay.vue';
import MenuComponent from '../components/MenuComponent.vue';

export default {
   
  data() {
    return {
      user: this.$store.state.user
    }
  },
  components: {
    palDisplay,
    // DateDisplay,
    MenuComponent
  },
  name: "home",
    MenuComponent
  // I don't know what line 64 and 65 is doing
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

.textPlaceHolder {
  padding: 20px;
  text-align: center;
}

h3 {
  text-align: center;
}

</style>
