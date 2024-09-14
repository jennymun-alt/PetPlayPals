package com.techelevator.dao;

import com.techelevator.model.Rating;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCRatingDao implements RatingDao{
    private JdbcTemplate template;

    public JDBCRatingDao(DataSource dataSource) {template= new JdbcTemplate(dataSource);}

    @Override
    public List<Rating> getPetsRatings(long petId){
        String swlGetRatings ="select * from ratings where ratedpetid =?";
        List<Rating> ratingsList = new ArrayList<Rating>();
        SqlRowSet results = template.queryForRowSet(swlGetRatings, petId);
        while(results.next()){
            long raterId = results.getLong("raterid");
            String notes = results.getString("notes");
            int rating = results.getInt("rating");
            Rating petRating= new Rating(raterId, petId, notes, rating);
            ratingsList.add(petRating);
        }
        return ratingsList;
    }
    @Override
    public void addRating(Rating rating){

        String sqlAddRating="insert into ratings (raterid,ratedpetid,notes,rating) values (?,?,?,?)";
        template.update(sqlAddRating,rating.getRaterId(),rating.getRaterPetId(),rating.getNotes(),rating.getRating());
    }
}
