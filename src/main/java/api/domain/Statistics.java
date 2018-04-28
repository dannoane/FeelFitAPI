package api.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Statistics {

    @Id
    private ObjectId id;

    @Getter @Setter
    private long time;

    @Getter @Setter
    private long startTime;

    @Getter @Setter
    private long endTime;

    @Getter @Setter
    private float altitude;

    @Getter @Setter
    private float maxAltitude;

    @Getter @Setter
    private float minAltitude;

    @Getter @Setter
    private float speed;

    @Getter @Setter
    private float maxSpeed;

    @Getter @Setter
    private float minSpeed;

    @Getter @Setter
    private float avgSpeed;

    @Getter @Setter
    private float distance;

    @Getter @Setter
    private float avgPace;
}
