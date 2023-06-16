import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandRegEx {
    final char[] alphabet = new char[]{'a','b','c','(',')','u','*'};
    String str = "";
    int length;

    public RandRegEx(int length){
        this.length = length;
    }

    public String output(){
        boolean expression = false;
        boolean uFeasibility = false;
        boolean klineFeasibility = false;
        boolean hooksFeasibility = false;
        int start = 1;

        while (!expression || !uFeasibility || !klineFeasibility || !hooksFeasibility){
            int countO = 0;
            int countC = 0;
            start = 1;
            str = "";
            while (start <= length){
                Random random = new Random();
                int i = random.nextInt(alphabet.length);
                if(alphabet[i] == '('){
                    countO++;
                }
                if(alphabet[i] == ')'){
                    countC++;
                }
                str += alphabet[i];
                start++;
            }

            expression = expressionBool(str);
            hooksFeasibility = hooksBool(str);
            klineFeasibility = klineBool(str);
            uFeasibility = uBool(str);
        }
        return str;
    }

    public void write(String path, String regular) throws IOException{
        path = "/Users/Глеб/IdeaProjects/Regular/src/"+path;
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(regular+"\n");
        fileWriter.close();
    }

    public boolean expressionBool(String str){
        boolean expression = false;
        char[] arr = str.toCharArray();
        int open = 0;
        int close = 0;
        int kline = 0;
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            if(arr[i] == '('){
                open++;
            }
            if(arr[i] == ')'){
                close++;
            }
            if(arr[i] == 'u'){
                u++;
            }
            if(arr[i] == '*'){
                kline++;
            }
        }
        if(open + close != 0 && kline != 0 && u != 0){
            expression = true;
        }
        return expression;
    }

    public boolean hooksBool(String str){
        int c1 = 0;
        int c2 = 0;
        boolean expression = false;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if(temp == '('){
                c1++;
            }
            if(temp == ')'){
                c2++;
            }
            if(c2 > c1){
                break;
            }
        }
        for (int i = 2; i < arr.length; i++) {
            if(arr[i-2] == '(' && (arr[i-1] != '*' && arr[i-1] != 'u') && arr[i] == ')'){
                return false;
            }
        }
        for (int i = 0; i < str.length()-1; i++) {
            if(arr[i] == '(' && arr[i+1] == ')'){
                return false;
            }
            if(c2 == c1) {
                expression = true;
            }
        }
        for (int i = 0; i < arr.length-2; i++) {
            if(arr[i] == '(' && arr[i+2] == ')'){
                return false;
            }
        }
        return expression;
    }


    public boolean klineBool(String str){
        boolean expression = true;
        char[] arr = str.toCharArray();
        if(arr[0] == '*'){
            return false;
        }
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] == '*' && arr[i+1] == '*'){
                expression = false;
                break;
            }
        }
        for (int i = 1; i < arr.length-1; i++) {
            if(arr[i-1] == 'u' && arr[i] == '*'){
                return false;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] == '*' && arr[i-1] == '('){
                return false;
            }
        }
        for (int i = 2; i < arr.length-2; i++) {
            if(arr[i-2] == '(' && arr[i] =='*' && arr[i+1] == ')' && arr[i+2] == '*'){//(.*)*
                return false;
            }
        }
        return expression;
    }

    public boolean uBool(String str){
        boolean expression = true;
        char[] arr = str.toCharArray();
        if(arr[0] =='u'){
            return false;
        }
        if(arr[str.length()-1] == 'u') {
            return false;
        }
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] == 'u' && arr[i+1] == 'u'){
                return false;
            }
        }
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] == 'u' && arr[i+1] == '*'){
                return false;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 'u' && arr[i - 1] == '(') {
                return false;
            }
        }
            for (int i = 1; i < arr.length-1; i++) {
                if (arr[i] == 'u' && arr[i + 1] == ')') {
                    return false;
                }
            }
        return expression;
    }

}
