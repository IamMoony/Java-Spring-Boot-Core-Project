package app;

import db_conn.DataAccess;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tables.*;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class CoreApplication extends Application {

    // List Views
    private ListView<Teacher> listViewTeachers;
    private ListView<Subject> listViewSubjects;
    private ListView<Classes> listViewClassesT;
    private ListView<Subject> listViewStudents;
    private ListView<Subject> listViewGradesS;
    private ListView<Classes> listViewClasses;
    private ListView<Subject> listViewClassesSubj;

    // Data
    private ObservableList<Teacher> teacherData;
    private ObservableList<Subject> teacherDataSubjects;
    private ObservableList<Classes> teacherDataClasses;
    private ObservableList<Student> studentData;
    private ObservableList<Grade> studentDataGrades;
    private ObservableList<Grade> gradeData;
    // private ObservableList<Student> studentDataGrades;


    // Text Fields
    private Text txtAddressField;
    private Text txtcontactPerson;
    private Text txtGrade;
    private Text txtClass;
    private Text txtHiddenIdField;

    // Get ID field

    private Text txtTeacherId;

    // DB Access
    private DataAccess dbDataAccess;

    @Override
    public void init() {

        try {
            dbDataAccess = new DataAccess();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
           dbDataAccess.closeDb();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("School Application");

        // GridPane
        /*
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
        */

        // Labels
        Label teacherLabel = new Label("Teacher");
        Label hasSubjectsLabel = new Label("has subjects");
        Label teachesLabel = new Label("teaches");

        // VBox Root
        VBox root = new VBox();

        // First Row Objects
        VBox vBoxR1Obj1 = new VBox(teacherLabel);
        VBox vBoxR1Obj2 = new VBox(hasSubjectsLabel);
        VBox vBoxR1Obj3 = new VBox(teachesLabel);

        // Second Row Objects
        //VBox vBoxR2Obj1 = new VBox();
        //VBox hBoxR2Obj2 = new VBox();
        //VBox hBoxR2Obj3 = new VBox();
        //VBox hBoxR2Obj4 = new VBox();
        //VBox hBoxR2Obj5 = new VBox();

        // Third Row Objects
        VBox vBoxR3Obj1 = new VBox();
        VBox vBoxR3Obj2 = new VBox();

        // First Row
        HBox hBoxFirstR = new HBox(vBoxR1Obj1, vBoxR1Obj2, vBoxR1Obj3);
        // Second Row
        HBox hBoxSecondR = new HBox();
        // Third Row
        HBox hBoxThirdR = new HBox();

        // Hidden HBOX
        HBox hBoxHiddenId = new HBox();




        // View Teacher

        listViewTeachers = new ListView<>();
        listViewTeachers.getSelectionModel().selectedIndexProperty();
        teacherData = getTeacherData();
        listViewTeachers.setItems(teacherData);

        listViewSubjects = new ListView<>();
        // teacherDataSubjects = getSubjectData();



        vBoxR1Obj1.getChildren().add(listViewTeachers);
        vBoxR1Obj2.getChildren().add(listViewSubjects);


        // vBoxR1Obj2.getChildren().add(listViewSubjects);

        root.getChildren().addAll(hBoxFirstR, hBoxSecondR, hBoxThirdR, hBoxHiddenId);

        // show
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private class ListSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= teacherData.size())) {

                return; // invalid data
            }

            // Set Subjects in List View

               Teacher teacher = teacherData.get(new_val.intValue());
                txtHiddenIdField.setText(Integer.toString(teacher.getId()));

               teacherDataSubjects = getSubjectData(Integer.valueOf(txtHiddenIdField.getText()));

               listViewSubjects.setItems(teacherDataSubjects);

        }
    }

    private ObservableList<Teacher> getTeacherData() {

        List<Teacher> teacherList = null;

        try {
            teacherList = dbDataAccess.getTeacherData();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Teacher> dbData = FXCollections.observableList(teacherList);
        return dbData;
    }

    private ObservableList<Subject> getSubjectData(int i) {

        List<Subject> subjectList = null;

        try {
            subjectList = dbDataAccess.getSubjectData(i);
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Subject> dbData = FXCollections.observableList(subjectList);
        return dbData;
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

    // Display Exception

    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
}

