package org.example.old;

import java.util.Scanner;
import lombok.*;
@ToString(of = {"name", "a", "h"})
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Prizma {
@Getter @Setter private String name;
@Getter @Setter private double a;
@Getter @Setter private int h;

    //здесь был конструктор без арг

    //здесь был конструтор с арг


    public void inpFigure(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Фигура: ");
        setName(inp.nextLine());
        System.out.print("Длина ребра: ");
        setA(inp.nextDouble());
        System.out.print("Высота: ");
        setH(inp.nextInt());
    }

    //здесь был ToString


    public double soCalc(){
        return (5*Math.pow(a,2))/(4*Math.sqrt(5-2*Math.sqrt(5)));
    }

    public double spCalc(){
        return (a*5)*h + 2*soCalc();
    }

    public double vCalc(){
        return soCalc()*h;
    }

}


