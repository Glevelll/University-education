package com.example.genealogicaltree;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminController {
    @FXML
    public final ObservableList<Member> familyData = FXCollections.observableArrayList();
    @FXML
    protected TableView<Member> familyTable;
    @FXML
    private TableColumn<Member, String> Surname;
    @FXML
    private TableColumn<Member, String> Name;
    @FXML
    private TableColumn<Member, String> Id;
    @FXML
    private TableColumn<Member, String> Date;
    @FXML
    private TableColumn<Member, String> Children;
    @FXML
    private TableColumn<Member, String> Spouse;
    @FXML
    private TableColumn<Member, String> Parents;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Hyperlink child;
    @FXML
    private Hyperlink spouse;
    @FXML
    private Hyperlink parent;
    @FXML
    private Button deleteData;
    @FXML
    private Button changeAble;
    @FXML
    private Button addButton;
    @FXML
    private TextField textField;
    @FXML
    public Button exit;

    @FXML
    private void onChangeAbleButtonClick() throws IOException {
        Member selectedMember = familyTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
            Stage stage1 = new Stage();
            stage1.setTitle("Изменить");
            Parent root = (Parent) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("EditMember.fxml")));
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
            ChangeFamily.index = this.familyTable.getSelectionModel().getSelectedIndex();
            ChangeFamily.list = this.familyData;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner((Window) null);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран член семьи");
            alert.setContentText("Выберите члена семьи");
            alert.showAndWait();
        }
    }

    @FXML
    private void onAddButtonClick() throws IOException {
        Stage stage1 = new Stage();
        stage1.setTitle("Добавить");
        Parent root = (Parent)FXMLLoader.load((URL)Objects.requireNonNull(this.getClass().getResource("AddMember.fxml")));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();
        ChangeFamily.list = this.familyData;
    }

    public void initialize() {
        try {
            Scanner scanner = (new Scanner(new File("src/main/java/com/example/genealogicaltree/Family"))).useLocale(Locale.US);
            try {
                while(scanner.hasNext()) {
                    Member member = new Member();
                    member.inputMember(scanner);
                    this.familyData.add(member);
                }
            } catch (Throwable var5) {
                if (scanner != null) {
                    try {
                        scanner.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }
                throw var5;
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }
        this.Surname.setCellValueFactory(new PropertyValueFactory("Surname"));
        this.Name.setCellValueFactory(new PropertyValueFactory("Name"));
        this.Id.setCellValueFactory(new PropertyValueFactory("Id"));
        this.Date.setCellValueFactory(new PropertyValueFactory("Date"));
        this.Parents.setCellValueFactory(new PropertyValueFactory("Parents"));
        this.Spouse.setCellValueFactory(new PropertyValueFactory("Spouse"));
        this.Children.setCellValueFactory(new PropertyValueFactory("Children"));
        this.familyTable.setItems(this.familyData);
        this.showFamilyDetails((Member)null);
        this.familyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.showFamilyDetails(newValue);
        });

        FilteredList<Member> filteredData = new FilteredList<>(familyData, p -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(member -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (member.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (member.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Member> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(familyTable.comparatorProperty());
        familyTable.setItems(sortedData);
    }

    public AdminController() {
    }

    public ObservableList<Member> getList() {
        try {
            Scanner scanner = (new Scanner(new File("src/main/java/com/example/genealogicaltree/Family"))).useLocale(Locale.US);
            try {
                while(scanner.hasNext()) {
                    Member member = new Member();
                    member.inputMember(scanner);
                    this.familyData.add(member);
                }
            } catch (Throwable var5) {
                if (scanner != null) {
                    try {
                        scanner.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }
                throw var5;
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }
        return this.familyData;
    }

    private void showFamilyDetails(Member member) {
        if (member != null) {
            this.label1.setText(String.valueOf(member.getSurname()));
            this.label2.setText(String.valueOf(member.getName()));
            this.label3.setText(String.valueOf(member.getId()));
            this.label4.setText(String.valueOf(member.getDate()));
            this.parent.setText(String.valueOf(member.getParents()));
            this.spouse.setText(String.valueOf(member.getSpouse()));
            this.child.setText(String.valueOf(member.getChildren()));
        } else {
            this.label1.setText("");
            this.label2.setText("");
            this.label3.setText("");
            this.label4.setText("");
            this.parent.setText("");
            this.spouse.setText("");
            this.child.setText("");
        }
    }
    @FXML
    private void handleDeleteMember() throws IOException {
        int selectedIndex = this.familyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.familyTable.getItems().remove(selectedIndex);
            ObservableList<Member> tableNow = this.familyTable.getItems();
            Member.updateFile(tableNow);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner((Window)null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран член семьи");
            alert.setContentText("Выберите члена семьи в таблице");
            alert.showAndWait();
        }

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
        Stage stage1 = (Stage) exit.getScene().getWindow();
        stage1.close();
    }
}

