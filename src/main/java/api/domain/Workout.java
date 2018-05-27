package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "workouts")
public class Workout {

    @Id
    private ObjectId id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private List<Location> route;

    @Getter @Setter
    private Statistics statistics;
}
