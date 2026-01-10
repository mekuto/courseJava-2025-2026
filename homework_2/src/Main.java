/*
 * File: Main.java
 * Path: ./
 * Build: javac ./Main.java
 * Date: 2026-01-02
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Main.java
 */

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import etc.BookStudent;
import etc.Student;

import java.util.Random;

public class Main {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = getListOfStudentsFromFile();
        } catch (IOException e) {
            System.out.println("Trouble with open file of students: " + e);
        }

        for (Student s: studentList) {
            try {
                getListOfBooksFromFile().forEach(s::addBook);
            } catch (IOException e) {
                System.out.println("Trouble with open file of books: " + e);
            }
        }

//        for (Student s : studentList) {
//            System.out.println(s.getName() + ";" +
//                    s.getLastname() + ";" +
//                    s.getEducationInstitute() + ";" +
//                    s.getFaculty() + ";"+
//                    s.getYearAtUniversity());
//        }

        studentList.forEach(System.out::println);
    }

    private static List<Student> getListOfStudentsFromFile() throws IOException {
        final String fileOfStudents = "../data/Students_WIP.csv";
        final Path pathFileStudentsPath = Paths.get(fileOfStudents).toAbsolutePath().normalize();

        List<Student> listOfStudent;

        Function<String, Student> lambda = s -> {
            if (s.isEmpty()) return (Student) null;
            String[] _s = s.split(";");
            if (_s.length != 5) return (Student) null;
            return new Student(_s[0], _s[1], _s[2], _s[3], Integer.parseInt(_s[4]));
        };

        try (Stream<String> fileStream = Files.lines(pathFileStudentsPath)) {
            listOfStudent = fileStream.map(lambda).collect(Collectors.toList());
        }
        return listOfStudent;
    }

    private static List<BookStudent> getListOfBooksFromFile () throws IOException {
        final String fileOfBooks = "../data/StudentBooks_WIP.csv";
        final Path pathFileOfBooks = Paths.get(fileOfBooks).toAbsolutePath().normalize();

        List<BookStudent> listOfBooks;

        Function<String, BookStudent> lambda = s -> {
            if (s.isEmpty()) return (BookStudent) null;
            String[] _s = s.split(";");
            if (_s.length != 6) return (BookStudent) null;
            return new BookStudent(_s[0], _s[1], _s[2], _s[3], Integer.parseInt(_s[4]), Integer.parseInt(_s[5]));
//            Random randomGenerator = new Random();
//            return new BookStudent(_s[0], _s[1], _s[2], _s[3],
//                    randomGenerator.nextInt(52) + 1985,
//                    randomGenerator.nextInt(101) + 450);
        };

        try (Stream<String> fileStream = Files.lines(pathFileOfBooks)) {
            listOfBooks = fileStream.map(lambda).collect(Collectors.toList());
        }
        return listOfBooks;
    }
}