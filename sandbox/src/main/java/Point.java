public class Point {

    public double x = 0;
    public double y = 0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point to) {
        if (to == null) {
            return Double.NaN;
        }
        double sqKatet1 = Math.pow(this.x - to.x, 2);
        double sqKatet2 = Math.pow(this.y - to.y, 2);
        return Math.sqrt(sqKatet1 + sqKatet2);
    }
}
