<template>
  <div id="login" class="text-center">

    <header>
      <h1 class="h3 mb-3 font-weight-normal">Sign in to Pet Pals!</h1>
    </header>

    <main> 

      <form class="form-signin" @submit.prevent="login">
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>

        <label for="username" class="sr-onlys"></label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only"></label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <button type="submit">Sign in</button>
        <div class="register">
          <router-link :to="{ name: 'register' }">
            <button type="submit" class="registerButton">Need to Register?</button>
          </router-link>
        </div>
      </form>

      <hr class="divider" />

      <!-- <div class="search">    
          <h3>Search for Playdates</h3>
        for now on login page let's have them search by location
        <input id="typeOfPet" placeholder="Type Of Pet" type="text" />
        <input id="Personality" placeholder="Personality" type="text" />
        <input id="Location" placeholder="Location" type="text" />
          <hr class="divider" />
      </div> -->

      <div class="aboutUs">
      <h3>About Us</h3>
        <p>
          Pet Pals is the place where your pets purrrsonalities come to life
          when they play with their furrrever pals!
        </p>
        <p>
          When their day has been a little ruff, Pet Pals makes it pawssible to
          turn it around and make it fun!
        </p>
        <p>It's the leashed we can do for your fur-baby!!</p>
      </div>

    </main>

    <footer>
      <a href="https://fuzzutoys.com/">Donate Toys to Foster Dogs | </a>
      <a href="https://www.petfinder.com/">Adopt A Pet</a>
    </footer>
  </div>

</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},

  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>

.aboutUs {
  color: black;
  text-shadow: 1px 1px 2px black, 0 0 25px gainsboro, 0 0 5px burlywood;
}

#login {
  background: url("../assets/loginPageBackground.jpg");
  background-size: cover;
  background-repeat: no-repeat;

  background-attachment: fixed;

  display: block;
  overflow: hidden;

  left: -5px;
  top:-5px;
  position: absolute;
  right: -5px;
  margin:-5px;

  display: flex;
  flex-direction: column;

  justify-content: center;
  align-content: center;
  text-align: center;
  background-color: transparent;
}

form {
  padding-top: 30px;
  background-color: transparent;
}

.divider {
 border-top: 1px solid grey;
 margin-top: 60px; 
 width: 40%
}

input[id=username], input[id=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #23db98;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

.registerButton {
  background-color: #04AA6D; 
}

header {
  margin-top: 50px;
  font-size: 25px;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}

h3 {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-size: 28px;
  text-align: center;
  margin-top: 50px;
  margin-bottom: 20px;
}

h4 {
  text-align: center;
}

p {
  text-align: center;
}

main {
  margin-bottom: 100px;
}

input[id="typeOfPet"],
input[id="Personality"],
input[id="Location"] {
  background: linear-gradient(to right, lightgray, white);
  border: none;
  color: black;
  padding: 15px 80px;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  font-size: 13px;
  color: black;
  -webkit-box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
  box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
  margin: 5px 5px 5px 5px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

footer {
  position: bottom;
  padding-bottom: 20px;
  width: 100%;
  text-align: center;
  padding-top: 100px;
}

</style>