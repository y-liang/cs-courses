public class RandomWalker {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);

        int x = 0;
        int y = 0;

        int steps = 0;

        while (Math.abs(x) + Math.abs(y) < r) {
            System.out.println("(" + x + ", " + y + ")");

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

        System.out.println("steps = " + steps);
    }
}
