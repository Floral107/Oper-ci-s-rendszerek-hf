import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Integer> input = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  //System.in
        boolean meg_mehet = true;
        while (meg_mehet && sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(",")) {
                String[] str = line.split(",");
                for (int i = 0; i < str.length; i++) {
                    input.add(Integer.parseInt(str[i]));
                }
            } else meg_mehet = false;
        }
        sc.close();
        LapCsere lapCsere = new LapCsere();
        lapCsere.kiir();
    }
}