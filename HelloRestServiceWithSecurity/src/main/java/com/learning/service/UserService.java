package com.learning.service;

import com.learning.model.User;

public interface UserService {
    User findByUsername(String username);
    User findByUserId(long userId);
}
