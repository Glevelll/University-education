import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class l3_a1 {
    public static void main(String[] args) throws IOException {
        int st = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        s = reader.readLine();
        for (int i = 0; i < s.length(); i++) {
            switch (st) {
                case (1):
                    if (s.charAt(i) == 'A') {
                        st = 2;
                    } else {
                        st = 6;
                    }
                    break;
                case (2):
                    if (s.charAt(i) == 'B' || s.charAt(i) == 'D' || s.charAt(i) == 'A') {
                        st = 3;
                    } else {
                        st = 6;
                    }
                    break;
                case (3):
                    if (s.charAt(i) == 'A' || s.charAt(i) == 'C') {
                        st = 4;
                    } else {
                        st = 6;
                    }
                    break;
                case (4):
                    if (s.charAt(i) == 'E') {
                        st = 5;
                    } else {
                        st = 6;
                    }
                    break;
                case (5):
                    if (s.charAt(i) == 'E' || s.charAt(i) == 'A' || s.charAt(i) == 'B' || s.charAt(i) == 'C' || s.charAt(i) == 'D') {
                        st = 6;
                    }
                    break;
            }
        }
        if (st == 5) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
