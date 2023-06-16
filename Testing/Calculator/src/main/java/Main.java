import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите два числа");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        char opera;

        System.out.println("Введите операцию между этими числами (+,-,*,/)");
        opera = sc.next().charAt(0);

        switch (opera) {
            case '+' -> System.out.println(Calculator.sum(a, b));
            case '-' -> System.out.println(Calculator.diff(a, b));
            case '*' -> System.out.println(Calculator.mult(a, b));
            case '/' -> System.out.println(Calculator.div(a, b));
        }
    }
}