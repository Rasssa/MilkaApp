package com.example.milkaapp.services;

import com.example.milkaapp.models.User;
import com.example.milkaapp.models.modelsDto.UserDto;
import com.example.milkaapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findClientByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    public String signUpUser(User user){
        boolean userExists = userRepository.findClientByEmail(user.getEmail())
                .isPresent();
        if (userExists){
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword =  bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "";
    }
}
