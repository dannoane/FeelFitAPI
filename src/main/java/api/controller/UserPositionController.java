package api.controller;

import api.domain.UserPosition;
import api.repository.UserPositionRepository;
import api.security.JWTSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
public class UserPositionController {

    private final UserPositionRepository userPositionRepository;

    @Autowired
    public UserPositionController(UserPositionRepository userPositionRepository) {
        this.userPositionRepository = userPositionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userposition")
    public @ResponseBody HttpEntity<List<UserPosition>> findUsersInArea(@RequestParam double latitude,
                                                                        @RequestParam double longitude) {

        List<UserPosition> userPositions = userPositionRepository.findUsersInArea(latitude, longitude);

        return new ResponseEntity<>(userPositions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/userposition")
    public @ResponseBody HttpEntity<UserPosition> setUserPosition(@RequestBody UserPosition userPosition) {

        userPosition.setUsername(JWTSubject.getSubject());
        UserPosition savedUserPosition = userPositionRepository.save(userPosition);

        return new ResponseEntity<>(savedUserPosition, HttpStatus.OK);
    }
}
