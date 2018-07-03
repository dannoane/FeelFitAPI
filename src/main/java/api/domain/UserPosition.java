package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserPosition {

    @Id
    private ObjectId id;

    @Getter @Setter @Indexed(unique = true, dropDups = true)
    private String username;

    @Setter @Indexed(expireAfterSeconds = 60 * 10) @CreatedDate
    private Date createdAt;

    @Getter @Setter
    private int activity;

    @Getter @Setter @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] location;
}
