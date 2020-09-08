// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;
// import java.awt.Color;


public class KernelFilter {

    // Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture) {
        double weights[][] = new double[3][3];

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

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture) {
        double weights[][] = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = 4 / 16;
                } else if (i == j || i + j == 2) { // ???
                    weights[i][j] = 1 / 16;
                } else {
                    weights[i][j] = 2 / 16;
                }

                System.out.print(weights[i][j] + " ");
            }
            System.out.print("\n");
        }


        return kernel(picture, weights);
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        double weights[][] = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    weights[i][j] = 5;
                } else if (i == j || i + j == 2) { // ???
                    weights[i][j] = 0;
                } else {
                    weights[i][j] = -1;
                }

                System.out.print(weights[i][j] + " ");
            }

            System.out.print("\n");
        }


        return kernel(picture, weights);
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        double weights[][] = new double[3][3];

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
        double weights[][] = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 0) {
                    weights[i][j] = -2;
                } else if (i == 0) {
                    weights[i][j] = weights[i][j - 1] + 1;
                } else {
                    weights[i][j] = weights[i - 1][j] + 1;
                }

                System.out.print(weights[i][j] + " ");
            }
            System.out.print("\n");
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        double weights[][] = new double[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    weights[i][j] = 1 / 9;
                } else {
                    weights[i][j] = 0;
                }
                System.out.print(j + " ");
            }
            System.out.print("\n");
            System.out.print(i + " ");
        }

        return kernel(picture, weights);
    }

    // Returns a new picture that applies an arbitrary kernel filter to the given picture.
    private static Picture kernel(Picture picture, double[][] weights) {

        // target picture
        Picture target = new Picture(picture.width(), picture.height());

        int cRow = (weights.length - 1) / 2;
        int cCol = (weights[0].length - 1) / 2;


        // modulo, a % b = (a % b + b) % b to prevent negative remainder

        int count = 0;

        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                int red = 0, green = 0, blue = 0;

//                count++;

                for (int i = 0, pRow = ((row - cRow) % weights.length + weights.length) % weights.length;
                     i < weights.length;
                     i++, pRow++) { // row
                    for (int j = 0, pCol = ((col - cCol) % weights[0].length + weights.length) % weights.length;
                         j < weights[0].length;
                         j++, pCol++) { // column

                        Color pColor = picture.get(pCol, pRow);
                        red += pColor.getRed() * weights[i][j];
                        green += pColor.getGreen() * weights[i][j];
                        blue += pColor.getBlue() * weights[i][j];

//                        System.out.println("red " + red);
//                        System.out.println("green " + green);
//                        System.out.println("blue " + blue);

                    }
                }

                if (red < 0) red = 0;
                if (red > 255) red = 255;
                if (green < 0) green = 0;
                if (green > 255) green = 255;
                if (blue < 0) blue = 0;
                if (blue > 255) blue = 255;

//                System.out.println("red " + red);
//                System.out.println("green " + green);
//                System.out.println("blue " + blue);

//                if (count > 5) return picture;

                Color color = new Color(red, green, blue);
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
