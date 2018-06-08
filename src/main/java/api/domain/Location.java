package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {

    @Id
    private ObjectId id;

    @Getter @Setter @GeoSpatialIndexed
    private double[] point;

    @Getter @Setter
    private long time;
}
