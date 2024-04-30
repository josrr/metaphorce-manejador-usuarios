package com.jmarr.users.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmarr.users.controllers.mapper.UserMapper;
import com.jmarr.users.controllers.request.UserRequest;
import com.jmarr.users.controllers.request.UpdateUserRequest;
import com.jmarr.users.controllers.response.UserResponse;
import com.jmarr.users.models.User;
import com.jmarr.users.repositories.UserRepository;
import com.jmarr.users.services.UserAlreadyExistsException;
import com.jmarr.users.services.ResourceNotFoundException;
import com.jmarr.users.services.UserServiceI;


@Service
public class UserService implements UserServiceI{

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void checkEmailAlreadyExists(String email) throws RuntimeException
    {
        Optional<User> userOpt = repository.findByEmail(email);
        if ( userOpt.isPresent() )
            throw new UserAlreadyExistsException("El correo electrónico ya existe en el sistema");
    }

    @Override
    public List<UserResponse> getAll() {
        List<User> users = (List<User>)repository.findAll();
        return mapper.toResponseList(users);
    }

    @Override
    public UserResponse getById(Long id) throws RuntimeException{
        try {
            Optional<User> user = repository.findById(id);
            return mapper.toResponse(user.get());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("El usuario no existe");
        }
    }

    @Override
    public UserResponse save(UserRequest request) {
        checkEmailAlreadyExists(request.getEmail());
        System.out.println(String.format("DATOS: %s", request));
        if ( request.getPassword() != null )
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        User entity = repository.save(mapper.toEntity(request));
        return mapper.toResponse(entity);
    }

    @Override
    public UserResponse update(UpdateUserRequest request) throws RuntimeException {
        Optional<User> userOpt = repository.findById(request.getId());
        if ( userOpt.isEmpty() )
            throw new ResourceNotFoundException("El usuario no existe");
        User user = userOpt.get();
        if ( ! user.getEmail().equals(request.getEmail()) )
            checkEmailAlreadyExists(request.getEmail());
        if ( request.getPassword() != null )
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        User updatedUser = mapper.toEntity(request);
        if ( updatedUser.getPassword() == null )
            updatedUser.setPassword(user.getPassword());
        /* esto preserva los roles del usuario */
        updatedUser.setRoles(user.getRoles());
        /* ahora salvamos los cambios */
        updatedUser = repository.save(updatedUser);
        return mapper.toResponse(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) throws RuntimeException {
        if(repository.existsById(id))
            throw new ResourceNotFoundException("El usuario no existe");
        repository.deleteById(id);
        return true;
    }

}
