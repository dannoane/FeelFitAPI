package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "routesegment")
public class RouteSegment {

    @Id
    private ObjectId id;

    @Getter @Setter
    private int activity;

    @Getter @Setter
    private List<Location> polyline;
}
