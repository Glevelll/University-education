import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        RandRegEx randRegEx = new RandRegEx(7);
        System.out.println("Ваш файл");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        for (int i = 0; i < 7; i++) {
            randRegEx.write(path,randRegEx.output());
        }
    }
}