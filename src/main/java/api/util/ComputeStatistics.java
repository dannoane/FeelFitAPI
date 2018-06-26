package api.util;

import api.domain.RouteSegment;
import api.domain.Workout;
import com.github.davidmoten.grumpy.core.Position;

import java.util.ArrayList;
import java.util.List;

public class ComputeStatistics {

    public static double totalDistance(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getDistance())
                .sum();
    }

    private static List<Double> distanceForEachWorkoutByActivity(List<Workout> workouts, int activity) {

        List<Double> distances = new ArrayList<>();

        for (Workout w : workouts) {

            double distance = 0;
            for (RouteSegment rs: w.getRoute()) {
                if (rs == null) {
                    continue;
                }

                if (rs.getActivity() != activity) {
                    continue;
                }

                for (int index = 0; index < rs.getPolyline().size() - 1; ++index) {
                    double[] x = rs.getPolyline().get(index).getPoint();
                    double[] y = rs.getPolyline().get(index + 1).getPoint();

                    distance += new Position(x[0], x[1]).getDistanceToKm(new Position(y[0], y[1]));
                }
            }

            distances.add(distance);
        }

        return distances;
    }

    public static double distanceByActivity(List<Workout> workouts, int activity) {

        return distanceForEachWorkoutByActivity(workouts, activity)
                .stream()
                .mapToDouble(d -> d)
                .sum();
    }

    public static double minDistanceByActivity(List<Workout> workouts, int activity) {

        return distanceForEachWorkoutByActivity(workouts, activity)
                .stream()
                .mapToDouble(d -> d)
                .min()
                .orElse(0);
    }

    public static double maxDistanceByActivity(List<Workout> workouts, int activity) {

        return distanceForEachWorkoutByActivity(workouts, activity)
                .stream()
                .mapToDouble(d -> d)
                .max()
                .orElse(0);
    }

    public static double avgDistanceByActivity(List<Workout> workouts, int activity) {

        return distanceForEachWorkoutByActivity(workouts, activity)
                .stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0);
    }

    public static long totalTime(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToLong(w -> w.getStatistics().getTime())
                .sum();
    }

    private static List<Long> timeForEachWorkoutByActivity(List<Workout> workouts, int activity) {

        List<Long> times = new ArrayList<>();

        for (Workout w : workouts) {

            long time = 0;
            for (RouteSegment rs: w.getRoute()) {
                if (rs == null) {
                    continue;
                }

                if (rs.getActivity() != activity) {
                    continue;
                }

                for (int index = 0; index < rs.getPolyline().size() - 1; ++index) {
                    long x = rs.getPolyline().get(index).getTime();
                    long y = rs.getPolyline().get(index + 1).getTime();

                    time += y - x;
                }
            }

            times.add(time);
        }

        return times;
    }

    public static long timeByActivity(List<Workout> workouts, int activity) {

        return timeForEachWorkoutByActivity(workouts, activity)
                .stream()
                .mapToLong(t -> t)
                .sum();
    }

    public static double minAltitude(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getMinAltitude())
                .min()
                .orElse(0);
    }

    public static double maxAltitude(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getMaxAltitude())
                .max()
                .orElse(0);
    }

    public static double minSpeed(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getMinSpeed())
                .min()
                .orElse(0);
    }

    public static double maxSpeed(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getMaxSpeed())
                .max()
                .orElse(0);
    }

    public static double avgSpeed(List<Workout> workouts) {

        return workouts
                .stream()
                .mapToDouble(w -> w.getStatistics().getAvgSpeed())
                .average()
                .orElse(0);
    }
}
