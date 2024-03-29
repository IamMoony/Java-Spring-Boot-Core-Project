package app;

import com.sun.prism.paint.Color;
import db_conn.DataAccess;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tables.*;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private VBox vBoxR2Obj4;
    private HBox hBoxSecondRB;
    private Button btnassignStudentGrade;
    private Button printReport;

    // Text Fields
    private Text txtAddressField;
    private Text txtcontactPerson;
    private Text txtClass;

    // New Combobox
    private ComboBox comboBoxSubj;
    private ComboBox comboBoxGra;


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

        BorderPane bpRoot = new BorderPane();

        // Labels
        Label teacherLabel = new Label("Teacher");
        teacherLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label hasSubjectsLabel = new Label("has subjects");
        hasSubjectsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label teachesLabel = new Label("teaches");
        teachesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentLabel = new Label("Student");
        studentLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentGradesLabel = new Label("Grades");
        studentGradesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentAddressLabel = new Label("Address");
        studentAddressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentContactPLabel = new Label("Contact Person");
        studentContactPLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label classLabel = new Label("Class");
        classLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label newClass = new Label("Class");
        newClass.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentsLabel = new Label("Students");
        studentsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label studentStatusLabel = new Label("Status");
        studentsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label subjectsLabel = new Label("Assign Student Grade");
        subjectsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        Label rootHeadingLabel = new Label("School Administration Tool");
        rootHeadingLabel.setFont(new Font("Arial", 20));
        rootHeadingLabel.setPadding(new Insets(20));

         // Buttons
         btnassignStudentGrade = new Button("Assign Grade");
         btnassignStudentGrade.setCursor(Cursor.HAND);

        printReport = new Button("Print Report");
        printReport.setCursor(Cursor.HAND);

        btnassignStudentGrade.setOnAction(e -> {
            Subject comboBoxSubjValue = (Subject) comboBoxSubj.getValue();
            int comboBoxSubjValueInt = comboBoxSubjValue.getId();

            String comboBoxGraValue =  (String) comboBoxGra.getValue();
            int comboBoxGraValueInt = Integer.valueOf(comboBoxGraValue);

            int listViewStudentsInt = listViewStudents.getSelectionModel().getSelectedItem().getId();

            System.out.println(comboBoxGraValueInt + " " + listViewStudentsInt + " " + comboBoxSubjValueInt);

            try {
                dbDataAccess.updateStudentGrade(comboBoxGraValueInt, listViewStudentsInt, comboBoxSubjValueInt);
                successGrade();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        printReport.setOnAction(e -> {
            int listViewStudentsInt = listViewStudents.getSelectionModel().getSelectedItem().getId();

            String firstname = listViewStudents.getSelectionModel().getSelectedItem().getName();
            String lastname = listViewStudents.getSelectionModel().getSelectedItem().getSurname();

            try {
                ArrayList<String> oneStudentReport = dbDataAccess.createStudentReport(listViewStudentsInt);
                FileWriter fileWriter = new FileWriter("./src/" + firstname + lastname + ".txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("SEMESTER REPORT FOR " + firstname.toUpperCase() + " " + lastname.toUpperCase());
                printWriter.println("=====================================");
                for(String report : oneStudentReport){
                    printWriter.println(report);
                }

                printWriter.close();

                successReport();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });

        // VBox Root
        VBox root = new VBox(rootHeadingLabel);
        root.setPadding(new Insets(10, 50, 50, 50));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // studentDataAddress = new HBox(studentAddressLabel, txtAddressField);
       //  HBox studentDataACP = new HBox (studentContactPLabel, txtcontactPerson);
       //  HBox studentDataClass = new HBox (classLabel, txtClass);



        // First Row Objects with Labels
        VBox vBoxR1Obj1 = new VBox(teacherLabel);
        vBoxR1Obj1.setMaxHeight(400);
        vBoxR1Obj1.setMaxWidth(400);
        VBox vBoxR1Obj2 = new VBox(hasSubjectsLabel);
        vBoxR1Obj2.setMaxHeight(400);
        vBoxR1Obj2.setMaxWidth(400);
        VBox vBoxR1Obj3 = new VBox(teachesLabel);
        vBoxR1Obj3.setMaxHeight(400);
        vBoxR1Obj3.setMaxWidth(400);

        //CSS layout
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-background-color: #fff";

        // Second Row Objects with Labels
        VBox vBoxR2Obj1 = new VBox(studentLabel);
        vBoxR2Obj1.setMaxHeight(400);
        vBoxR2Obj1.setMaxWidth(400);
        VBox vBoxR2Obj2 = new VBox(studentAddressLabel);
        vBoxR2Obj2.setMaxHeight(400);
        vBoxR2Obj2.setMaxWidth(400);
        vBoxR2Obj2.setPadding(new Insets(10));
        vBoxR2Obj2.setStyle(cssLayout);
        VBox vBoxR2Obj3 = new VBox(studentContactPLabel);
        vBoxR2Obj3.setMaxHeight(400);
        vBoxR2Obj3.setMaxWidth(400);
        vBoxR2Obj3.setPadding(new Insets(10));
        vBoxR2Obj3.setStyle(cssLayout);
        vBoxR2Obj4 = new VBox(studentGradesLabel);
        vBoxR2Obj4.setMaxHeight(400);
        vBoxR2Obj4.setMaxWidth(400);
        vBoxR2Obj4.setStyle(cssLayout);
        vBoxR2Obj4.setPadding(new Insets(10));
        VBox vBoxR2Obj5 = new VBox(newClass);
        vBoxR2Obj5.setMaxHeight(400);
        vBoxR2Obj5.setMaxWidth(400);
        vBoxR2Obj5.setPadding(new Insets(10));
        vBoxR2Obj5.setStyle(cssLayout);

        // VBox vBoxR2Obj6 = new VBox(studentStatusLabel);
        // vBoxR2Obj6.setMaxHeight(400);
        // vBoxR2Obj6.setMaxWidth(400);
        // vBoxR2Obj6.setStyle(cssLayout);

        // Third row objects
        VBox vBoxR3Obj1 = new VBox(classLabel);
        VBox vBoxR3Obj2 = new VBox(studentsLabel);

        // First row
        HBox hBoxFirstR = new HBox(vBoxR1Obj1, vBoxR1Obj2, vBoxR1Obj3);
        hBoxFirstR.setSpacing(50);
        Region region1 = new Region();
        // HBox.setHgrow(vBoxR1Obj3, Priority.ALWAYS);

        // Second row
        HBox hBoxSecondR = new HBox(vBoxR2Obj1, vBoxR2Obj2, vBoxR2Obj3, vBoxR2Obj4, vBoxR2Obj5);
        hBoxSecondR.setSpacing(50);
        // HBox.setHgrow(hBoxSecondR, Priority.ALWAYS);

        // Extra row for button
        hBoxSecondRB = new HBox(subjectsLabel);
        hBoxSecondRB.setSpacing(10);
        // HBox.setHgrow(hBoxSecondRB, Priority.ALWAYS);

        // Third row
        HBox hBoxThirdR = new HBox(vBoxR3Obj1, vBoxR3Obj2);
        hBoxThirdR.setSpacing(50);
        // HBox.setHgrow(hBoxThirdR, Priority.ALWAYS);

        // Initialize Text Fields
        txtAddressField = new Text();
        txtcontactPerson = new Text();
        txtClass = new Text();

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
        vBoxR2Obj5.getChildren().add(txtClass);
        vBoxR3Obj1.getChildren().add(listViewClasses);
        vBoxR3Obj2.getChildren().add(listViewClassesS);

        root.getChildren().addAll(hBoxFirstR, hBoxSecondR, hBoxSecondRB, hBoxThirdR);
        bpRoot.setCenter(root);
        root.setAlignment(Pos.CENTER_LEFT);

        // show
        Scene scene = new Scene(bpRoot, 1200, 750);
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

            if ((new_val.intValue() < 0) || (new_val.intValue() >= studentData.size())) {

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

                ArrayList<Subject> studentsSubjects = dbDataAccess.getStudentSubjects(student.getId());
                gradeBox = new GridPane();
                gradeBox.setHgap(5);
                gradeBox.setVgap(5);

                for (int index = 0; index < studentsSubjects.size(); index++) {
                    Label subject = new Label(studentsSubjects.get(index).getName());
                    gradeBox.add(subject, 1, index + 1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                ArrayList<String> studentsGrades = dbDataAccess.getStudentGrades(student.getId());

                for (int index = 0; index < studentsGrades.size(); index++) {
                    Label subject = new Label(studentsGrades.get(index));
                    gradeBox.add(subject, 2, index + 1);
                }

                vBoxR2Obj4.getChildren().add(gradeBox);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                hBoxSecondRB.getChildren().remove(comboBoxSubj);
                hBoxSecondRB.getChildren().remove(btnassignStudentGrade);
                hBoxSecondRB.getChildren().remove(printReport);

                ArrayList<Subject> comboBoxSubjects = dbDataAccess.getStudentSubjects(student.getId());

                // ComboBox
                comboBoxSubj = new ComboBox();
                ObservableList<Subject> subjects = FXCollections.observableArrayList();
                subjects.addAll(comboBoxSubjects);

                comboBoxSubj.setItems(subjects);

                hBoxSecondRB.getChildren().addAll(comboBoxSubj, btnassignStudentGrade, printReport);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            hBoxSecondRB.getChildren().remove(comboBoxGra);
            hBoxSecondRB.getChildren().remove(btnassignStudentGrade);
            hBoxSecondRB.getChildren().remove(printReport);

            ArrayList<String> comboBoxGrades = null;
            try {
                comboBoxGrades = dbDataAccess.getGradeData();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // ComboBox
            comboBoxGra = new ComboBox();
            ObservableList<String> grades = FXCollections.observableArrayList();
            grades.addAll(comboBoxGrades);

            comboBoxGra.setItems(grades);

            hBoxSecondRB.getChildren().addAll(comboBoxGra, btnassignStudentGrade, printReport);

        }
    }
    private class ListSelectChangeListenerClass implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= classData.size())) {

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

    private void successReport(){
        Alert successReport = new Alert(Alert.AlertType.INFORMATION);
        successReport.setTitle("Success");
        successReport.setHeaderText(null);
        successReport.setContentText("Report successfully created.");
        successReport.showAndWait();
    }

    private void successGrade(){
        Alert successReport = new Alert(Alert.AlertType.INFORMATION);
        successReport.setTitle("Success");
        successReport.setHeaderText(null);
        successReport.setContentText("Grade successfully assigned");
        successReport.showAndWait();
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

