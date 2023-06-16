package org.example.old;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import lombok.*;

@ToString(of = {"surname", "name", "date", "gender"})

class Person {

    @Getter @Setter String surname;
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

}


