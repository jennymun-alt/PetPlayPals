<template>
    <div class="box">
        <form class="form">
            <h3>POST A REPLY</h3>
            <div class="row">
                <div class="form-group">
                    <label for="message" class="message">Message</label>
                    <textarea class="form-control" message="message" id="message" cols="50" rows="7" v-model="reply.message" />

                </div>
            </div>
            <button class="btn-submit" v-on:click.prevent="addReply">Post Message</button>
        </form>
    </div>
</template>

<script>
import TopicService from "../services/TopicService";

export default {
methods: {
    addReply() {
        const newReply = {
            topicId: this.$route.params.topicId,
            message: this.reply.message,
            userId: this.$store.state.user.id,
            userName: this.user.username
        };
            
        TopicService.addPost(newReply).then(
            (response) => {
                console.log(response.status);
            }
        );
        this.$router.go();
    }
 },

data() {
    return {
        reply: {
            topicId: "",
            message: "",
            userId: "",
            userName: ""
        },

        user: this.$store.state.user
    }
} 
}
</script>

<style scoped>

h3 {
    color: #888888;
    font: 14px Poppins, sans-serif;
    Margin: 0px 0px 30px;
    text-align: center;
}

.form-control {
    display: flex;
    flex-direction: column;
    margin: 6px 0px 10px;
    padding: 10px;
}

.form {
    display: flex;
    flex-direction: column;
}

button {
    background-color: lightgreen;
    border: none;
    color: white;
    padding: 15px 32px;
}


</style>