public class AudioCollage {

    // Returns a new array that rescales a[] by a multiplicative factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        int n = a.length;
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = a[i] * alpha;
        }
        return b;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        int n = a.length;
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = a[n - 1 - i];
        }
        return b;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        int m = a.length;
        int n = b.length;
        double[] c = new double[m + n];

        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                c[i] = a[i];
            } else {
                c[i] = b[i - m];
            }
        }

        return c;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter arrays with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        int m = a.length;
        int n = b.length;
        double[] c;

        if (m >= n) {
            c = new double[m];
            for (int i = 0; i < n; i++) {
                c[i] = a[i] + b[i];
            }
            for (int i = n; i < m; i++) {
                c[i] = a[i];
            }
        } else {
            c = new double[n];
            for (int i = 0; i < m; i++) {
                c[i] = a[i] + b[i];
            }
            for (int i = m; i < n; i++) {
                c[i] = b[i];
            }
        }

        return c;
    }

    // Returns a new array that changes the speed by the given factor.
    public static double[] changeSpeed(double[] a, double alpha) {
        int n = a.length;
        int m = (int) (n / alpha); // new length;
        double[] c = new double[m];


        for (int i = 0; i < m; i++) {
            c[i] = a[(int) (i * alpha)]; //???
        }

        return c;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
        double[] array1 = StdAudio.read("chimes.wav");
        double[] array2 = StdAudio.read("piano.wav");
        double[] array3 = StdAudio.read("harp.wav");
        double[] array4 = StdAudio.read("dialup.wav");
        double[] array5 = StdAudio.read("cow.wav");

        array1 = amplify(array1, 0.5);
        array2 = reverse(array2);
        array3 = changeSpeed(array3, 2);

        StdAudio.play(mix(array5, merge(array4, mix(array3, merge(array1, array2)))));
    }
}
