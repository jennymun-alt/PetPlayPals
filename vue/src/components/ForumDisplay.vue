<template>
  <div class="forum">

      <table>

        <thead>
            <tr>
                <th>Topics</th>
            </tr>  
        </thead>

        <tbody>
            <tr v-for="topic in allTopics" v-bind:key="topic.topicId" v-on:click="goToTopic(topic)">
                <td>{{topic.title}}</td>
            </tr>
        </tbody>    

      </table>

  </div>
</template>

<script>
import TopicService from "../services/TopicService";

export default {
created() {
    TopicService.getAllTopics().then(
        (response) => {
            this.allTopics = response.data;
        }
    )
    
},

data() {
    return {
        allTopics: []
    }
},

methods: {
    goToTopic(topic) {
        this.$router.push(`/messages/${topic.topicId}`)
    }
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
  width: 35%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: center;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
  text-align: center;
}

td:hover {
    background-color: yellowgreen;
}



</style>