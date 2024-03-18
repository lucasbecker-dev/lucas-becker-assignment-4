package com.coderscampus.assignment4;

import java.io.*;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

public class StudentService {
    private static final String DESTINATION_PATH = "src/com/coderscampus/assignment4/";
    private static final String FILE_NAME_1 = DESTINATION_PATH + "course1.csv";
    private static final String FILE_NAME_2 = DESTINATION_PATH + "course2.csv";
    private static final String FILE_NAME_3 = DESTINATION_PATH + "course3.csv";
    private static final String COURSE_NAME_1 = "COMPSCI";
    private static final String COURSE_NAME_2 = "APMTH";
    private static final String COURSE_NAME_3 = "STAT";
    private static final int STUDENT_ARRAY_SIZE = 100;

    public static void parseFile(String filePath) {
        Student[] course1Students = new Student[STUDENT_ARRAY_SIZE];
        Student[] course2Students = new Student[STUDENT_ARRAY_SIZE];
        Student[] course3Students = new Student[STUDENT_ARRAY_SIZE];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(FILE_NAME_1));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(FILE_NAME_2));
             BufferedWriter writer3 = new BufferedWriter(new FileWriter(FILE_NAME_3))) {

            writeHeaders(writer1, writer2, writer3);
            readAndParseFile(reader, course1Students, course2Students, course3Students);
            sortStudents(course1Students, course2Students, course3Students);
            writeStudentsToFile(course1Students, writer1);
            writeStudentsToFile(course2Students, writer2);
            writeStudentsToFile(course3Students, writer3);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("File read failed: " + filePath);
            System.err.println(e.getMessage());
        }
    }

    private static void writeHeaders(BufferedWriter... writers) throws IOException {
        final String fileHeader = "Student ID,Student Name,Course,Grade\n";
        for (BufferedWriter writer : writers) {
            writer.write(fileHeader);
        }
    }

    private static void readAndParseFile(BufferedReader reader, Student[]... studentArrays) throws IOException {
        String currentLine;
        int count = 0;
        reader.readLine(); // throw away the first line since it's always the file header, not a Student
        while ((currentLine = reader.readLine()) != null && count < STUDENT_ARRAY_SIZE) {
            parseLine(currentLine, count, studentArrays);
            ++count;
        }
    }

    private static void parseLine(String line, int index, Student[]... studentArrays) {
        try {
            String[] splitLine = line.split(",");
            String courseName = splitLine[2].split(" ")[0];
            switch (courseName) {
                case COURSE_NAME_1:
                    studentArrays[0][index] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                    break;
                case COURSE_NAME_2:
                    studentArrays[1][index] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                    break;
                case COURSE_NAME_3:
                    studentArrays[2][index] = new Student(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                    break;
                default:
                    System.err.println("No such course name: " + courseName);
                    break;
            }
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid format for file: " + line);
            System.err.println(e.getMessage());
        }
    }

    private static void sortStudents(Student[]... studentArrays) {
        try {
            StudentComparator studentComparator = new StudentComparator();
            for (Student[] students : studentArrays) {
                Arrays.sort(students, studentComparator);
            }
        } catch (ClassCastException e) {
            System.err.println("Array contains elements that are not mutually comparable using the specified comparator");
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Specified comparator violates the Comparator contract");
            System.err.println(e.getMessage());
        }
    }

    private static void writeStudentsToFile(Student[] students, BufferedWriter writer) throws IOException {
        for (Student student : students) {
            if (student != null) {
                writer.write(student.toString());
            }
        }
    }
}
