package com.coderscampus.assignment4;

public class Student implements Comparable<Student> {
    private String studentID;
    private String studentName;
    private String course;
    private String grade;

    public Student(String studentID, String studentName, String course, String grade) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return studentID + "," + studentName + "," + course + "," + grade + "\n";
    }

    @Override
    public int compareTo(Student student) {
        int comparison = 0;
        try {
            comparison = Integer.parseInt(student.getGrade()) - Integer.parseInt(this.getGrade());
        } catch (NumberFormatException e) {
            System.out.println("Student grade String does not contain a parsable integer!");
        }
        return comparison;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
