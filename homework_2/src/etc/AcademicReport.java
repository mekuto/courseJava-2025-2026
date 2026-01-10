/*
 * File: AcademicReport.java
 * Path: ./etc/
 *
 * Date: 2026-01-02
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: AcademicReport class
 */


package etc;

public class AcademicReport {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    private class semestr {
        private int physics;
        private int math;
        private int etc;
    }

    protected semestr[] report;

    AcademicReport() {
        this.report = new semestr[2];
    }
}
