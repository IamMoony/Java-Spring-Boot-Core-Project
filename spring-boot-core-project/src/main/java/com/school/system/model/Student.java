package com.school.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int studentid;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentSurname")
    private String studentSurname;

    @Column(name = "studentAddress")
    private String studentAddress;

    @Column(name = "studentContactPerson")
    private String studentContactPerson;

    @Column(name = "fk_class_id")
    private int fk_class_id;

    @Override
    public String toString() {
        return studentName + " " + studentSurname;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int student_id) {
        this.studentid = student_id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentContactPerson() {
        return studentContactPerson;
    }

    public void setStudentContactPerson(String studentContactPerson) {
        this.studentContactPerson = studentContactPerson;
    }

    public int getFk_class_id() {
        return fk_class_id;
    }

    public void setFk_class_id(int fk_class_id) {
        this.fk_class_id = fk_class_id;
    }
}
