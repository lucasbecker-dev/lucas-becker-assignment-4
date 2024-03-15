package com.coderscampus.assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class StudentService {

    public static void parseFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File read failed: " + filePath);
            System.out.println(e.getMessage());
        }
    }
}
