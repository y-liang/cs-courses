public class ThueMorse {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int pow = (int) Math.ceil(Math.log(n) / Math.log(2));

        int len = (int) Math.pow(2, pow);
        int[] arr = new int[len];

        // generate sequence
        for (int k = 0; k < len; k++) {
            if (k % 2 == 1) {
                arr[k] = 1 - arr[k / 2];
            } else {
                arr[k] = arr[k / 2];
            }
        }

/*
        // generate sequence
        String tm = "0";
        String mt = "1";
        String t = "";
        String m = "";

        for (int k = 0; k < pow; k++) {
            t = tm + mt;
            m = mt + tm;

            tm = t;
            mt = m;
        }
*/

        // make grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    System.out.print("+  ");
                } else {
                    System.out.print("-  ");
                }
            }
            System.out.print("\n");
        }

    }
}
