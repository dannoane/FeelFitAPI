package api.repository;

import api.domain.UserPosition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPositionRepository extends MongoRepository<UserPosition, String> {

    @Query(value = "{ 'location': { $near: { $geometry: { type: 'Point', coordinates: [?0, ?1] }, $maxDistance: 20000 } } }")
    List<UserPosition> findUsersInArea(@Param("latitude") double latitude,
                                       @Param("longitude") double longitude);
}
