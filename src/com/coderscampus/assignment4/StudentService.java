package com.coderscampus.assignment4;

import java.io.*;
import java.util.ArrayList;

public class StudentService {

    public static void parseFile(String filePath) {

        // setup local variables
        final String DESTINATION_PATH = "src/com/coderscampus/assignment4";
        final String FILE_NAME_1 = DESTINATION_PATH + "course1.csv";
        final String FILE_NAME_2 = DESTINATION_PATH + "course2.csv";
        final String FILE_NAME_3 = DESTINATION_PATH + "course3.csv";
        final String COURSE_NAME_1 = "COMPSCI";
        final String COURSE_NAME_2 = "APMTH";
        final String COURSE_NAME_3 = "STAT";
        final int STUDENT_ARRAY_SIZE = 100;
        Student[] course1Students = new Student[STUDENT_ARRAY_SIZE];
        Student[] course2Students = new Student[STUDENT_ARRAY_SIZE];
        Student[] course3Students = new Student[STUDENT_ARRAY_SIZE];

        // initialize readers and writers in try with resources block
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(FILE_NAME_1));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(FILE_NAME_2));
             BufferedWriter writer3 = new BufferedWriter(new FileWriter(FILE_NAME_3));) {

            // write file header to each file
            final String fileHeader = "Student ID,Student Name,Course,Grade\n";
            writer1.write(fileHeader);
            writer2.write(fileHeader);
            writer3.write(fileHeader);

            // read through csv file and parse into appropriate Student arrays
            String currentLine;
            int count = 0;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(COURSE_NAME_1)) {
                    String[] splitLine = currentLine.split(",");
                    course1Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                } else if (currentLine.contains(COURSE_NAME_2)) {
                    String[] splitLine = currentLine.split(",");
                    course2Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                } else if (currentLine.contains(COURSE_NAME_3)) {
                    String[] splitLine = currentLine.split(",");
                    course3Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                } else {
                    String invalidCourseName = currentLine.split(",")[2].split(" ")[0];
                    System.err.println("No such course name: " + invalidCourseName);
                }
                ++count;
            }



        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File read failed: " + filePath);
            System.out.println(e.getMessage());
        }
    }

}
