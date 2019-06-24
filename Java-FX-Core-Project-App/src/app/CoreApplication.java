package app;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class CoreApplication extends Application {

    public void start(Stage primaryStage) throws Exception {

        // GridPane
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Label teacherLabel = new Label();
        Text teacherId = new Text();
        Text teacherName = new Text();
        Text teacherSurname = new Text();
        Text teacherAddress = new Text();
        Text teacherEmail = new Text();

        Label subjectLabel = new Label();
        Text subjectId = new Text();
        Text subjectName = new Text();

        Label classesLabel = new Label();
        Text classId = new Text();
        Text className = new Text();

        GridPane.setHalignment(teacherLabel, HPos.RIGHT);
        root.add(teacherLabel,0,1);

        GridPane.setHalignment(subjectLabel, HPos.CENTER);
        root.add(subjectLabel,0,2);

        GridPane.setHalignment(classesLabel, HPos.LEFT);
        root.add(classesLabel,0,3);



        // show
        Scene scene = new Scene(root, 1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
