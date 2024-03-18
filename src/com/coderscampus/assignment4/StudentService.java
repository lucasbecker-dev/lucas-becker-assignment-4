package com.coderscampus.assignment4;

import java.io.*;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

public class StudentService {

    public static void parseFile(String filePath) {

        // setup local variables
        final String DESTINATION_PATH = "src/com/coderscampus/assignment4/";
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
             BufferedWriter writer3 = new BufferedWriter(new FileWriter(FILE_NAME_3))) {

            // write file header to each file
            final String fileHeader = "Student ID,Student Name,Course,Grade\n";
            writer1.write(fileHeader);
            writer2.write(fileHeader);
            writer3.write(fileHeader);

            // read through csv file and parse into appropriate Student arrays
            String currentLine;
            int count = 0;
            reader.readLine(); // throw away the first line since it's always the file header, not a Student
            while ((currentLine = reader.readLine()) != null && count < STUDENT_ARRAY_SIZE) {
                try {
                    String[] splitLine = currentLine.split(",");
                    String courseName = splitLine[2].split(" ")[0];
                    switch (courseName) {
                        case COURSE_NAME_1:
                            course1Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                            break;
                        case COURSE_NAME_2:
                            course2Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                            break;
                        case COURSE_NAME_3:
                            course3Students[count] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                            break;
                        default:
                            System.err.println("No such course name: " + courseName);
                            break;
                    }
                } catch (PatternSyntaxException e) {
                    System.err.println("Invalid format for file: " + filePath);
                    System.err.println(e.getMessage());
                }
                ++count;
            }

            // sort the Student arrays using custom Comparator
            try {
                StudentComparator studentComparator = new StudentComparator();
                Arrays.sort(course1Students, studentComparator);
                Arrays.sort(course2Students, studentComparator);
                Arrays.sort(course3Students, studentComparator);
            } catch (ClassCastException e) {
                System.err.println("Array contains elements that are not mutually comparable using the specified comparator");
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Specified comparator violates the Comparator contract");
                System.err.println(e.getMessage());
            }

            // write the sorted arrays using the appropriate writers
            for (Student student : course1Students) {
                if (student != null) {
                    writer1.write(student.toString());
                }
            }
            for (Student student : course2Students) {
                if (student != null) {
                    writer2.write(student.toString());
                }
            }
            for (Student student : course3Students) {
                if (student != null) {
                    writer3.write(student.toString());
                }
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
