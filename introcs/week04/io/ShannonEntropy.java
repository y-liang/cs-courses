public class ShannonEntropy {
    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int[] x = new int[m]; // a sequence of integers between 1 and m, value is frequencies
        int n = 0; // the length of the sequence
        double sum = 0.0;

        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            x[i - 1] += 1; // i - 1 to match with the x array indices

            n++;
        }

        for (int i = 0; i < m; i++) {
            if (x[i] != 0) {
                double p = (double) x[i] / n;
                sum += -(p * (Math.log(p) / Math.log(2)));
            }
        }

//        System.out.format("%.4f%n", sum);
        StdOut.printf("%.4f%n", sum);
    }
}

// log2 N = log10 N / log10 2 that is (int) (Math.log(x) / Math.log(base))

