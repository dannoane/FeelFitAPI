package api.repository;

import api.domain.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "workouts", path = "workouts")
public interface WorkoutRepository extends MongoRepository<Workout, String> {
}
