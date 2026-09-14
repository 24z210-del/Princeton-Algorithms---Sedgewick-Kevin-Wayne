/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints {
    private int count = 0;
    private final LineSegment[] values;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        int n = points.length;
        values = new LineSegment[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < n; i++) {
            if (points[i - 1] == points[i]) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int m = k + 1; m < n; m++) {

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                && points[i].slopeTo(points[k]) == points[i].slopeTo(points[m])) {
                            Point max = points[i];
                            Point min = points[i];
                            if (points[j].compareTo(points[i]) > 0) {
                                max = points[j];
                            }
                            if (points[k].compareTo(max) > 0) {
                                max = points[k];
                            }
                            if (points[m].compareTo(max) > 0) {
                                max = points[m];
                            }
                            if (points[j].compareTo(min) < 0) {
                                min = points[j];
                            }
                            if (points[k].compareTo(min) < 0) {
                                min = points[k];
                            }
                            if (points[m].compareTo(min) < 0) {
                                min = points[m];
                            }
                            LineSegment linseg = new LineSegment(min, max);
                            values[count++] = linseg;
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[count];
        for (int i = 0; i < count; i++) {
            copy[i] = values[i];
        }
        return copy;
    }

}
