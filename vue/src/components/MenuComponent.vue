<template>
<div class='menu'>
    <img class='logo' src='../assets/logo.jpg' />

    <h3>Menu</h3>
        <div class='menuItem'>
            <router-link to='/searchForPets'>Search for Dates</router-link> 
        </div>
        <div class='menuItem'>
            <router-link to='/'>Add a Pet</router-link>
        </div>
        <div class='menuItem'>
            <router-link to='/messages'>Forum/Message</router-link>
        </div>
 </div> 
</template>

<script>


export default {
components: {

  }
}
</script>

<style>
.logo {
    border-radius: 50%;
    width: 100px;
}

.menuItem {
  border-bottom: 2px solid rgb(212, 217, 223);
  margin: 10px auto;
  padding-bottom: 10px;
  width: 80%;
  line-height: 2;
}
</style>