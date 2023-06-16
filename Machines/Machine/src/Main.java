import java.io.*;
public class Main {
    public static void main(String[] args) {
        int s = 1;
        int num = 0;
        char[] arr = new char[128];
        int c;
        boolean ch = false;
        int[] states = new int[4];
        states[0]++;
        int[] symbol = new int[5];

        try (FileReader reader = new FileReader("C:\\Users\\Глеб\\IdeaProjects\\Machine\\src\\text.txt")) {
            while ((c = reader.read()) != -1) {
                switch (s) {
                    case (1):
                        switch (c) {
                            case 'a' -> {
                                arr[num] = 'u';
                                symbol[0]++;
                                s = 2;
                                states[2]++;
                            }
                            case ('b') -> {
                                arr[num] = 'p';
                                symbol[2]++;
                                s = 2;
                                states[2]++;
                            }
                        }
                        break;

                    case (2):
                        switch (c) {
                            case 'a' -> {
                                arr[num] = 'u';
                                symbol[0]++;
                                s = 3;
                                states[0]++;
                                if (!ch) {
                                    ch = true;
                                }
                            }
                            case 'b' -> {
                                arr[num] = 'o';
                                symbol[4]++;
                                s = 3;
                                states[3]++;
                            }
                        }
                        break;

                    case (3):
                        switch (c) {
                            case 'a' -> {
                                arr[num] = 'v';
                                symbol[1]++;
                                s = 4;
                                states[3]++;
                            }
                            case 'b' -> {
                                arr[num] = 'r';
                                symbol[3]++;
                                s = 1;
                                states[1]++;
                            }
                        }
                        break;

                    case (4):
                        switch (c) {
                            case 'a' -> {
                                arr[num] = 'p';
                                symbol[2]++;
                                states[3]++;
                            }
                            case 'b' -> {
                                arr[num] = 'o';
                                symbol[4]++;
                                s = 3;
                            }
                        }
                        break;
                }
                num++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}