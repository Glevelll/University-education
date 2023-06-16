import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int st = 1;
        int i = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        boolean flag = false;
        s = reader.readLine();
        char[] chars = s.toCharArray();
        while (!flag) {
            switch (st) {
                case (1) -> {
                    switch (chars[i]) {
                        case ('L') -> {
                            chars[i] = 'a';
                            st = 2;
                            i--;
                        }
                        case ('a'), ('b') -> {
                            if (chars[i + 2] == 'b' && chars[i + 1] == 'y') {
                            }
                            i++;
                        }
                        case ('y') -> {
                            if (chars[i + 2] == 'b' && chars[i + 1] == 'y') {
                            }
                            chars[i] = 'б';
                            i++;
                        }
                        case ('б') -> {
                            if (chars[i + 2] == 'b' && chars[i + 1] == 'y') {
                            }
                            chars[i] = 'y';
                            i++;
                        }
                    }
                }
                case (2) -> {
                    switch (chars[i]) {
                        case ('L') -> {
                            chars[i] = 'б';
                            flag = true;
                            System.out.println("Завершено!");
                        }
                        case ('a'), ('b') -> {
                            i--;
                        }
                        case ('y') -> {
                            chars[i] = 'a';
                            st = 3;
                        }
                        case ('б') -> {
                            chars[i] = 'b';
                            st = 3;
                            i--;
                        }
                    }
                }
                case (3) -> {
                    switch (chars[i]) {
                        case ('L') -> {
                            chars[i] = 'a';
                            st = 2;
                            i--;
                        }
                        case ('a') -> {
                            chars[i] = 'b';
                            i--;
                        }
                        case ('b') -> {
                            chars[i] = 'y';
                            st = 1;
                        }
                        case ('y') -> {
                            chars[i] = 'б';
                            if (chars[i + 2] == 'b' && chars[i + 1] == 'б') {
                            }
                            st = 1;
                            i++;
                        }
                        case ('б') -> {
                            chars[i] = 'L';
                            i++;
                            flag = true;
                            System.out.println("Завершено!");
                        }
                    }
                }
            }
        }
    }
}
