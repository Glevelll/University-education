import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Глеб\\IdeaProjects\\Automat\\src\\auto.txt"));
        List<String> lines = new ArrayList<>();
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        int[] finiteConditions = new int[lines.get(0).split(" ").length];
        for (int i=0;i<finiteConditions.length;i++) {
            String[] line = lines.get(0).split(" ");
            finiteConditions[i] = Integer.parseInt(line[i]);
        }
        int start = 0;
        String[] alphabet = new String[lines.get(1).replace(" ","").length()];
        for (int i=0;i<alphabet.length;i++) {
            String[] line = lines.get(1).replace("  ", "").split(" ");
            alphabet[i]=line[i];
        }
        int m = alphabet.length+1;
        int n = lines.size()-2;
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String[] line = lines.get(i+2).split(" "); //разделение строки по переходам
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        int[][] matrixT = new int[n][m-1]; //переходы
        int[][] matrixA = new int[n][n]; //достижимость
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m-1; j++) {
                matrixT[i][j]=matrix[i][j+1];
                matrixA[i][matrixT[i][j]]=1; //проход, если есть прохождение, то 1
            }
        }
        Boolean[] v = new Boolean[n]; //булевская матрица посещения
        Arrays.fill(v, false);
        new DFS(start,v,matrixA); //обход вглубину (недопустимые вершины)
        int c=0; //количество достижимых вершин
        for (Boolean aBoolean : v) {
            if (aBoolean) {
                c++;
            }
        }
        int[][] matN = new int[c][m]; //матрица с достиж вершинами
        int indV = 0;
        for (int i=0;i< v.length;i++){
            if (v[i]){ //если находится посещение, то матрица копируется в этом месте
                System.arraycopy(matrix[i], 0, matN[indV], 0, m);
                indV++;
            }
        }
        HashMap<Integer,Integer> newEquivalence = new LinkedHashMap<>();
        for (int i =0;i<c;i++) {
            for (int finiteCondition : finiteConditions) {
                if (matN[i][0] != finiteCondition) {
                    newEquivalence.put(matN[i][0], 0); //если не конеч сост = 0
                } else {
                    newEquivalence.put(matN[i][0], 1); //1
                    break;
                }
            }
        }
        ArrayList<HashMap<Integer, Integer>> equivalence = new ArrayList<HashMap<Integer, Integer>>();
        equivalence.add(newEquivalence);
        equivalence.add(new LinkedHashMap <Integer,Integer>());
        int countHash=1;
        int countClass = 2;
        while (!(equivalence.get(countHash - 1).equals(equivalence.get(countHash)))) {
            //перебираем вершины
            for (int i = 0; i < c - 1; i++) {
                boolean single = false; //сравн переходов
                for (int j = i + 1; j < c; j++) {
                    //если равны классы эквив вершин, то они одинаковые
                    if (Objects.equals(equivalence.get(countHash - 1).get(matN[i][0]), equivalence.get(countHash - 1).get(matN[j][0]))) {
                        single = true;
                        int matches = 0;
                        //проверка оставшихся совпад i
                        for (int index = 0; index < m - 1; index++) {
                            if (Objects.equals(equivalence.get(countHash - 1).get(matN[i][index + 1]), equivalence.get(countHash - 1).get(matN[j][index + 1]))) {
                                matches++;
                            }
                        }
                        if (matches != m - 1) {
                            //если первое сост записано, то второе в эквив
                            if ((equivalence.get(countHash).containsKey(matN[i][0])) && (!(equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[j][0], countClass);
                            }
                            if (!(equivalence.get(countHash).containsKey(matN[i][0])) && (!(equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[i][0], equivalence.get(countHash - 1).get(matN[i][0]));
                                equivalence.get(countHash).put(matN[j][0], countClass);
                            }
                            if (!(equivalence.get(countHash).containsKey(matN[i][0])) && ((equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[i][0], countClass);
                            }
                        } else { //если класс эквив не сошелся
                            if ((equivalence.get(countHash).containsKey(matN[i][0])) && (!(equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[j][0], equivalence.get(countHash - 1).get(matN[i][0]));
                            }
                            if (!(equivalence.get(countHash).containsKey(matN[i][0])) && ((equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[i][0], equivalence.get(countHash - 1).get(matN[j][0]));
                            }
                            if (!(equivalence.get(countHash).containsKey(matN[i][0])) && (!(equivalence.get(countHash).containsKey(matN[j][0])))) {
                                equivalence.get(countHash).put(matN[i][0], equivalence.get(countHash - 1).get(matN[i][0]));
                                equivalence.get(countHash).put(matN[j][0], equivalence.get(countHash - 1).get(matN[j][0]));
                            }
                        }
                    }
                }
                if (!(single) && (!equivalence.get(countHash).containsKey(matN[i][0]))) { //запись в новую матрицу i
                    equivalence.get(countHash).put(matN[i][0], equivalence.get(countHash - 1).get(matN[i][0]));
                }
            }
            //если после преобраз они равны, то заканч процедру
            if (equivalence.get(countHash - 1).equals(equivalence.get(countHash))) {
                break;
            } else {//если нет то добав новый класс
                equivalence.add(new LinkedHashMap<Integer, Integer>());
                countHash++;
                countClass++;
            }
        }
        HashMap<Integer,Integer> lastEquivalence = new LinkedHashMap<>();
        //соблюдение границ
        lastEquivalence = equivalence.get(equivalence.size()-1);
        FileWriter out = new FileWriter("C:\\Users\\Глеб\\IdeaProjects\\Automat\\src\\out.txt");
        out.write("\n  ");
        for (String s : alphabet) {
            out.write(s + " ");
        }
 //первый столбец - это в какие мы сост перейдем в новую матрицу перепис послед дейст
        for(int i =0; i<c;i++){
            for (int j=0;j<m;j++){
                matN[i][j]=lastEquivalence.get(matN[i][j]);
            }
        }
        //кол-во новых сост в первом столбце
        int cNew=0;
        for (int i = 0;i<c-1;i++){
            for (int j = i+1;j<c;j++) {
                if ((matN[i][0]== matN[j][0])){
                    cNew++;
                    break;
                }
            }
        }
        if (cNew==0){
            out.write("\n");
            for (int i = 0; i < c; i++) {
                for (int j= 0;j<m;j++) {
                    out.write(matN[i][j] + " ");
                }
                out.write("\n");
            }
        } else {
            int k=0;
            int[][] newAuto = new int[cNew][m];
            for (int i = 0; i < c; i++) {
                int cAuto = 0;
                for (int j = 0; j < cNew; j++) {
                    boolean same = true;
                    for (int i1 = 0; i1 < m; i1++) {
                        if ((matN[i][i1] != newAuto[j][i1])) { //матр дост верш != знач в новом авт
                            same = false;
                        }
                    }

                    if (!same) {
                        cAuto++;
                    }
                }

                if (cAuto==cNew){
                    System.arraycopy(matN[i], 0, newAuto[k], 0, m);
                    k++;
                }
            }
            out.write("\n");
            for (int i = 0; i < cNew; i++) {
                for (int j = 0; j < m; j++) {
                    out.write(newAuto[i][j] + " ");
                }
                out.write("\n");
            }
        }
        out.close();
    }
    public static class DFS {
        DFS(int start, Boolean[] visited, int[][] matrixTrans) { //вглубину
            visited[start] = true;
            for (int i = 0; i < matrixTrans.length; i++) {
                if ((matrixTrans[start][i] != 0) && (!visited[i])) { //поиск недостающих вершин
                    new DFS(i, visited, matrixTrans);
                }
            }
        }
    }
}