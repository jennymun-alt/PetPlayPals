package com.techelevator.dao;

import com.techelevator.model.Topic;

import java.util.List;

public interface TopicDao {

    public List<Topic> getTopics();
    public List<Topic> getTopicsByUserId(long userId);
    public void addTopic(Topic topicToAdd);
    public Topic getTopicById(long topicId);




}
