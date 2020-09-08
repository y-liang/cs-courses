public class WorldMap {
    public static void main(String[] args) {
        int width = StdIn.readInt();
        int height = StdIn.readInt();

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.enableDoubleBuffering();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            int n = StdIn.readInt();
            double[] x = new double[n];
            double[] y = new double[n];

            for (int i = 0; i < (2 * n); i++) {
                if (i % 2 == 0) {
                    x[i / 2] = StdIn.readDouble();
                } else {
                    y[i / 2] = StdIn.readDouble();
                }
            }

            StdDraw.polygon(x, y);
        }

        StdDraw.show();

    }
}
