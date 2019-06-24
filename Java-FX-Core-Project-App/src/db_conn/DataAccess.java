package db_conn;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class DataAccess {

    private Connection conn;
    private static final String teacherTable = "teacher";
    private static final String studentTable = "student";
    private static final String subjectTable = "subject";
    private static final String gradeTable = "grade";
    private static final String classTable = "classes";

    public DataAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        //STEP 2: Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql:173.212.235.205/benjamin_java_core_project" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "benjamin_Benjo",
                "");

        // we will use this connection to write to a file
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
    public List<> getAllRows()  throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacherName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<> list = new ArrayList<>();

        while (rs.next()) {
            int i = rs.getInt("teacherId");
            String name = rs.getString("teacherName");
            String surname = rs.getString("teacherSurname");
            String email = rs.getString("teacherEmail");
            list.add(new (i, name, surname, email));
        }

        pstmnt.close(); // also closes related result set
        return list;
    }

    public List<> getAllRows2(int i) throws SQLException {

        String sql = "SELECT class.classId, class.className FROM class INNER JOIN teacherclass ON class.classId = teacherclass.fk_classId WHERE teacherclass.fk_teacherId = ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1, i);
        ResultSet rs = pstmnt.executeQuery();
        List<> listClasses = new ArrayList<>();

        while (rs.next()) {
            int i2 = rs.getInt("classId");
            String name = rs.getString("className");
            listClasses.add(new (i2, name));
        }
        pstmnt.close();
        return listClasses;
    }
}
