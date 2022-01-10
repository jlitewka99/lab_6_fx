package GUI;

import Database.Database;
import Student.ClassContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

public class Main extends Application {

    Database database = new Database();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ClassContainer classContainer = new ClassContainer();
        classContainer.infill();

        Database.setClassesR(classContainer.getListaGrup());

        Database.saveDatabase();
        Database.saveToCsvFile();
        System.out.println("e");
//        Database.saveToCsvFile();


        launch();
    }
}