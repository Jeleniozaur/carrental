package com.jeleniozaur.carrental.car;

import com.jeleniozaur.carrental.Toolkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Car getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM car where id = ?", BeanPropertyRowMapper.newInstance(Car.class),id);
    }
    public List<Car> getAll() {
        return jdbcTemplate.query("SELECT * FROM car",BeanPropertyRowMapper.newInstance(Car.class));
    }
    public int add(Car car) {
        return jdbcTemplate.update("INSERT INTO Car (brand,model) VALUES (?,?)", car.getBrand(), car.getModel());
    }

    public int delete(Car car) {
            return jdbcTemplate.update("DELETE FROM car where id = ?", car.getId());
    }

    public int update(Car car) {
        return jdbcTemplate.update("UPDATE car SET brand = ?, model = ? where id = ?", car.getBrand(),car.getModel(),car.getId());
    }

    public int setRent(int id, Integer userId, boolean rented)
    {
        return jdbcTemplate.update("UPDATE car SET rented = ?, rented_to_user_id = ? where id = ?", Toolkit.boolToInt(rented),userId,id);
    }
}
