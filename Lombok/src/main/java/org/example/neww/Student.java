package org.example.neww;

import java.util.Scanner;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

@Builder
public class Student {
    @Getter @Setter public String name;
    @Getter @Setter public String surname;
    @Getter @Setter public LocalDate date;

    public int calculateAge() {
        LocalDate cDate = LocalDate.now();
        return Period.between(date,cDate).getYears();
    }

}
