public class ActivationFunction {

    // Returns the Heaviside function of x.
    public static double heaviside(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (Math.abs(x) == 0) {
            return (double) 1 / 2;
        } else if (x < 0) {
            return 0;
        } else {
            return 1;
        }

    }

    // Returns the sigmoid function of x.
    public static double sigmoid(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        return (double) 1 / (1 + Math.exp(-x));
    }

    // Returns the hyperbolic tangent of x.
    public static double tanh(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
    }

    // Returns the softsign function of x.
    public static double softsign(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        return x / (1 + Math.abs(x));
    }

    // Returns the square nonlinearity function of x.
    public static double sqnl(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (x <= -2) {
            return -1;
        } else if (x > -2 && x < 0) {
            return x + Math.pow(x, 2) / 4;
        } else if (x >= 0 && x < 2) {
            return x - Math.pow(x, 2) / 4;
        } else {
            return 1;
        }
    }

    // Takes a double command-line argument x and prints each activation
    // function, evaluated, in the format (and order) given below.
    public static void main(String[] args) {

        double x = Double.parseDouble(args[0]);

        StdOut.printf("heaviside(%f) = %.6f%n", x, heaviside(x));
        StdOut.printf("sigmoid(%f) = %.6f%n", x, sigmoid(x));
        StdOut.printf("tanh(%f) = %.6f%n", x, tanh(x));
        StdOut.printf("softsign(%f) = %.6f%n", x, softsign(x));
        StdOut.printf("sqnl(%f) = %.6f%n", x, sqnl(x));

    }
}
