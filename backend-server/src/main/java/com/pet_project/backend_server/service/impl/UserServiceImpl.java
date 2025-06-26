package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.exception.EntityNotFoundException;
import com.pet_project.backend_server.exception.IncorrectPasswordException;
import com.pet_project.backend_server.exception.NotValidDataException;
import com.pet_project.backend_server.repository.user.UserRepository;
import com.pet_project.backend_server.service.UserService;
import com.pet_project.backend_server.util.ExceptionUtil;
import com.pet_project.backend_server.util.ValidatorsUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User entity){
        checkCorrectUser(entity);
        userRepository.save(entity);
    }

    @Override
    public void update(User entity){
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<User> findAll(DataTableRequest request){
        return null;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND);
        }
        return optionalUser.get();
    }

    @Override
    public User resetPassword(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }


    private void checkCorrectUser(final User user){
        checkIdIsNotNull(user.getId());
        checkEmailIsNull(user.getEmail());
        checkEmailIsValid(user.getEmail());
        checkEmailIsExist(user.getEmail());
        checkPasswordIsNotNull(user.getPassword());

    }

    private void checkIdIsNotNull(Long id){
        if (id != null) {
            throw new NotValidDataException(ExceptionUtil.USER_ALREADY_EXISTS);
        }
    }
    private void checkEmailIsNull(final String email){
        if (email == null) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_IS_NOT_PERCENT);
        }
    }

    private void checkEmailIsValid(final String email){
        if (!email.matches(ValidatorsUtil.EMAIL_REGEX)) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_IS_NOT_VALID);
        }
    }

    private void checkEmailIsExist(final String email){
        if (userRepository.existsByEmail(email)) {
            throw new NotValidDataException(ExceptionUtil.USER_EMAIL_EXIST);
        }
    }

    private void checkPasswordIsNotNull(final String password){
        if (password == null) {
            throw new NotValidDataException(ExceptionUtil.USER_PASSWORD_IS_NOT_PERCENT);
        }
    }
}
