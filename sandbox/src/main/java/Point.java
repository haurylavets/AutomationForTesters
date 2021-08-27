public class Point {

    public double x = 0;
    public double y = 0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point to) {
        return distance(this, to);
    }

    public static double distance(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            return Double.NaN;
        }
        double sqKatet1 = Math.pow(p2.x - p1.x, 2);
        double sqKatet2 = Math.pow(p2.y - p1.y, 2);
        return Math.sqrt(sqKatet1 + sqKatet2);
    }
}
