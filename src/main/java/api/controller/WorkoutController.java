package api.controller;

import api.domain.Activity;
import api.domain.Workout;
import api.repository.WorkoutRepository;
import api.security.JWTSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/workouts/me")
    public @ResponseBody HttpEntity<List<Workout>> readMyWorkouts() {

        List<Workout> workouts = workoutRepository
                .findByUsername(JWTSubject.getSubject());

        if (workouts.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/workouts")
    public @ResponseBody HttpEntity<List<Workout>> readWorkouts(@RequestParam String username) {

        List<Workout> workouts = workoutRepository
                .findByUsernameRegex(username);

        if (workouts.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/workouts")
    public @ResponseBody HttpEntity<Workout> createWorkout(@RequestBody Workout workout) {

        if (workout.getRoute() == null || workout.getStatistics() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        workout.setUsername(JWTSubject.getSubject());
        Workout savedWorkout = workoutRepository.save(workout);

        return new ResponseEntity<>(savedWorkout, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/workouts/find")
    public @ResponseBody HttpEntity<List<Workout>> findWorkouts(@RequestParam(defaultValue = "") String username,
                                                                @RequestParam double latitude,
                                                                @RequestParam double longitude,
                                                                @RequestParam(defaultValue = "10000") int radius,
                                                                @RequestParam Activity activity) {

        List<Workout> workouts = workoutRepository
                .findWorkouts(username,
                        latitude,
                        longitude,
                        radius,
                        activity);

        if (workouts.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }
}
