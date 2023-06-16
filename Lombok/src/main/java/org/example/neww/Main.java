package org.example.neww;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Student.builder()
                .name("Gleb")
                .surname("Nikishin")
                .date(LocalDate.ofEpochDay(2003-10-01))
                .build();
        System.out.println(Student.builder());
    }
}

