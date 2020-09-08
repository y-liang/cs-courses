public class GreatCircle {
    public static void main(String[] args) {
        double x1 = Math.toRadians(Double.parseDouble(args[0]));
        double y1 = Math.toRadians(Double.parseDouble(args[1]));
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        double r = 6371.0;

        double x = (x2 - x1) / 2;
        double y = (y2 - y1) / 2;

        double discriminant = Math.pow(Math.sin(x), 2) + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin(y), 2);
        double distance = 2 * r * Math.asin(Math.sqrt(discriminant));

        System.out.println(distance + " kilometers");

    }
}


