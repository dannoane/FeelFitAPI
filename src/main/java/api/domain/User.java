package api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.ResourceSupport;

@ToString
public class User extends ResourceSupport {

    @Id private String id;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String name;
    @Getter @Setter private String email;
}
