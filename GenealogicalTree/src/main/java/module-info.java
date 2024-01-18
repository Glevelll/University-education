module com.example.genealogicaltree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.genealogicaltree to javafx.fxml;
    exports com.example.genealogicaltree;
}