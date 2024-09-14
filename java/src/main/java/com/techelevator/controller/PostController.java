package com.techelevator.controller;


import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostDao dao;

    @RequestMapping(path="/Posts/{topicId}", method = RequestMethod.GET)
    public List<Post> getPostsByTopicId(@PathVariable long topicId){

        List<Post> posts = dao.getPostsByTopicId(topicId);
        return posts;
    }

    @RequestMapping(path ="/addpost",method =  RequestMethod.POST)
    public void addPost(@RequestBody Post post){
        System.out.println("postcontroller");
        dao.addPost(post);
    }

}
