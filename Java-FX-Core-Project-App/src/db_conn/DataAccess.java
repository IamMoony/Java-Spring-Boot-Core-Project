package db_conn;

import tables.Student;
import tables.Subject;
import tables.Teacher;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class DataAccess {

    private Connection conn;
    private static final String teacherTable = "teachers";
    private static final String studentTable = "students";
    private static final String subjectTable = "subjects";
    private static final String gradeTable = "grades";
    private static final String classTable = "classes";

    public DataAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        // Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open Connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/java_group_project_test" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // Write a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

    /**
     * Get all db records
     * @return
     * @throws SQLException
     */

    /*
    ##################
    FETCH TEACHER DATA
    ##################
     */
    public List<Teacher> getTeacherData()  throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacherName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();

        List<Teacher> teacherList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("teacher_id");
            String name = rs.getString("teacherName");
            String surname = rs.getString("teacherSurname");
            String address = rs.getString("teacherAddress");
            String email = rs.getString("teacherEmail");

            teacherList.add(new Teacher(id, name, surname, address, email));
        }

        pstmnt.close(); // also closes related result set
        return teacherList;
    }

    /*
  ##################
  FETCH SUBJECT DATA
  ##################
   */
    public List<Subject> getSubjectData(int i)  throws SQLException {

        // String sql = "SELECT subjects.subject_id, subjects.subjectName FROM subjects INNER JOIN teachersubject ON subjects.subject_Id  = teachersubject.fk_subject_Id WHERE teachersubject.fk_teacher_Id = ?";
        // "SELECT class.classId, class.className FROM class INNER JOIN teacherclass ON class.classId = teacherclass.fk_classId WHERE teacherclass.fk_teacherId = ?";
        String sql = "SELECT * FROM " + subjectTable;
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();

        List<Subject> subjectList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("subject_id");
            String name = rs.getString("subjectName");
            subjectList.add(new Subject(id, name));
        }

        pstmnt.close(); // also closes related result set
        return subjectList;
    }

    /*
    ##################
    FETCH STUDENT DATA
    ##################
     */
    public List<Student> getStudentData() throws SQLException {

        String sql = "SELECT * FROM " + studentTable + " ORDER BY studentName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();

        List<Student> studentList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("student_id");
            String name = rs.getString("studentName");
            String surname = rs.getString("studentSurname");
            String address = rs.getString("studentAddress");
            String contactPerson = rs.getString("studentContactPerson");
            int fk_class_id = rs.getInt("fk_class_id");

           studentList.add(new Student(id, name, surname, address, contactPerson, fk_class_id));
        }

        pstmnt.close(); // also closes related result set
        return studentList;
    }


    /*
    ##################
    FETCH CLASS DATA
    ##################
     */
    /*
    public List<Classes> getClassData() throws SQLException {

        String sql = "SELECT * FROM " + classTable + " ORDER BY className";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();

        List<Classes> classList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("class_id");
            String name = rs.getString("className");

            classList.add(new Classes(id, name));
        }

        pstmnt.close(); // also closes related result set
        return classList;
    }
*/
}