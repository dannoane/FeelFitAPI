package api.controller;

import api.domain.User;
import api.repository.UserRepository;
import api.security.JWTSubject;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
public class UserController {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/me")
    public @ResponseBody HttpEntity<User> readMyUser() {

        Optional<User> user = repository
                .findByUsername(JWTSubject.getSubject())
                .stream()
                .findFirst();

        return user
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public @ResponseBody HttpEntity<List<User>> readUsers(@RequestParam String username) {

        List<User> users = repository.findByUsername(username);

        if (users.size() > 0) {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public @ResponseBody HttpEntity<User> createUser(@RequestBody User user) {

        String encodedPassword;

        encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = repository.save(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
