public class Ramanujan4 {

    // Is n a Ramanujan number?
    public static boolean isRamanujan(long n) {
        int m = (int) Math.pow(n, 1.0 / 4.0);
        int count = 0;

        System.out.println("m " + m);

//        for (int i = 1; i <= m; i++) {
//            for (int j = m; j >= i + 1; j--) {
//                if (Math.pow(i, 3) + Math.pow(j, 3) == n) {
//                    count += 1;
////                    System.out.println("i " + i + " - j " + j);
//                    break;
//                }
//            }
//
//            // count of pairs
//            if (count == 2) {
//                return true;
//            }
//        }


        // alternatively, check if the different is a cube, bc Math.pow(i, 3) + Math.pow(j, 3) == n
        for (int i = 1; i <= m; i++) {
            int difference = (int) (n - Math.pow(i, 4));
            int j = (int) Math.pow(n, 1.0 / 4.0);

//            System.out.println("j " + j);
//            System.out.println("cbrt " + Math.cbrt(difference));

            // check if j is exactly a cube root
            if (j == Math.pow(n, 1.0 / 4.0)) {
                count += 1;

                System.out.println("j " + j);
//                System.out.println("cbrt " + Math.cbrt(difference));
            }

            if (count == 2) return true;
        }


        return false;
    }

    // Takes a long integer command-line arguments n and prints true if
    // n is a Ramanujan number, and false otherwise.
    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);

        StdOut.print(isRamanujan(n));

    }
}
