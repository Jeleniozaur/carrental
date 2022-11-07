package com.jeleniozaur.carrental.userdata;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDataRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserData> getAll() {
        return jdbcTemplate.query("SELECT * FROM userdata;", BeanPropertyRowMapper.newInstance(UserData.class));
    }

    public UserData getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM userdata where id = ?", BeanPropertyRowMapper.newInstance(UserData.class), id);
    }
}
