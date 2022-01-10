module com.example.lab_6_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens GUI to javafx.fxml;
    opens Student to javafx.fxml;
    exports GUI;
    exports Student;
}