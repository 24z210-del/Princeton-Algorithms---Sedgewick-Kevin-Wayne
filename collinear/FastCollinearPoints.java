import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int lineSegmentCount = 0;
    private LineSegment[] lsgmnts;

    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        int value = (int) Math.pow(n, 2);
        lsgmnts = new LineSegment[value];

        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] pointcopy = points.clone();
        Arrays.sort(pointcopy);
        for (int i = 1; i < n; i++) {
            if (pointcopy[i - 1].compareTo(pointcopy[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < n; i++) {
            Point origin = points[i];
            Point[] copy = new Point[n];
            for (int j = 0; j < n; j++) {
                copy[j] = points[j];
            }

            Comparator<Point> cmptr = origin.slopeOrder();
            Arrays.sort(copy, cmptr);

            int start = 1;
            while (start < n) {
                int end = start;
                double slope = origin.slopeTo(copy[start]);
                Point max = origin;
                Point min = origin;
                while (end < n && Double.compare(origin.slopeTo(copy[end]), slope) == 0) {
                    if (copy[end].compareTo(min) < 0) {
                        min = copy[end];
                    }
                    if (copy[end].compareTo(max) > 0) {
                        max = copy[end];
                    }
                    end++;
                }
                int count = end - start;
                if (count >= 3 && origin.compareTo(min) == 0) {
                    LineSegment lsg = new LineSegment(origin, max);
                    lsgmnts[lineSegmentCount++] = lsg;
                }
                start = end;
            }

        }
    }

    public int numberOfSegments() {
        return lineSegmentCount;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[lineSegmentCount];
        for (int i = 0; i < lineSegmentCount; i++) {
            copy[i] = lsgmnts[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
