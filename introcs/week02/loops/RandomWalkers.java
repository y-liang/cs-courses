public class RandomWalkers {

    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        int sum = 0;

        for (int i = 0; i < trials; i++) {

            int x = 0;
            int y = 0;

            int steps = 0;

            while (Math.abs(x) + Math.abs(y) < r) {
                int direction = (int) (Math.random() * 4);

                if (direction == 0) {
                    y += 1;
                } else if (direction == 1) {
                    x += 1;
                } else if (direction == 2) {
                    y -= 1;
                } else if (direction == 3) {
                    x -= 1;
                }

                steps++;
            }

            sum += steps;
        }

        double average = (double) sum / trials;
        System.out.println("average number of steps = " + average);


    }
}
