package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String title;
    private long topicId;
    private String message;
    private String userName;
    private long userId;
    private List<Post> topicPosts = new ArrayList<Post>();

    public Topic(String title, long topicId, String message, String userName, long userId ){
        this.title=title;
        this.topicId=topicId;
        this.message=message;
        this.userName=userName;
        this.userId=userId;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
