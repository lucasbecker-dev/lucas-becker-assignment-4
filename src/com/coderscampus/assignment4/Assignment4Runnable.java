package com.coderscampus.assignment4;

public class Assignment4Runnable {
    public static void main(String[] args) {
        final String filePath = "src/com/coderscampus/assignment4/student-master-list.csv";
        StudentService.parseFile(filePath);
    }
}