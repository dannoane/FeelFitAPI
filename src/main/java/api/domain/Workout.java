package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users")
public class Workout {

    @Id private ObjectId id;
    @Getter @Setter @DBRef @Field("user") private User user;
    @Getter @Setter @DBRef @Field("route") private List<Location> route;
    @Getter @Setter @DBRef @Field("statistics") private Statistics statistics;
}
