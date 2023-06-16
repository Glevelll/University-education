import java.io.*;
public class tt1 {
    public static void main(String[] args) {
        int s = 1;
        int num = 0;
        char[] arr = new char[128];
        int c;
        int count1 = 0;
        int count123 = 0;
        int countB = 0;
        int t = 0;
        boolean ch = false;
        int[] states = new int[4];
        states[0] ++;
        int[] symbol = new int[5];

        try(FileReader reader = new FileReader("C:\\Users\\Глеб\\IdeaProjects\\TAiY\\src\\text.txt"))
        {
            while((c=reader.read())!=-1){
                switch (s){
                    case (1):
                        switch (c) {
                            case 'a' -> {
                                arr[num] = 'u';
                                symbol[0]++;
                                s = 2;
                                countB++;
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
                                if(!ch){
                                    ch = true;
                                    t = num++;
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
                                s = 4;
                                states[3]++;
                            }
                            case 'b' -> {
                                arr[num] = 'o';
                                symbol[4]++;
                                s = 3;
                                //states[4]++;
                            }
                        }
                        break;
                }
                num++;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        StringBuilder str = new StringBuilder();
        System.out.print("Выходное слово: ");
        for (int i = 0; i < num; i++) {
            System.out.print(arr[i]);
            str.append(arr[i]);
        }
        System.out.println();

        System.out.println("a) ");
        for (int i = 0; i < num; i++) {
            if(arr[i] == 'u'){
                count1++;
            }
        }
        System.out.println("Количество u: " + count1);

        System.out.println("б) ");
        for (int i = 0; i < num-2; i++) {
            if(arr[i] == 'u'){
                if((arr[i] == 'u') && (arr[i+1] == 'v') && (arr[i+2] == 'v')){
                    count123++;
                }
            }
        }
        if (count123 == 0){
            System.out.println("Нет подслов uvv ");
        }
        else{
            System.out.println("Количество подслов uvv: " + count123);
        }

        System.out.println("в) ");
        System.out.println("Кол-во раз, когда автомат оказался в состоянии S1 и выдал при этом символ u: " + countB);

        System.out.println("г) ");
        if(str.length()>29){
            if((arr[9] == 'u') && (arr[9] == arr[19]) && (arr[19] == arr[29])){
                System.out.println("Автомат выдал u");
            }
            else {
                System.out.println("Автомат не выдал u");
            }
        }
        else {
            System.out.println("Мало тактов");
        }

        System.out.println("д) ");
        if (count1 != 0) {
            int count1D = 0;
            int max = 1;
            for (int i = 0; i < num - 1; i++) {
                if (arr[i] == arr[i + 1]) {
                    count1D++;
                    if (count1D >= max) {
                        max = count1D;
                    }
                } else {
                    count1D = 1;
                }
            }
            System.out.println("Максимальная длина повторения u подряд = " + max);
        }
        else {
            System.out.println("u отсутствует");
        }

        System.out.println("е) ");
        System.out.println("Тактов на котором автомат оказался в S2, и выдал v = " + t);

        System.out.println("ж) ");
        boolean States = true;
        boolean Symbols = true;

        for (int i = 0; i < 4; i++){
            if(states[i] == 0){
                States = false;
                break;
            }
        }

        if (States) {
            for (int i = 0; i < 4; i++) {
                if (states[i] == 0) {
                    Symbols = false;
                }
            }
            System.out.println("Да");
        }
        else {
            System.out.println("Нет");
        }

        System.out.println("з) ");
        System.out.println("u = " + symbol[0]);
        System.out.println("v = " + symbol[1]);
        System.out.println("p = " + symbol[2]);
        System.out.println("r = " + symbol[3]);
        System.out.println("o = " + symbol[4]);
    }
}