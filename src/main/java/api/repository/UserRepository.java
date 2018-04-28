package api.repository;

import api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'username': { $regex: '?0', $options: 'i' } }")
    List<User> findByUsername(@Param("username") String username);
}
