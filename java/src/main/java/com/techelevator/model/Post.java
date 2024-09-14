package com.techelevator.model;

public class Post {
    private long postId;
    private long topicId;
    private long userId;
    private String message;
    private String userName;


    public Post(long postId, long topicId, long userId, String message, String userName){
        this.postId = postId;
        this.topicId=topicId;
        this.userId=userId;
        this.message=message;
        this.userName=userName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
