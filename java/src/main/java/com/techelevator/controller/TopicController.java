package com.techelevator.controller;


import com.techelevator.dao.TopicDao;
import com.techelevator.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TopicController {
    @Autowired
    TopicDao dao;

    @RequestMapping(path="/Topics", method= RequestMethod.GET)
    public List<Topic> getAllTopics(){
        List<Topic> allTopics = dao.getTopics();
        return allTopics;
    }

    @RequestMapping(path="/Topics/{userId}", method = RequestMethod.GET)
    public List<Topic> getTopicsByUserId(@PathVariable long userId){
        List<Topic> userTopics = dao.getTopicsByUserId(userId);
        return userTopics;
    }
    @RequestMapping(path="/addTopic", method = RequestMethod.POST)
    public void addTopic(@RequestBody Topic topic){
        System.out.println("Trying to add to Controller: ");
        dao.addTopic(topic);
    }

    @RequestMapping(path="/Topic/{topicId}", method = RequestMethod.GET)
    public Topic getTopicById(@PathVariable long topicId){
        System.out.println("Trying to get TOPIC ID!!!!! IN CONTROLLER");
        Topic topic = dao.getTopicById(topicId);
        return topic;
    }


}
