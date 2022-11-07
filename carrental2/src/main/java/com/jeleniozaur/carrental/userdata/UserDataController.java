package com.jeleniozaur.carrental.userdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userdata/")
public class UserDataController {
    @Autowired
    UserDataRepository userDataRepository;

    @GetMapping("all")
    public List<UserData> getAll() {
        return userDataRepository.getAll();
    }

    public boolean exists(int id) {
        return userDataRepository.getById(id) != null;
    }
}
