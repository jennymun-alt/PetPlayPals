<template>
  <div class="individualTopic">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
      <body>
          <header>
              <h1>Topic Page: "{{topic.title}}"</h1>
          </header>

        <aside>
            <menu-component />
        </aside>

        <main>
          <table>

            <thead>
                <tr>
                    <th id="userPost">Initially Posted By: {{topic.userName}}</th>
                    <th>"{{topic.message}}"</th>
                </tr>  
            </thead>

            <tbody>
                <tr v-for="post in allPosts" v-bind:key="post.postId">
                    <td><i class="fa fa-reply"></i> {{post.userName}}</td>
                    <td>{{post.message}}</td>
                </tr>
            </tbody>    

        </table>

        <post-form />
        </main>

        <footer>Copyright 2021 by Me | Privacy Policy | Terms of Use</footer>

      </body>
  </div>
</template>

<script>
import MenuComponent from '../components/MenuComponent.vue';
import postForm from '../components/postForm.vue';
import TopicService from '../services/TopicService';


export default {
    created() {
        console.log(this.$route.params.topicId);

        TopicService.getTopic(this.$route.params.topicId).then(
            (response) => {
                this.topic = response.data;
                console.log(this.topic);
            }
        );

        TopicService.getAllPosts(this.$route.params.topicId).then(
            (response) => {
                this.allPosts = response.data;
            }
        )

    },

    data() {
        return {
            topic: {
                title: "",
                userName: "",
                userId: "",
                message: "",
                topicId: ""
            },

            allPosts: []
        }
    },

    components: {
        MenuComponent,
        postForm
    }

}
</script>

<style scoped>

.forum {
    display: flex;
    justify-content: center;
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 80%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;

  margin: 60px;
}

th {
  text-align: left;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #b88b5d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

#userPost {
    width: 30%;
}

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

  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
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


</style>