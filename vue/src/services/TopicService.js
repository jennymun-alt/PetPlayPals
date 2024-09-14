import axios from 'axios';

export default {
    getAllTopics() {
        return axios.get('http://localhost:8080/Topics');
    },

    addTopic(topic) {
        return axios.post('http://localhost:8080/addTopic', topic);
    },

    getTopic(topicId) {
        return axios.get('http://localhost:8080/Topic/' + topicId);
    },

    getAllPosts(topicId) {
        return axios.get('http://localhost:8080/Posts/' + topicId);
    },

    addPost(post) {
        console.log("Before Axios" + post);
        return axios.post('http://localhost:8080/addpost', post);
    }
}