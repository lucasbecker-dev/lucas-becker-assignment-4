package com.coderscampus.assignment4;

public class Assignment4Runnable {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        StudentService studentService = new StudentService(fileService);
        StudentReportService reportService = new StudentReportService(fileService, studentService);

        reportService.generateCourseStudentsReport("COMPSCI", "course1.csv");
        reportService.generateCourseStudentsReport("APMTH", "course2.csv");
        reportService.generateCourseStudentsReport("STAT", "course3.csv");
    }
}