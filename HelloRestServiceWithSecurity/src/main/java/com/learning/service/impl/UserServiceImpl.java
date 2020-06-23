package com.learning.service.impl;

import com.learning.model.ROLE_ENUM;
import com.learning.model.Role;
import com.learning.model.User;
import com.learning.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log
public class UserServiceImpl implements UserService {
    private Map<Long, User> mapByIdRepository = new HashMap<>();
    private Map<String, User> mapByNameRepository = new HashMap<>();

    public UserServiceImpl(){
        log.info("UserServiceImpl.buildRepository()");
        buildRepository();
    }

    @Override
    public User findByUsername(String username) {
        return mapByNameRepository.get(username);
    }

    @Override
    public User findByUserId(long userId) {
        return mapByIdRepository.get(userId);
    }

    private void buildRepository(){
        //password: admin
        User admin = new User(1L, "admin",
                "$2y$12$iVLtadtk5HyKoYGS6bjzvOC6vyrmpX9e5Yqls69zv5J9DIbn4vzea",
                "admin", "admin",
                "admin@gmail.com", true,
                new Role(ROLE_ENUM.ADMIN));

        //password: user
        User user = new User(1L, "user",
                "$2y$12$xOD46HEVVuCjlp3SIomjQ.AXiwQ48o8KPnByJAJURrMOLcwCEJij2",
                "user", "user",
                "user@gmail.com", true,
                new Role(ROLE_ENUM.USER));

        mapByIdRepository.put(1L, admin);
        mapByIdRepository.put(2L, user);

        mapByNameRepository.put("admin", admin);
        mapByNameRepository.put("user", user);
    }
}
