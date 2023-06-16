package org.example.neww;

import lombok.*;
import org.example.neww.Person;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Arrays;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
class Schoolkid extends org.example.neww.Person {
    int count = 10;
    @Getter @Setter protected int[] estimates = new int[count];

    public void input() {
        Scanner sc = new Scanner(System.in);
        super.Read();
        System.out.print("Кол-во оценок: ");
        this.count = sc.nextInt();
        System.out.print("Введите оценки ");
        this.estimates = new int[count];
        for (int i = 0; i < estimates.length; i++) {
            estimates[i] = sc.nextInt();
        }
    }
    public double average(){//сред балл
        double count = 0.0;
        for (int estimate : estimates) {
            count += estimate;
        }
        return count /= estimates.length;
    }

    public boolean otl(){
        boolean count = true;
        for (int i = 0; i < estimates.length; i++) {
            if(average() != 5) {
                count = false;
            }
        }
        return count;
    }
    public boolean two(){
        boolean count = false;
        for (int estimate : estimates) {
            if (estimate == 2) {
                count = true;
                break;
            }
        }
        return count;
    }
    public void output(){
        super.Print();
        System.out.println("Оценки: " + Arrays.toString(estimates));
        System.out.println("Сред балл: " + average());
        System.out.println("Отличник: " + otl());
        System.out.println("Имеет двойку: " + two());
    }
}




