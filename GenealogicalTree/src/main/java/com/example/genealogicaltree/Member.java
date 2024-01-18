package com.example.genealogicaltree;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Member {
    String Surname;
    String Name;
    String  Id;
    String Date;
    int Spouse;
    String Parents;
    String Children;


//    public Member(String Surname, String Name, String Patronymic, String Date, String Spouse, String Parents, String Children){
//        this.Surname = Surname;
//        this.Name = Name;
//        this.Patronymic = Patronymic;
//        this.Date = Date;
//        this.Parents = Parents;
//        this.Spouse = Spouse;
//        this.Children = Children;
//    }

    public Member() {
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String  getId() {
        return Id;
    }

    public void setId(String  Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getSpouse() {
        return Spouse;
    }

    public void setSpouse(int spouse) {
        Spouse = spouse;
    }

    public String getChildren() {
        return Children;
    }

    public void setChildren(String children) {
        Children = children;
    }

    public String getParents() {
        return Parents;
    }

    public void setParents(String parents) {
        Parents = parents;
    }

    public void inputMember(Scanner scanner) {
        Surname = scanner.next();
        Name = scanner.next();
        Id = scanner.next();
        Date = scanner.next();
        Spouse = Integer.parseInt(scanner.next());
        Parents = scanner.next();
        Children = scanner.next();

    }
    public static void updateFile(ObservableList<Member> tableNow) throws IOException {
        FileWriter writer = new FileWriter("C://Users//Глеб//IdeaProjects//GenealogicalTree//src//main//java//com//example//genealogicaltree//Family");
        writer.write("");
        Iterator var2 = tableNow.iterator();

        while(var2.hasNext()) {
            Member member = (Member)var2.next();
            String Surname = member.getSurname();
            String Name = member.getName();
            String  Id = member.getId();
            String Date = member.getDate();
            int Spouse = member.getSpouse();
            String Parents = member.getParents();
            String Children = member.getChildren();
            writer.write("" + Surname + " " + Name + " " + Id + " " + Date + " " + Spouse +
                    " " + Parents + " " + Children + "\n");
        }
        writer.close();
    }
}
