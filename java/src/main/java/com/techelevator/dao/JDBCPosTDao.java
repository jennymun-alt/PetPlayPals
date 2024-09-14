package com.techelevator.dao;


import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCPosTDao implements  PostDao{

    private JdbcTemplate template;
    public JDBCPosTDao(DataSource dataSource) { template = new JdbcTemplate(dataSource);}

    @Override
    public List<Post> getPostsByTopicId(long topicId){
        List<Post> postList = new ArrayList<Post>();
        String sqlGetPostByTopicId ="Select * from post where topicid = ?";
        SqlRowSet results = template.queryForRowSet(sqlGetPostByTopicId, topicId);
        while(results.next()){
            long postId = results.getLong("postid");
            long userId = results.getLong("userid");
            String message = results.getString("message");
            String username = results.getString("username");
            Post post = new Post(postId, topicId, userId, message, username);
            postList.add(post);
        }
        return postList;



    }
    @Override
    public void addPost(Post post){
        System.out.println("Top of JDBC Dao");
        String sqlAddPost ="insert into post (topicid, userid, message, username) values(?,?,?,?)";
        template.update(sqlAddPost, post.getTopicId(), post.getUserId(), post.getMessage(), post.getUserName());
        System.out.println("Bottom of Dao");

    }

}
