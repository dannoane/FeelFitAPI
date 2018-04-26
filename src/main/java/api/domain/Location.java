package api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.ResourceSupport;

@ToString
public class Location extends ResourceSupport {

    @Id private String id;
    @Getter @Setter private double longitude;
    @Getter @Setter private double latitude;
    @Getter @Setter private long time;
    @Getter @Setter private Activity activity;
}
