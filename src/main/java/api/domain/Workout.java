package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class Workout extends ResourceSupport {

    @Id private String id;
    @Getter @Setter private User user;
    @Getter @Setter private List<Location> route;
    @Getter @Setter private Statistics statistics;
}
