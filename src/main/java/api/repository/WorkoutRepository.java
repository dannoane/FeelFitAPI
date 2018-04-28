package api.repository;

import api.domain.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, String> {

    @Query(value = "{ 'username': ?0 }")
    List<Workout> findByUsername(@Param("username") String username);

    @Query(value = "{ 'username': { $regex: '?0', $options: 'i' } }")
    List<Workout> findByUsernameRegex(@Param("username") String username);
}
