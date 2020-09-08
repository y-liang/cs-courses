public class Birthday {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // bdays in total
        int trials = Integer.parseInt(args[1]);


        // n + 1 people in total, guarantee duplicate bdays
        int[] counts = new int[n + 1]; // all 0 at the initialization, logs the number of people entered the room before duplate happens, indices 0 and 1 dont matter much
        int sum = 0;

        for (int j = 0; j < trials; j++) {
            boolean[] bdays = new boolean[n];

            // people entering the room one by one
            for (int i = 0; i < n; i++) {
                int bday = (int) (Math.random() * n);

                if (bdays[bday]) { // if true
                    counts[i + 1] += 1; // (i + 1) persons have entered the room
                    break;
                } else {
                    bdays[bday] = true;
                }
            }
        }

        // print table, put 366 not n + 1?
        for (int k = 1; k < n + 1; k++) {
            sum += counts[k];
            double fraction = (double) sum / trials;
            System.out.println(k + "   " + counts[k] + "   " + fraction);

            if (fraction >= 0.5) {
                break;
            }
        }
    }
}
