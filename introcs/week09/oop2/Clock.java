public class Clock {
    private int h, m;

    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59) throw new IllegalArgumentException("Out of range.");

        this.h = h;
        this.m = m;
    }

    // Creates a clock whose initial time is specified as a string, using the format HH:MM.
    public Clock(String s) {
        int h, m;
        String hour = s.substring(0, 2); // could be not a number
        String minute = s.substring(3); // could be not a number

        if (s.charAt(2) != ':') throw new IllegalArgumentException("Time format exception occured.");

        try {
            h = Integer.parseInt(hour);
            m = Integer.parseInt(minute);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Time format exception occured.");
        }

        if (h < 0 || h > 23 || m < 0 || m > 59) throw new IllegalArgumentException("Out of range.");

        this.h = h;
        this.m = m;

    }

    // Returns a string representation of this clock, using the format HH:MM.
    public String toString() {
        return (h + ":" + m);
    }

    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock that) {
        if ((h * 60 + m) < (that.h * 60 + that.m)) return true;
        else return false;
    }

    // Adds 1 minute to the time on this clock.
    public void tic() {
        int time = h * 60 + m + 1;
        h = (time / 60) % 24;
        m = time % 60;
    }

    // Adds Δ minutes to the time on this clock.
    public void toc(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Argument is negative.");

        int time = h * 60 + m + delta;
        h = (time / 60) % 24;
        m = time % 60;
    }

    // Test client (see below).
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        String s = args[2];
        int delta = Integer.parseInt(args[3]);

        Clock clock1 = new Clock(h, m);
        Clock clock2 = new Clock(s);

        if (clock1.isEarlierThan(clock2)) StdOut.println(clock1.toString() + " is earlier than " + clock2.toString());
        else StdOut.println(clock1.toString() + " is later than " + clock2.toString());

        clock1.tic();
        StdOut.println(clock1.toString());

        clock2.toc(delta);
        StdOut.println(clock2.toString());


    }
}
