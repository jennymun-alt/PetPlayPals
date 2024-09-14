package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    public List<Post> getPostsByTopicId(long topicId);
    public void addPost(Post post);


}
