import java.util.Arrays;

public class BarChartRacer {

    public static void main(String[] args) {
        In in = new In(args[0]); // filename
        int numOfMost = Integer.parseInt(args[1]); // how many bars

        String title = in.readLine();
        String xAxis = in.readLine();
        String source = in.readLine();

        StdDraw.setCanvasSize(1000, 800);
        StdDraw.enableDoubleBuffering();

        BarChart chart = new BarChart(title, xAxis, source);
//        StdAudio.loop("");

        while (in.hasNextLine()) {
            in.readLine(); // read blank line
            int numOfRecords = Integer.parseInt(in.readLine());

            Bar[] bars = new Bar[numOfRecords];
            String year = "";

            int cnt = 0; // only put in array when value is not zero

            // create bars array of all records
            for (int i = 0; i < numOfRecords; i++) {
                String line = in.readLine();
                String[] fields = line.split(",");

                year = fields[0];
                String name = fields[1];
//                String country = fields[2];
                int value = Integer.parseInt(fields[3]);
                String category = fields[4];

                if (value > 0) {
                    bars[cnt] = new Bar(name, value, category);
                    cnt++;
                }
            }


            if (cnt > 1) {
                if (cnt < numOfRecords) {
                    bars = Arrays.copyOf(bars, cnt);
                }

                Arrays.sort(bars);


//                BarChart chart = new BarChart(title, xAxis, source);

                // build chart of top records
                for (int i = numOfMost < cnt ? numOfMost - 1 : cnt - 1; i >= 0; i--) {
                    chart.setCaption(year);
                    chart.add(bars[i].getName(), bars[i].getValue(), bars[i].getCategory());
                }

                chart.draw();
                StdDraw.show();
                StdDraw.pause(1);

                StdDraw.clear();
                chart.reset();
            } // else, all values in a group is zero, do nothing


        }

    }
}
