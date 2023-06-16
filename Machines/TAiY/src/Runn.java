import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runn {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int n, t = 0, u = 0, k = 0, st = 1;
        s = reader.readLine();
        n = s.length();
        for (int i = 0; i < s.length(); i++) {
            switch (st) {
                case (1):
            if ((s.charAt(0) >= 'A') && (s.charAt(0) <= 'Z')) {
                u = 0;
            }//Проверка на заглавную букву
            else {
                u = 1;
            }
                if (u != 1) {
                    if ((s.charAt(i) >= 'A') && (s.charAt(i) <= 'Z')) { // если встречается заглвная буква
                        t = 0;
                        k++;
                    } else {
                        t++;
                        k = 0;
                    }
                    if ((t > 3) || (k > 1)) {
                        u = 1;
                    } // если слово содержит более 4 букв и более 1 заглавной буквы подряд
                } else {
                    i = n;
                }
            }
        }
        if ((u == 1) || (k == 1)) {System.out.println("No");}
        else {System.out.println("Yes");}
    }
}
