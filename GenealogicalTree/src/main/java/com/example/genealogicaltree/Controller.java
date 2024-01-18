package com.example.genealogicaltree;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    @FXML
    public TextField textField1;

    @FXML
    public TextField textField2;

    @FXML
    public Button button1;

    @FXML
    public Button button2;

    @FXML
    public Label labelCheck;
    @FXML
    public Hyperlink reg;

    @FXML
    public void handleButtonAction () throws IOException {
        User user = new User();
        user.setLogin(textField1.getText());
        user.setPassword(textField2.getText());
        boolean forFan = user.comparison(user, user.fillTheUser());
        boolean adminCheck = user.comparison(user,user.fillTheAdmin());

        if (adminCheck) {
            Stage stage = new Stage();
            stage.setTitle("Администратор");
            Parent root = FXMLLoader.load(getClass().getResource("AdminFamily.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            button1.getScene().getWindow().hide();

        } else {
            if(forFan){
                Stage stage = new Stage();
                stage.setTitle("Генеалогическое древо");
                Parent root = FXMLLoader.load(getClass().getResource("Family.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                button1.getScene().getWindow().hide();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Сообщение об ошибке");
                alert.setContentText("Проверьте введенные логин и пароль");
                alert.showAndWait();
            }
        }
    }
    @FXML
    public void emptyLine(){
        button2.setOnAction(event ->{
            User user = new User();
            user.setPassword(String.valueOf(textField2));
            user.setLogin(String.valueOf(textField1));
            textField1.setText("");
            textField2.setText("");
        } );
    }
    @FXML
    public void goToRegistration(){
        reg.setOnAction(event ->{
            reg.getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            Parent root2 = null;
            try {
                root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root2);
            stage.setScene(scene);
            stage.show();
        });
    }
}
