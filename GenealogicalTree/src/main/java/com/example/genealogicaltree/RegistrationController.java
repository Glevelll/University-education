package com.example.genealogicaltree;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
    @FXML
    public TextField log;

    @FXML
    public TextField pass;

    @FXML
    public Button save;

    @FXML
    public Button back;

    @FXML
    public void saveInfo() {
        save.setOnAction(event -> {
            User user = new User();
            user.setLogin(log.getText());
            user.setPassword(pass.getText());
            try {
                if (!user.comparison(user,user.fillTheUser())) {
                    user.write(user);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText("Данные сохранены");
                    alert.setContentText("Логин и пароль успешно сохранены");
                    alert.showAndWait();
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    Parent root2 = null;
                    try {
                        root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root2);
                    stage.setScene(scene);
                    stage.show();
                    Stage stage1 = (Stage) back.getScene().getWindow();
                    stage1.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Ошибка авторизации");
                    alert.setContentText("Некорректные данные");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    public void backToMain(){
        Stage stage = new Stage();
        stage.setTitle("Генеалогическое древо");
        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) back.getScene().getWindow();
        stage1.close();
    }
}

