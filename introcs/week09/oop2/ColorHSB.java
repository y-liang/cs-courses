public class ColorHSB {
    private final int h, s, b;

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {

        if (h < 0 || h > 359 || s < 0 || s > 100 || b < 0 || b > 100) {
            throw new IllegalArgumentException("Out of range.");
        }

        this.h = h;
        this.s = s;
        this.b = b;

    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return ("(" + h + ", " + s + ", " + b + ")");
    }

    // Is this color a shade of gray?
    public boolean isGrayscale() {
        if (s == 0 || b == 0) return true;
        else return false;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that) {
        if (that == null) throw new IllegalArgumentException("Null object.");

        int h2 = that.h;
        int s2 = that.s;
        int b2 = that.b;

        int distance = Math.min((h - h2) * (h - h2), (360 - Math.abs(h - h2)) * (360 - Math.abs(h - h2))) + (s - s2) * (s - s2) + (b - b2) * (b - b2);

        return distance;
    }

    // Sample client (see below).
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB color = new ColorHSB(h, s, b);


        int nearest = 0;
        String name = "";
        ColorHSB nearestColor = new ColorHSB(0, 0, 0);


        while (!StdIn.isEmpty()) {
            String c2 = StdIn.readString();
            int h2 = StdIn.readInt();
            int s2 = StdIn.readInt();
            int b2 = StdIn.readInt();

            ColorHSB color2 = new ColorHSB(h2, s2, b2);
            int distance = color.distanceSquaredTo(color2);

            if (nearest == 0 || distance < nearest) {
                nearest = distance;
                name = c2;
                nearestColor = color2;
            }
        }

        StdOut.print(name + " " + nearestColor.toString());


    }

}
