import java.awt.Color;

public class KernelFilter {

    // Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture) {
        double[][] weights = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = 1;
                } else {
                    weights[i][j] = 0;
                }
            }
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies a Gaussian blur filter to the given
    public static Picture gaussian(Picture picture) {
        double[][] weights = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = (double) 4 / 16;
                } else if (i == j || i + j == 2) { // ???
                    weights[i][j] = (double) 1 / 16;
                } else {
                    weights[i][j] = (double) 2 / 16;
                }

            }
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        double[][] weights = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = 5;
                } else if (i == j || i + j == 2) {
                    weights[i][j] = 0;
                } else {
                    weights[i][j] = -1;
                }
            }

        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        double[][] weights = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = 8;
                } else {
                    weights[i][j] = -1;
                }
            }
        }

        return kernel(picture, weights);

    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        double[][] weights = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    weights[i][j] = -2;
                } else if (i == 1 && j == 1) {
                    weights[i][j] = 1;
                } else if (i == 0) {
                    weights[i][j] = weights[i][j - 1] + 1;
                } else if (i == 2 && j == 1) {
                    weights[i][j] = 1;
                } else {
                    weights[i][j] = weights[i - 1][j] + 1;
                }
            }
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        double[][] weights = new double[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    weights[i][j] = (double) 1 / 9;
                } else {
                    weights[i][j] = 0;
                }
            }
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies an arbitrary kernel filter to the given
    // picture.
    private static Picture kernel(Picture picture, double[][] weights) {

        // target picture
        Picture target = new Picture(picture.width(), picture.height());

        int wHeight = weights.length;
        int wWidth = weights[0].length;

        int cRow = (wHeight - 1) / 2; // center of kernel
        int cCol = (wWidth - 1) / 2; // center of kernel

        // modulo, a % b = (a % b + b) % b to prevent negative remainder
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {

                double red = 0.0, green = 0.0, blue = 0.0;

                for (int i = 0; i < wHeight; i++) { // row
                    int pRow = ((row - cRow + i) % picture.height() + picture.height()) % picture.height();

                    for (int j = 0; j < wWidth; j++) { // column
                        int pCol = ((col - cCol + j) % picture.width() + picture.width()) % picture.width();

                        Color pColor = picture.get(pCol, pRow);
                        red += pColor.getRed() * weights[i][j];
                        green += pColor.getGreen() * weights[i][j];
                        blue += pColor.getBlue() * weights[i][j];

                    }
                }

                int tRed = (int) Math.round(red);
                int tGreen = (int) Math.round(green);
                int tBlue = (int) Math.round(blue);

                if (tRed < 0)
                    tRed = 0;
                if (tRed > 255)
                    tRed = 255;
                if (tGreen < 0)
                    tGreen = 0;
                if (tGreen > 255)
                    tGreen = 255;
                if (tBlue < 0)
                    tBlue = 0;
                if (tBlue > 255)
                    tBlue = 255;

                Color color = new Color(tRed, tGreen, tBlue);
                target.set(col, row, color);
            }
        }

        return target;
    }

    // Test client
    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);

        identity(pic).show();
        gaussian(pic).show();
        sharpen(pic).show();
        laplacian(pic).show();
        emboss(pic).show();
        motionBlur(pic).show();

    }

}
