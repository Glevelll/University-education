package org.example.neww;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
class Person {
    @Getter @Setter public String surname;
    @Getter @Setter public String name;
    @Getter @Setter public LocalDate date;
    @Getter @Setter public char gender;

    public void Read(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите фамилию");
        setSurname(sc.nextLine());
        System.out.println("Введите имя");
        setName(sc.nextLine());
        System.out.println("Введите дату");
        date = LocalDate.parse(sc.nextLine());
        System.out.println("Введите пол");
        setGender(sc.next().charAt(0));
    }
    public int calculateAge() {
        LocalDate cDate = LocalDate.now();
        return Period.between(date,cDate).getYears();
    }
    public void Print(){
        System.out.println("Фамилия: " + surname);
        System.out.println("Инициалы: " + name.substring(0,1) + ".");
        System.out.println("Дата: " + date);
        System.out.println("Пол: " + gender);
        System.out.println("Возраст: " + calculateAge());
    }

}



