package com.resellerapp.service;

import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.model.dto.UserDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private HttpSession session;
    private PasswordEncoder passwordEncoder;
    private LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    public UserDTO findById(Long userId) {
        return mapToDTO(userRepository.findById(userId));
    }

    public User findSellerOrBuyerById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User validateUniqueUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User validateUniqueEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void login(String username) {
        boolean existingUser = userRepository.findByUsername(username).isPresent();
        if (existingUser) {
            this.loggedUser.setUsername(userRepository.findByUsername(username).get().getUsername());
            this.loggedUser.setId(userRepository.findByUsername(username).get().getId());
        }
    }

    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(mapToEntity(registerDTO));
    }

    public boolean checkCredentials(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }
        return user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword());
    }

    private UserDTO mapToDTO(Optional<User> byId) {

        if (byId.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(byId.get().getId());
            userDTO.setEmail(byId.get().getEmail());
            userDTO.setUsername(byId.get().getUsername());

            return userDTO;
        }
        return null;

    }

    private User mapToEntity(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return user;
    }
}
