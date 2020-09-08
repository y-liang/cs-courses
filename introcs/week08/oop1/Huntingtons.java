public class Huntingtons {

    // Returns the maximum number of consecutive repeats of CAG in the DNA string.
    public static int maxRepeats(String dna) {
        int count = 0;
        int max = 0;
        int i = 0;

        while (i < dna.length() - 4) {

            while (dna.substring(i, i + 3).equals("CAG")) {

                if (i >= dna.length() - 3) {
                    if (dna.substring(i).equals("CAG")) {
                        count += 1;
                        if (max < count) max = count;
                    }
                    break;
                }

                count += 1;
                i += 3;
                if (max < count) max = count;

            }

            count = 0;
            i += 1;
        }

        return max;
    }

    // Returns a copy of s, with all whitespace (spaces, tabs, and newlines) removed.
    public static String removeWhitespace(String s) {
        s = s.replace(" ", "").replace("\t", "").replace("\n", "");
        return s;
    }

    // Returns one of these diagnoses corresponding to the maximum number of repeats:
    // "not human", "normal", "high risk", or "Huntington's".
    public static String diagnose(int maxRepeats) {
        if (maxRepeats >= 0 && maxRepeats <= 9) return "not human";
        else if (maxRepeats <= 35) return "normal";
        else if (maxRepeats <= 39) return "high risk";
        else if (maxRepeats <= 180) return "Huntington's";
        else return "not human";
    }

    public static void main(String[] args) {
        // Take the name of a file as a command-line argument
        // Read the genetic sequence from the file using the In class
        In in = new In(args[0]);
        String s = in.readAll();

        String dna = removeWhitespace(s);
        int max = maxRepeats(dna);

        StdOut.println("max repeats = " + max);
        StdOut.println(diagnose(max));
    }

}
