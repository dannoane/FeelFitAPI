package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id private ObjectId id;
    @Getter @Setter @Indexed(unique = true) private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String name;
    @Getter @Setter @Indexed(unique = true) private String email;
}
