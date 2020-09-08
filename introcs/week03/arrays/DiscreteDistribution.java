public class DiscreteDistribution {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = args.length;

        int[] arr = new int[n];
        int[] sum = new int[n];
        arr[0] = 0; // or null
        sum[0] = 0;

        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(args[i]); // starts at 1, ends at n - 1
            sum[i] = sum[i - 1] + arr[i]; // starts at 1, ends at n - 1

//            System.out.print(arr[i] + " ");
//            System.out.print(sum[i] + " ");
        }

        for (int j = 0; j < m; j++) {
            int r = (int) (Math.random() * sum[n - 1]);
//            System.out.print(r + " ");

            for (int k = 1; k < n; k++) {
                if (sum[k - 1] <= r && r < sum[k]) {
                    System.out.print(k + " ");
                }
            }
        }
    }
}
