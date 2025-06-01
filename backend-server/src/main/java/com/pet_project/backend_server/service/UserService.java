package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.user.User;

public interface UserService extends CrudService<User>{
    User findByUsername(String username);
}
