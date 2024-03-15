package com.coderscampus.assignment4;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        if (student1 == null && student2 == null) {
            return 0;
        } else if (student1 == null) {
            return -1;
        } else if (student2 == null) {
            return 1;
        } else {
            return student1.compareTo(student2);
        }
    }
}
