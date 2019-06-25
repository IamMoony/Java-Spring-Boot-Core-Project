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
    private ListView<Student> listViewStudents;
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
    private Text txtGradeMath;
    private Text txtGradeEng;
    private Text txtGradePhys;
    private Text txtGradeChem;
    private Text txtGradeGer;
    private Text txtGradeBio;
    private Text txtGradeSpan;
    private Text txtGradeIt;
    private Text txtGradeHis;
    private Text txtClass;

    // Get ID
    private Text txtHiddenIdField;

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
        Label studentAddressLabel = new Label("Address");
        Label studentContactPLabel = new Label("Contact Person");
        Label classLabel = new Label("Class");
        Label studentsLabel = new Label("Students");

        // Labels Grades
        Label mathLabel = new Label("Math");
        Label englLabel = new Label("English");
        Label physLabel = new Label("Physics");
        Label chemLabel = new Label("Chemistry");
        Label gerLabel = new Label("German");
        Label bioLabel = new Label("Biology");
        Label spanLabel = new Label("Spanish");
        Label itLabel = new Label("IT");
        Label histLabel = new Label("History");

        // VBox Root
        VBox root = new VBox();

        // First Row Objects with Labels
        VBox vBoxR1Obj1 = new VBox(teacherLabel);
        VBox vBoxR1Obj2 = new VBox(hasSubjectsLabel);
        VBox vBoxR1Obj3 = new VBox(teachesLabel);

        // Second Row Objects with Labels
        VBox vBoxR2Obj1 = new VBox(studentLabel);
        VBox vBoxR2Obj2 = new VBox();
        VBox vBoxR2Obj3 = new VBox(studentContactPLabel);
        VBox vBoxR2Obj4 = new VBox();
        VBox vBoxR2Obj5 = new VBox(classLabel);

        // Third Row Objects with Labels
        VBox vBoxR3Obj1 = new VBox(classLabel);
        VBox vBoxR3Obj2 = new VBox(studentsLabel);


        // HBox Grades
        HBox hBoxGradeMath = new HBox(txtGradeMath);
         /*
        HBox hBoxGradeEngl = new HBox(englLabel, txtGradeEng);
        HBox hBoxGradePhys = new HBox(physLabel, txtGradePhys);
        HBox hBoxGradeChem = new HBox(chemLabel, txtGradeChem);
        HBox hBoxGradeGer = new HBox(gerLabel, txtGradeGer);
        HBox hBoxGradeBio = new HBox(bioLabel, txtGradeBio);
        HBox hBoxGradeSpanish = new HBox(spanLabel, txtGradeSpan);
        HBox hBoxGradeIT = new HBox(itLabel, txtGradeIt);
        HBox hBoxGradeHist = new HBox(histLabel, txtGradeHis);
          */

        // First Row
        HBox hBoxFirstR = new HBox(vBoxR1Obj1, vBoxR1Obj2, vBoxR1Obj3);
        // Second Row
        HBox hBoxSecondR = new HBox(vBoxR2Obj1, vBoxR2Obj2, vBoxR2Obj3, vBoxR2Obj4, vBoxR2Obj5);
        // Third Row
        HBox hBoxThirdR = new HBox(vBoxR3Obj1, vBoxR3Obj2);

        // Initialize Text Fields
        txtHiddenIdField = new Text();
        txtAddressField = new Text();
        txtcontactPerson = new Text();
        txtGradeMath = new Text();
        txtGradeEng = new Text();
        txtGradePhys = new Text();
        txtGradeChem = new Text();
        txtGradeGer = new Text();
        txtGradeSpan = new Text();
        txtGradeBio = new Text();
        txtGradeHis = new Text();
        txtGradeIt = new Text();
        txtClass = new Text();

        // Hidden ID
        // HBox hBoxHiddenId = new HBox(txtHiddenIdField);

        // View Teacher
        listViewTeachers = new ListView<>();
        listViewTeachers.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        teacherData = getTeacherData();
        listViewTeachers.setItems(teacherData);

        // View Subjects
        listViewSubjects = new ListView<>();

        // View Classes
        listViewClasses = new ListView<>();

        // View Students
        listViewStudents = new ListView();
        listViewStudents.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        studentData = getStudentData();
        listViewStudents.setItems(studentData);


        //hBoxHiddenId.getChildren().add(txtHiddenIdField);

        // Set Views
        vBoxR1Obj1.getChildren().add(listViewTeachers);
        vBoxR1Obj2.getChildren().add(listViewSubjects);
        vBoxR1Obj3.getChildren().add(listViewClasses);
        vBoxR2Obj1.getChildren().add(listViewStudents);
        vBoxR2Obj2.getChildren().addAll(hBoxGradeMath);
        // vBoxR2Obj3.getChildren().add(txtcontactPerson);

        root.getChildren().addAll(hBoxFirstR, hBoxSecondR, hBoxThirdR);

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

               // Set Teachers in List View
               Teacher teacher = teacherData.get(new_val.intValue());
               txtHiddenIdField.setText(Integer.toString(teacher.getId()));
               teacherDataSubjects = getSubjectData(Integer.valueOf(txtHiddenIdField.getText()));

               // Set Subjects in List View
               listViewSubjects.setItems(teacherDataSubjects);

               // Set Student and Student Data in List View / Text Fields
               Student student = studentData.get(new_val.intValue());
               txtAddressField.setText(student.getAddress());
               txtcontactPerson.setText(student.getContactPerson());
               // txtClass.setText(student.getClass());

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

