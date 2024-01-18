package com.example.genealogicaltree;

import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    public String login;
    public String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }


    public ArrayList<User> fillTheUser() throws FileNotFoundException {
        ArrayList<User> client = new ArrayList<>();
        Scanner fileProp = new Scanner(new File("src/main/java/com/example/genealogicaltree/Data"));
        while (fileProp.hasNext()) {
            User user = new User();
            user.inputUserParams(fileProp);
            client.add(user);
        }
        return client;
    }

    public ArrayList<User> fillTheAdmin() throws FileNotFoundException {
        ArrayList<User> admins = new ArrayList<>();
        Scanner fileProp = new Scanner(new File("src/main/java/com/example/genealogicaltree/admins")); // в файле храню лог пароль
        while (fileProp.hasNext()) {
            User user = new User();
            user.inputUserParams(fileProp);
            admins.add(user);
        }
        return admins;
    }
    public void inputUserParams(Scanner fileProp) {
        setLogin(fileProp.next());
        setPassword(fileProp.next());
    }

    public void write(User user) throws IOException {
        String filePath = ("src/main/java/com/example/genealogicaltree/Data");
        String text ="\n"+ user.getLogin()+" "+user.getPassword();

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean comparison(User user, ArrayList<User> students) { // потом дописать. тут будет сравнение
        boolean flag = false;
        for (User e:students) {
            if(e.getLogin().equals(user.getLogin()) && e.getPassword().equals(user.getPassword())){
                flag = true;break;
            }
        }
        return flag;
    }

}

