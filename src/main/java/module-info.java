module com.example.assignment3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assignment4 to javafx.fxml;
    exports com.example.assignment4;
}