package com.techelevator.dao;


import com.techelevator.model.Topic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTopicDao implements  TopicDao {

    private JdbcTemplate template;
    public JDBCTopicDao(DataSource dataSource){template = new JdbcTemplate(dataSource);}

    @Override
    public List<Topic> getTopics(){
        String sqlGetAllTopics = "select * from topics";
        List<Topic> topicsList = new ArrayList<Topic>();
        SqlRowSet results = template.queryForRowSet(sqlGetAllTopics);
        while(results.next()){
            long topicId = results.getLong("topicid");
            String title = results.getString("title");
            String message = results.getString("message");
            long userId = results.getLong("userid");
            String userName = results.getString("username");
            Topic topic = new Topic(title,topicId, message,userName,userId);
            topicsList.add(topic);

        }
        return topicsList;
    }
    @Override
    public List<Topic> getTopicsByUserId(long userId){
        String sqlGetTopicsByUserId ="select * from topics where userid=?";
        List<Topic> topicsList = new ArrayList<Topic>();
        SqlRowSet results = template.queryForRowSet(sqlGetTopicsByUserId, userId);
        while (results.next()){
            long topicId = results.getLong("topicid");
            String title = results.getString("title");
            String message = results.getString("message");
            String userName = results.getString("username");
            Topic topic = new Topic(title,topicId, message,userName,userId);
            topicsList.add(topic);

        }
        return topicsList;
    }
    @Override
    public void addTopic(Topic topic){

        String title = topic.getTitle();
        String message = topic.getMessage();
        Long userId = topic.getUserId();
        String userName = topic.getUserName();

        String sqlAddTopic ="insert into topics (title, message, userid, username) values (?,?,?,?)";
        template.update(sqlAddTopic, title, message, userId, userName);
    }
    @Override
    public Topic getTopicById(long topicId){

        System.out.println("BEFORE SQL QUERY");

        String sqlGetTopicById="select * from topics where topicid = ?";
        SqlRowSet result = template.queryForRowSet(sqlGetTopicById, topicId);

        String title = "";
        String message = "";
        String userName = "";
        Long userId = 0L;

        while(result.next()) {

            System.out.println(result.getLong("userid"));

            message = result.getString("message");
            System.out.println(message);

            userName = result.getString("username");
            System.out.println(userName);

            userId = result.getLong("userid");
            System.out.println(userId);

            title = result.getString("title");


            System.out.println("After creating topic");
        }
        Topic topic = new Topic(title, topicId, message,userName,userId );
        return topic;


    }

}
