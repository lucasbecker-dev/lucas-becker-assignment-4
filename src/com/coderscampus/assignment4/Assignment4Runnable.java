package com.coderscampus.assignment4;

public class Assignment4Runnable {
    public static void main(String[] args) {
        final String FILE_PATH = "src/com/coderscampus/assignment4/student-master-list.csv";
        StudentService.parseFile(FILE_PATH);
    }
}