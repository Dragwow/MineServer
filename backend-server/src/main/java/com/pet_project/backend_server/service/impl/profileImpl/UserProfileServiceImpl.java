package com.pet_project.backend_server.service.impl.profileImpl;


import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.userProfile.UserProfileRepository;
import com.pet_project.backend_server.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;


    @Override
    public void create(UserProfile entity) {
        userProfileRepository.save(entity);
    }

    @Override
    public void update(UserProfile entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserProfile findById(Long id) {
        return null;
    }

    @Override
    public Page<UserProfile> findAll(DataTableRequest request) {
        return null;
    }


    @Override
    public Optional<UserProfile> findByUser(User user) {
        return userProfileRepository.findByUser(user); // если связь через user
    }

}
