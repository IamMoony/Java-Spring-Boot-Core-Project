package app;

import db_conn.DataAccess;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tables.*;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoreApplication extends Application {

    // List Views
    private ListView<Teacher> listViewTeachers;
    private ListView<Subject> listViewSubjects;
    private ListView<Classes> listViewClassesT;
    private ListView<Student> listViewStudents;
    private ListView<Subject> listViewGradesS;
    private ListView<Classes> listViewClasses;
    private ListView<Student> listViewClassesS;

    // Data
    private ObservableList<Teacher> teacherData;
    private ObservableList<Subject> teacherDataSubjects;
    private ObservableList<Classes> teacherDataClasses;
    private ObservableList<Student> studentData;
    private ObservableList<Grade> studentDataGrades;
    private ObservableList<Grade> gradeData;
    private ObservableList<Classes> classData;
    private ObservableList<Student> classDataStudents;


    //Gridpane and box
    private GridPane gradeBox;
    VBox vBoxR2Obj4;

    // Text Fields
    private Text txtAddressField;
    private Text txtcontactPerson;
    private Text txtClass;

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
        Label studentLabel = new Label("Student");
        Label studentGradesLabel = new Label("Grades");
        Label studentAddressLabel = new Label("Address");
        Label studentContactPLabel = new Label("Contact Person");
        Label classLabel = new Label("Class");
        Label studentsLabel = new Label("Students");

        // VBox Root
        VBox root = new VBox();

        // First Row Objects with Labels
        VBox vBoxR1Obj1 = new VBox(teacherLabel);
        VBox vBoxR1Obj2 = new VBox(hasSubjectsLabel);
        VBox vBoxR1Obj3 = new VBox(teachesLabel);

        // Second Row Objects with Labels
        VBox vBoxR2Obj1 = new VBox(studentLabel);
        VBox vBoxR2Obj2 = new VBox(studentAddressLabel);
        VBox vBoxR2Obj3 = new VBox(studentContactPLabel);
        vBoxR2Obj4 = new VBox(studentGradesLabel);
        VBox vBoxR2Obj5 = new VBox(classLabel);

        // Third Row Objects with Labels
        VBox vBoxR3Obj1 = new VBox(classLabel);
        VBox vBoxR3Obj2 = new VBox(studentsLabel);


        // First Row
        HBox hBoxFirstR = new HBox(vBoxR1Obj1, vBoxR1Obj2, vBoxR1Obj3);
        // Second Row
        HBox hBoxSecondR = new HBox(vBoxR2Obj1, vBoxR2Obj2, vBoxR2Obj3, vBoxR2Obj4, vBoxR2Obj5);
        // Third Row
        HBox hBoxThirdR = new HBox(vBoxR3Obj1, vBoxR3Obj2);

        // Initialize Text Fields
        txtAddressField = new Text();
        txtcontactPerson = new Text();
        txtClass = new Text();

        //Gridpane for grades
        GridPane gradeBox = new GridPane();

        // View Teacher
        listViewTeachers = new ListView<>();
        listViewTeachers.getSelectionModel().selectedIndexProperty().addListener(new ListSelectChangeListenerTeacher());
        teacherData = getTeacherData();
        listViewTeachers.setItems(teacherData);

        // View Subjects
        listViewSubjects = new ListView<>();

        // View ClassesT
        listViewClassesT = new ListView<>();

        // View Students
        listViewStudents = new ListView();
        listViewStudents.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerStudent());
        studentData = getStudentData();
        listViewStudents.setItems(studentData);

        // View Classes
        listViewClasses = new ListView<>();
        listViewClasses.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerClass());
        classData = getClassData();
        listViewClasses.setItems(classData);

        // View For The Students In The Class

        listViewClassesS = new ListView<>();


        //hBoxHiddenId.getChildren().add(txtHiddenIdField);

        // Set Views
        vBoxR1Obj1.getChildren().add(listViewTeachers);
        vBoxR1Obj2.getChildren().add(listViewSubjects);
        vBoxR1Obj3.getChildren().add(listViewClassesT);
        vBoxR2Obj1.getChildren().add(listViewStudents);
        vBoxR2Obj2.getChildren().add(txtAddressField);
        vBoxR2Obj3.getChildren().add(txtcontactPerson);
        vBoxR2Obj4.getChildren().add(gradeBox);
        vBoxR2Obj5.getChildren().add(txtClass);
        vBoxR3Obj1.getChildren().add(listViewClasses);
        vBoxR3Obj2.getChildren().add(listViewClassesS);

        root.getChildren().addAll(hBoxFirstR, hBoxSecondR, hBoxThirdR);

        // show
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private class ListSelectChangeListenerTeacher implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= teacherData.size())) {

                return; // invalid data
            }

               // Set Teacher Data in List View
               Teacher teacher = teacherData.get(new_val.intValue());
               teacherDataSubjects = getSubjectData(teacher.getId());
               teacherDataClasses = getTeacherDataClasses(teacher.getId());

               // Set Subject Data in List View
               listViewSubjects.setItems(teacherDataSubjects);

               // Set Class Data in List View
               listViewClassesT.setItems(teacherDataClasses);

        }
    }

    private class ListSelectChangeListenerStudent implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= teacherData.size())) {

                return; // invalid data
            }

            // Set Student and Student Data in List View / Text Fields
            Student student = studentData.get(new_val.intValue());
            txtAddressField.setText(student.getAddress());
            txtcontactPerson.setText(student.getContactPerson());

            try {
                txtClass.setText(dbDataAccess.getStudentClass(student.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                vBoxR2Obj4.getChildren().remove(gradeBox);

                ArrayList<String> studentsSubjects = dbDataAccess.getStudentSubjects(student.getId());
                gradeBox = new GridPane();
                gradeBox.setHgap(5);
                gradeBox.setVgap(5);

                for (int index = 0; index < studentsSubjects.size(); index++) {
                    Label subject = new Label(studentsSubjects.get(index));
                    System.out.println(studentsSubjects.get(index));
                    gradeBox.add(subject, 1, index + 1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                ArrayList<String> studentsGrades = dbDataAccess.getStudentGrades(student.getId());

                for (int index = 0; index < studentsGrades.size(); index++) {
                    Label subject = new Label(studentsGrades.get(index));
                    System.out.println(studentsGrades.get(index));
                    gradeBox.add(subject, 2, index + 1);
                }

                vBoxR2Obj4.getChildren().add(gradeBox);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private class ListSelectChangeListenerClass implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= teacherData.size())) {

                return; // invalid data
            }
            // View for the class
            Classes classes = classData.get(new_val.intValue());

            // View for the students in a specific class
            classDataStudents = getClassDataStudents(classes.getId());
            listViewClassesS.setItems(classDataStudents);


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

    private ObservableList<Classes> getTeacherDataClasses(int i) {

        List<Classes> classList = null;

        try {
            classList = dbDataAccess.getClassDataT(i);
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Classes> dbData = FXCollections.observableList(classList);
        return dbData;
    }

    private ObservableList<Student> getStudentData() {

        List<Student> studentList = null;

        try {
            studentList = dbDataAccess.getStudentData();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Student> dbData = FXCollections.observableList(studentList);
        return dbData;
    }

    private ObservableList<Classes> getClassData() {

        List<Classes> classList = null;

        try {
            classList = dbDataAccess.getClassData();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Classes> dbData = FXCollections.observableList(classList);
        return dbData;
    }

    private ObservableList<Student> getClassDataStudents(int i) {

        List<Student> classStudentList = null;

        try {
            classStudentList = dbDataAccess.getClassDataS(i);
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Student> dbData = FXCollections.observableList(classStudentList);
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

