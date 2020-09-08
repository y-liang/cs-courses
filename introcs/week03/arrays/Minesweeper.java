public class Minesweeper {
//    public static String printField(String arr[][]) {
//        String grid = "";
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                grid += arr[i][j] + "  ";
//            }
//            grid += "\n";
//        }
//        return grid;
//    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        String asterisk = "*";

        String[][] field = new String[m][n];

        // put mines in the field
        for (int i = 0; i < k; i++) {
            int spot = (int) (Math.random() * (m * n));
            int row = (int) (Math.floor(spot / n));
            int col = spot % n;

            while (field[row][col] != null) {
                spot = (int) (Math.random() * (m * n));
                row = (int) (Math.floor(spot / n));
                col = spot % n;

            }

            field[row][col] = asterisk;
        }


//        System.out.print(printField(field) + "\n\n");
//        System.out.println(Arrays.toString(field));


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == null) {
                    int count = 0;
                    if (i > 0) {
                        if (field[i - 1][j] != null && field[i - 1][j].equals(asterisk)) count++;
                    } // top
                    if (i > 0 && j < n - 1) {
                        if (field[i - 1][j + 1] != null && field[i - 1][j + 1].equals(asterisk)) count++;
                    } // top right
                    if (j < n - 1) {
                        if (field[i][j + 1] != null && field[i][j + 1].equals(asterisk)) count++;
                    } // right
                    if (j < n - 1 && i < m - 1) {
                        if (field[i + 1][j + 1] != null && field[i + 1][j + 1].equals(asterisk)) count++;
                    } // right bottom
                    if (i < m - 1) {
                        if (field[i + 1][j] != null && field[i + 1][j].equals(asterisk)) count++;
                    } // bottom
                    if (i < m - 1 && j > 0) {
                        if (field[i + 1][j - 1] != null && field[i + 1][j - 1].equals(asterisk)) count++;
                    } // bottom left
                    if (j > 0) {
                        if (field[i][j - 1] != null && field[i][j - 1].equals(asterisk)) count++;
                    } // left
                    if (j > 0 && i > 0) {
                        if (field[i - 1][j - 1] != null && field[i - 1][j - 1].equals(asterisk)) count++;
                    } // left up

                    field[i][j] = Integer.toString(count);
                }
                System.out.print(field[i][j] + "  ");
            }
            System.out.print("\n");
        }

    }
}
