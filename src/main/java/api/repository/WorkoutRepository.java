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

    @Query(value = "{ 'username': { $regex: '?0', $options: 'i' }, " +
            "'route.polyline.point': { $near: { $geometry: { type: 'Point', coordinates: [?1, ?2] }, $maxDistance: ?3 } }, " +
            "'route.activity': ?4 }")
    List<Workout> findWorkouts(@Param("username") String username,
                               @Param("latitude") double latitude,
                               @Param("longitude") double longitude,
                               @Param("radius") int radius,
                               @Param("activity") int activity);
}
