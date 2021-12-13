package GUI;

import Student.Student;
import Student.Class;
import Student.ClassContainer;
import Student.StudentCondition;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ClassContainer classContainer = new ClassContainer();



    @FXML
    private ComboBox<StudentCondition> cmbBox;

    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    @FXML
    private TableColumn<Student, StudentCondition> status;


    @FXML
    private TableView<Class> table_1 = new TableView<Class>();

    @FXML
    private TableColumn<Class, String> className;

    @FXML
    private TableColumn<Class, Integer> noStudents;


    @FXML
    private TableView<Student> table_2;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, Double> points;

    @FXML
    private TableColumn<Student, String> secondName;


    ObservableList<Student> listOfStudents;
    ObservableList<Class> listOfClasses;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listOfStudents = FXCollections.observableArrayList();
        listOfClasses = FXCollections.observableArrayList();


        name.setCellValueFactory(new PropertyValueFactory<>("imie"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        points.setCellValueFactory(new PropertyValueFactory<>("iloscPunktow"));
//        points.setCellValueFactory(new PropertyValueFactory<>("stanStudenta"));


        className.setCellValueFactory(new PropertyValueFactory<>("nazwaGrupy"));
        noStudents.setCellValueFactory(new PropertyValueFactory<>("iloscStudentow"));

//        listOfClasses.add(new Class(List.of(new Student("Pawel"))));


        cmbBox.getItems().setAll(StudentCondition.values());

        loadTableData();
        events();

    }



    private void loadTableData() {

        listOfClasses = FXCollections.observableArrayList(classContainer.getListaGrup());



        table_1.setItems(listOfClasses);


        table_1.setOnMouseClicked(e -> {
                    events();
                }
        );
        table_2.setOnMouseClicked(e -> {
                    eventsTable2();
                }
        );
    }

    private void events() {
       Class a =  table_1.getSelectionModel().getSelectedItem();
       if (a != null) {
           System.out.println(a);
           listOfStudents = FXCollections.observableArrayList(a.getListaStudentow());
           table_2.setItems(listOfStudents);
       }
    }
    private void eventsTable2(){
        Student a = table_2.getSelectionModel().getSelectedItem();
        if (a != null) {
//            cmbBox.set
            cmbBox.getSelectionModel().select(a.getStanStudenta());
        }
    }


}