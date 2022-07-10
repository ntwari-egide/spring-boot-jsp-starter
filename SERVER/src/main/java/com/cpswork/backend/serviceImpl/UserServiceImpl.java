package com.cpswork.backend.serviceImpl;

import com.cpswork.backend.dtos.UserDto;
import com.cpswork.backend.exceptions.EmailIsNotValidException;
import com.cpswork.backend.exceptions.UserNotFoundException;
import com.cpswork.backend.models.User;
import com.cpswork.backend.repositories.UserRepository;
import com.cpswork.backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.management.relation.RoleNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Value("${upload.beneficiary-profile}")
    private String userProfileDirectory;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User checkExistence(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())

            throw new UserNotFoundException("User with id :"+id+" is not found");

        return userOptional.get();
    }

    @Override
    public User findById(UUID id) {
        return checkExistence(id);
    }

    @Override
    public User signupUser( UserDto newUser) throws RoleNotFoundException, MessagingException, UnsupportedEncodingException {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(userRepository.existsByEmail(newUser.getEmail()) || userRepository.existsByUsername(newUser.getUsername())){
            throw new UserNotFoundException("user with username or email exists");
        }

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(newUser.getEmail());

        if(! matcher.matches() ) throw new EmailIsNotValidException("User email: " +newUser.getEmail()+" is not valid...");

        User saveUser = new User(
                newUser.getFirstName(),
                newUser.getSecondName(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getProfilePicturePath(),
                bcryptEncoder.encode(newUser.getPassword())
        );

        User savedUser = userRepository.save(saveUser);

        return savedUser;
    }

    @Override
    public User updateUser(UserDto updateUser, UUID id) throws RoleNotFoundException {

        /**
         * no roles, no password, no status
         */

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())

            throw new UserNotFoundException("User with id :"+id+" is not found");

        User saveUser = new User(
                updateUser.getFirstName(),
                updateUser.getSecondName(),
                updateUser.getUsername(),
                updateUser.getEmail(),
                updateUser.getProfilePicturePath(),
                bcryptEncoder.encode(updateUser.getPassword())
        );

        saveUser.setId(id);

        User savedUpdatedUser = userRepository.save(saveUser);

        return savedUpdatedUser;
    }

    @Override
    public User deleteUser(UUID id) {
        User userFound = checkExistence(id);

        userRepository.deleteById(id);

        return userFound;
    }

}
