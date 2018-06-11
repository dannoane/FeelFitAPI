package api.controller;

import api.domain.RouteSegment;
import api.domain.UserWorkoutStatistics;
import api.domain.Workout;
import api.repository.WorkoutRepository;
import api.security.JWTSubject;
import api.util.ComputeStatistics;
import com.github.davidmoten.grumpy.core.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                                                                @RequestParam int activity) {

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

    @RequestMapping(method = RequestMethod.GET, value = "/workouts/stats")
    public @ResponseBody HttpEntity<UserWorkoutStatistics> workoutStatistics(@RequestParam long startDate,
                                                                             @RequestParam long endDate) {

        UserWorkoutStatistics stats = new UserWorkoutStatistics();
        List<Workout> workouts = workoutRepository.findUserWorkoutsByTime(JWTSubject.getSubject(), startDate, endDate);

        stats.setTotalDistance(ComputeStatistics.totalDistance(workouts));
        
        stats.setTotalWalkingDistance(ComputeStatistics.distanceByActivity(workouts, 1));
        stats.setMinWalkingDistance(ComputeStatistics.minDistanceByActivity(workouts, 1));
        stats.setMaxWalkingDistance(ComputeStatistics.maxDistanceByActivity(workouts, 1));
        stats.setAvgWalkingDistance(ComputeStatistics.avgDistanceByActivity(workouts, 1));

        stats.setTotalRunningDistance(ComputeStatistics.distanceByActivity(workouts, 2));
        stats.setMinRunningDistance(ComputeStatistics.minDistanceByActivity(workouts, 2));
        stats.setMaxRunningDistance(ComputeStatistics.maxDistanceByActivity(workouts, 2));
        stats.setAvgRunningDistance(ComputeStatistics.avgDistanceByActivity(workouts, 2));

        stats.setTotalBikingDistance(ComputeStatistics.distanceByActivity(workouts, 3));
        stats.setMinBikingDistance(ComputeStatistics.minDistanceByActivity(workouts, 3));
        stats.setMaxBikingDistance(ComputeStatistics.maxDistanceByActivity(workouts, 3));
        stats.setAvgBikingDistance(ComputeStatistics.avgDistanceByActivity(workouts, 3));

        stats.setTotalTime(ComputeStatistics.totalTime(workouts));
        stats.setTimeWalking(ComputeStatistics.timeByActivity(workouts, 1));
        stats.setTimeRunning(ComputeStatistics.timeByActivity(workouts, 2));
        stats.setTimeBiking(ComputeStatistics.timeByActivity(workouts, 3));

        stats.setMinSpeed(ComputeStatistics.minSpeed(workouts));
        stats.setMaxSpeed(ComputeStatistics.maxSpeed(workouts));
        stats.setAvgSpeed(ComputeStatistics.avgSpeed(workouts));

        stats.setMinAltitude(ComputeStatistics.minAltitude(workouts));
        stats.setMaxAltitude(ComputeStatistics.maxAltitude(workouts));

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
