/*
 * File: Student.java
 * Path: ./etc/
 *
 * Date: 2026-01-02
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Student class
 */

package etc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;


public class Student extends Human {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    private String institute;
    private String faculty;
    private int yearAtUniversity;
    private final ArrayList<AcademicReport> reports;
    private final ArrayList<BookStudent> bookshelf;

    public Student(String name, String lastname) {
        super(name, lastname);
        reports = new ArrayList<>();
        bookshelf = new ArrayList<>();
    }

    public Student(String name, String lastname, String institute, String faculty, int year) {
        this(name, lastname);
        setEducationInstitute(institute);
        setFaculty(faculty);
        setYearAtUniversity(year);
    }

    public void setEducationInstitute(String institute) {
        this.institute = Optional.of(institute).get();
    }

    public void setFaculty(String faculty) {
        this.faculty = Optional.of(faculty).get();
    }

    public void setYearAtUniversity(int year) {
        this.yearAtUniversity = (year >= 1 && year <= 6) ? year : 1;
    }

    public String getEducationInstitute(){
        return this.institute;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public int getYearAtUniversity() {
        return this.yearAtUniversity;
    }

    public ArrayList<AcademicReport> getReportList() {
        return this.reports;
    }

    public ArrayList<BookStudent> getBookList() {
        return this.bookshelf;
    }

    public boolean addBook(BookStudent book) {
        return this.bookshelf.add(book);
    }


    public boolean deleteBook(BookStudent book) {
        return bookshelf.removeIf(book::equals);
    }

    public int getTotalBook() {
        return bookshelf.size();
    }

    @Override
    public String toString() {
        return bookshelf.stream()
                .sorted()
//                .sorted((b1, b2) -> b1.getTotalPages() - b2.getTotalPages())
//                .sorted(Comparator.comparingInt(BookStudent::getTotalPages))
                .distinct()
                .filter(b -> b.getYear() >= 2000)
                .limit(3)
                .map(b -> Integer.valueOf(b.getYear()).toString())
                .findAny().orElse("The book is missing");
    }
}
