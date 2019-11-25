package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Persondataaccess")
public class Persondataaccess implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    private static List<Person> DB = new ArrayList<>();

    @Autowired
    public Persondataaccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {

        final String sqlquery = "INSERT INTO person (id , name) VALUES('"+id+"' , '"+person.getName()+"');";






//        DB.add(new Person(id, person.getName()));
//        return 1;
    }

    @Override
    public List<Person> selectAllpeople() {
        final String sqlquery = "SELECT * FROM Person";
        List<Person> allpeople = jdbcTemplate.query(sqlquery, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });

        return allpeople;

//        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {

//        final String sqlquery = "SELECT * FROM Person WHERE id = "+"'"+id+"'";
//        List<Person> person = jdbcTemplate.query(sqlquery,  (resultSet, i) -> {
//            UUID pid = UUID.fromString(resultSet.getString("id"));
//            String name = resultSet.getString("name");
//            return new Person(pid, name);
//        });

        //return Optional.ofNullable(person.get(0));


        final String sqlquery = "SELECT * FROM Person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sqlquery,new Object[]{id} ,  (resultSet, i) -> {
            UUID pid = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(pid, name);
        });

        return Optional.ofNullable(person);

//        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();


    }

    @Override
    public int updatePersonById(UUID id) {
        return 0;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

//    @Override
//    public int insertperson(Person person) {
//        DB.add(new Person(person.getName()));
//        return 0;
//    }
}
