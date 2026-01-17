/*
 * File: BookStudent.java
 * Path: ./etc/
 *
 * Date: 2026-01-02
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: BookStudent class
 */

package etc;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class BookStudent implements Comparable<BookStudent> {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    private final String ISBN;
    private final String discipline;
    private final String author;
    private final String title;
    private final int year;
    private final int numberOfPages;
    private final String stringForHash;

    public BookStudent(String ISBN, String discipline, String author,
                       String title, int year, int pages) {

        boolean failFlag = Optional.of(ISBN).get().isEmpty() || Optional.of(author).get().isEmpty() ||
                Optional.of(discipline).get().isEmpty() || Optional.of(title).get().isEmpty();

        failFlag = failFlag || (!(year >= 0 && year <= LocalDate.now().getYear() + 1)) ||
                (pages < 1) || (pages > 10_000);

        if (failFlag) {
            this.ISBN = "";
            this.discipline = "";
            this.author = "";
            this.title = "";
            this.year = -1;
            this.numberOfPages = -1;
        } else {
            this.ISBN = ISBN;
            this.discipline = discipline;
            this.author = author;
            this.title = title;
            this.year = year;
            this.numberOfPages = pages;
        }
        stringForHash = ISBN + author + title + year + numberOfPages;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getDiscipline() {
        return this.discipline;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public int getTotalPages() {
        return this.numberOfPages;
    }

    @Override
    public String toString() {
        return "ISBN or other ID: " + this.getISBN() + "\n" +
                "Discipline: " + this.getDiscipline() + "\n" +
                "Author: " + this.getAuthor() + "\n" +
                "Title: " + this.getTitle() + "\n" +
                "Year: " + this.getYear() + "\n" +
                "Number of pages: " + this.getTotalPages();
    }

    @Override
    public int compareTo(BookStudent o) {
        return this.numberOfPages - o.numberOfPages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookStudent interest = (BookStudent) obj;
        return stringForHash.equalsIgnoreCase(interest.stringForHash);
    }

    @Override
    public int hashCode() {
        return stringForHash.hashCode();
    }
}
