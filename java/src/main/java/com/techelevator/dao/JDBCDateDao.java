package com.techelevator.dao;

import com.techelevator.model.Date;
import com.techelevator.model.Pet;
import com.techelevator.model.PetDate;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCDateDao implements DateDao{

    String sqlPetsPlaying ="select * from pets inner join petsplaying on pets.petId=petsplaying.petid where petsplaying.playdateid = ?;";

    private JdbcTemplate template;
    public  JDBCDateDao(DataSource dataSource){template = new JdbcTemplate(dataSource);}

    @Override
    public List<Date> getDates(){
        String sqlGetAllDates =" Select * from playdate";
        List<Date> dateList = new ArrayList<Date>();
        SqlRowSet results = template.queryForRowSet(sqlGetAllDates);
        while(results.next()){
            String city = results.getString("city");
            long id = results.getLong("playdate_id");
            boolean showPets = false;
            String address = results.getString("address");
            LocalDate dateOfDate = results.getDate("dateofdate").toLocalDate();
            List<Pet> petsPlaying = new ArrayList<Pet>();
            Date date = new Date(city, id, address, dateOfDate, petsPlaying, showPets);


            SqlRowSet results2 = template.queryForRowSet(sqlPetsPlaying, date.getDateId());
            while(results2.next()){

                String name = results2.getString("petname");
                long petId = results2.getLong("petid");
                String breed = results2.getString("breed");
                String personality = results2.getString("personality");
                long userId = results2.getLong("user_Id");
                boolean editable = false;
                boolean attending = false;
                Pet pet = new Pet(name, petId, breed, personality, userId, editable, attending);
                petsPlaying.add(pet);

            }
            dateList.add(date);



        }
        return dateList;


    }
    @Override
    public Date getDate(long id){
        String sqlGetDate ="Select * from playdate where playdate_id =?";
        SqlRowSet result = template.queryForRowSet(sqlGetDate,id);
        Date date = null;
        if(result.next()){

            String city2 = result.getString("city");
            long id2 = result.getLong("playdate_id");
            String address2 = result.getString("address");
            LocalDate dateOfDate2 = result.getDate("dateofdate").toLocalDate();
            List<Pet> petsPlaying2 = new ArrayList<Pet>();
            boolean showPets = false;
            date = new Date(city2, id2, address2, dateOfDate2, petsPlaying2, showPets);

            System.out.println("before getdate rowset");
                SqlRowSet results2 = template.queryForRowSet(sqlPetsPlaying, date.getDateId());


            while(results2.next()){

                String name = results2.getString("petname");
                long petId = results2.getLong("petId");
                String breed = results2.getString("breed");
                String personality = results2.getString("personality");
                long userId = results2.getLong("user_id");
                boolean editable = false;
                boolean attending=false;
                System.out.println(name);
                Pet pet = new Pet(name, petId, breed, personality, userId, editable, attending);
                petsPlaying2.add(pet);

            }
            System.out.println(petsPlaying2.size());

        }
        System.out.println(date.getDatingPets().size());
        return date;
    }
    @Override
    public void saveDate(Date date){
        String sqlSaveDate ="insert into playdate(city, address,dateofdate) values (?,?,?)";
        String city = date.getCity();
        String address =date.getAddress();
        LocalDate dateOfDate =date.getDateOfDate();
        template.update(sqlSaveDate, city, address,dateOfDate);


    }
    @Override
    public void  removeDate(int id){
        String sqlRemovePetsPlaying = "delete from petsplaying where playdateid =?";
        String sqlRemoveDate =" delete from playdate where playdate_id =?";
        template.update(sqlRemovePetsPlaying, id);
        template.update(sqlRemoveDate, id);
    }
    @Override
    public void addPetToDate(PetDate petDate){
        String sqlAddDatingPet ="insert into petsplaying (petid, playdateid) values(?,?)";

        template.update(sqlAddDatingPet,  petDate.getPetId(),petDate.getDateId());
    }

    @Override
    public void removePetFromDate(long petId, long dateId){
        String sqlRemovePetFromDate ="delete from petsplaying where petid=? and playdateid =?";
        template.update(sqlRemovePetFromDate,petId, dateId);
    }

    @Override
    public List<Date> getDatesByUserId(long userId){
        String sqlGetDatesByUserId ="select * from playdate inner join petsplaying on playdate.playdate_id = petsplaying.playdateid inner join pets on pets.petid = petsplaying.petid where pets.user_id=?";
        SqlRowSet results = template.queryForRowSet(sqlGetDatesByUserId, userId);
        List<Date> dateList = new ArrayList<Date>();
        while (results.next()){
            String city = results.getString("city");
            long playdateId = results.getLong("playdate_id");
            String address = results.getString("address");
            LocalDate dateOfDate =results.getDate("dateofdate").toLocalDate();
            boolean showPet= false;
            List<Pet> petsPlaying = new ArrayList<Pet>();
            Date date = new Date(city, playdateId, address, dateOfDate, petsPlaying, showPet);
            SqlRowSet results2 = template.queryForRowSet(sqlPetsPlaying, date.getDateId());
            while(results2.next()){
                String name = results2.getString("petname");
                long petId = results2.getLong("petId");
                String breed = results2.getString("breed");
                String personality = results2.getString("personality");
                long userId2 = results2.getLong("userId");
                boolean editable = false;
                boolean attending=false;
                Pet pet = new Pet(name, petId, breed, personality, userId2, editable, attending);
                petsPlaying.add(pet);

            }
            dateList.add(date);

        }
        return dateList;
    }
    @Override
    public List<User> getAttendingUser(long dateId){
        String sqlGetAttendingUsers ="select distinct username, users.user_id  from users inner join pets on users.user_id =pets.user_id inner join petsplaying on pets.petid=petsplaying.petid where petsplaying.playdateid =? ";
        String sqlAttendingPetsOfUser ="select * from pets inner join users on pets.user_id =users.user_id inner join petsplaying on pets.petid=petsplaying.petid where petsplaying.playdateid =? and users.user_id =?";
        SqlRowSet results = template.queryForRowSet(sqlGetAttendingUsers, dateId);
        List<User> userList = new ArrayList<User>();
        while(results.next()){

            String username =results.getString("username");
            long userId = results.getLong("user_id");
            List<Pet> userPetsAttending = new ArrayList<Pet>();
            User user = new User();
            SqlRowSet results2 = template.queryForRowSet(sqlAttendingPetsOfUser,dateId,userId);
            while(results2.next()){
                long petId =results2.getLong("petid");
                String petName =results2.getString("petname");
                String breed = results2.getString("breed");
                String personality = results2.getString("personality");
                boolean editable = false;
                boolean attending = false;
                Pet pet = new Pet(petName,petId,breed,personality,userId,editable,attending);
                userPetsAttending.add(pet);
            }
            user.setUsername(username);
            user.setId(userId);
            user.setUserPets(userPetsAttending);
            userList.add(user);


        }

        return userList;

    }

    @Override
    public List<Date> getDatesByCity(String cityString) {
        String sqlGetCity = "SELECT * FROM playdate WHERE city = ?";
        SqlRowSet results = template.queryForRowSet(sqlGetCity, cityString);
        List<Date> dateList = new ArrayList<Date>();
        while (results.next()){
            String city = results.getString("city");
            long playdateId = results.getLong("playdate_id");
            String address = results.getString("address");
            LocalDate dateOfDate =results.getDate("dateofdate").toLocalDate();
            boolean showPet= false;
            List<Pet> petsPlaying = new ArrayList<Pet>();
            Date date = new Date(city, playdateId, address, dateOfDate, petsPlaying, showPet);
            dateList.add(date);
        }
        return dateList;
        }

}
