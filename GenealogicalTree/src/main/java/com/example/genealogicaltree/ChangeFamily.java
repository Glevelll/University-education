package com.example.genealogicaltree;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChangeFamily implements Initializable {
    public static int index;
    public static ObservableList<Member> list;
    @FXML
    private TextField Surname;
    @FXML
    private TextField Name;
    @FXML
    private TextField Id;
    @FXML
    private TextField Date;
    @FXML
    private TextField Parents;
    @FXML
    private TextField Spouse;
    @FXML
    private TextField Children;
    @FXML
    private Button applyEditButton;
    @FXML
    private Button Cancel;
    @FXML
    private Button applyAddButton;
    @FXML
    private Button cancelAdd;
    private Stage dialogStage;
    private Member member;
    private boolean okClicked = false;

    public ChangeFamily() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Member member) {
        this.member = member;
        this.Surname.setText(member.getSurname());
        this.Name.setText(member.getName());
        this.Id.setText(String.valueOf(member.getId()));
        this.Date.setText(member.getDate());
        this.Spouse.setText(member.getDate());
        this.Parents.setText(String.valueOf(member.getParents()));
        this.Children.setText(String.valueOf(member.getChildren()));
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (this.Surname.getText() == null || this.Surname.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступной фамилии\n";
        }
        if (this.Name.getText() == null || this.Name.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступного имени\n";
        }
        if (this.Id.getText() == null || this.Id.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступного Id\n";
        }

        if (this.Date.getText() == null || this.Date.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступной даты рождения\n";
        }

        if (this.Spouse.getText() == null || this.Spouse.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступного супруга\n";
        }

        if (this.Parents.getText() == null || this.Parents.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступного родителя\n";
        }

        if (this.Children.getText() == null || this.Children.getText().length() == 0) {
            errorMessage = errorMessage + "Нет доступного ребенка";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(this.dialogStage);
            alert.setTitle("Неккоректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleOk() throws IOException {
        if (this.isInputValid()) {
            Member member = new Member();
            member.setSurname(this.Surname.getText());
            member.setName(this.Name.getText());
            member.setId(this.Id.getText());
            member.setDate(this.Date.getText());
            member.setSpouse(Integer.parseInt(this.Spouse.getText()));
            member.setParents(this.Parents.getText());
            member.setChildren(this.Children.getText());
            this.change(list, index, member);
            this.okClicked = true;
            this.applyEditButton.getScene().getWindow().hide();
        }

        this.Cancel.getScene().getWindow().hide();
    }

    public void change(ObservableList<Member> list, int index, Member member) throws IOException {
        list.set(index, member);
        Member.updateFile(list);
    }

    @FXML
    public void add() throws IOException {
        if (this.isInputValid()) {
            Member member = new Member();
            member.setSurname(this.Surname.getText());
            member.setName(this.Name.getText());
            member.setId(this.Id.getText());
            member.setDate(this.Date.getText());
            member.setSpouse(Integer.parseInt(this.Spouse.getText()));
            member.setParents(this.Parents.getText());
            member.setChildren(this.Children.getText());
            list.add(member);
            Member.updateFile(list);
            this.okClicked = true;
            this.applyAddButton.getScene().getWindow().hide();
        }

        this.cancelAdd.getScene().getWindow().hide();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

