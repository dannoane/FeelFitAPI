package api.domain;

import lombok.Getter;
import lombok.Setter;

public class UserWorkoutStatistics {

    @Getter @Setter
    private double totalDistance;

    @Getter @Setter
    private double totalWalkingDistance;

    @Getter @Setter
    private double minWalkingDistance;

    @Getter @Setter
    private double maxWalkingDistance;

    @Getter @Setter
    private double avgWalkingDistance;

    @Getter @Setter
    private double totalRunningDistance;

    @Getter @Setter
    private double minRunningDistance;

    @Getter @Setter
    private double maxRunningDistance;

    @Getter @Setter
    private double avgRunningDistance;

    @Getter @Setter
    private double totalBikingDistance;

    @Getter @Setter
    private double minBikingDistance;

    @Getter @Setter
    private double maxBikingDistance;

    @Getter @Setter
    private double avgBikingDistance;

    @Getter @Setter
    private long totalTime;

    @Getter @Setter
    private long timeWalking;

    @Getter @Setter
    private long timeRunning;

    @Getter @Setter
    private long timeBiking;

    @Getter @Setter
    private double minSpeed;

    @Getter @Setter
    private double maxSpeed;

    @Getter @Setter
    private double avgSpeed;

    @Getter @Setter
    private double minAltitude;

    @Getter @Setter
    private double maxAltitude;
}
