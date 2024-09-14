<template>
    <div class="messages">
        <body>
            <header>
                <h1>Pet Play Pal Forum</h1>
            </header>

            <aside>
                <menu-component />
            </aside>

            <main>
            <p>Post Messages for the Pet Pal Community</p>
            <br/>
            <br/>

            <forum-display />
            <br/>
            <br/>
            <post-message-form />

            </main>

        <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>
        </body>
    </div>
</template>

<script>
import ForumDisplay from '../components/ForumDisplay.vue'
import MenuComponent from '../components/MenuComponent.vue'
import PostMessageForm from '../components/postMessageForm.vue'

export default {
  components: { MenuComponent, ForumDisplay, PostMessageForm },

}


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

p {
    text-align: center;
    font-size: 25px;
}
</style>