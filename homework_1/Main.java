/*
 * File: Main.java
 * Path: (Main)
 * Build: javac Main.java
 * Date: 2025-12-24
 * Author: Aleksandr Karpenko Ivanovich
 * 
 * Thesis: главный class Main предназначен для демонстрации работы
 * модулей: 
 * ru.astondevs.learn.etc.Student
 * ru.astondevs.learn.etc.ConsoleColor
 */

import ru.astondevs.learn.etc.Student;

public class Main {
    public static void main(String[] args){
		String[] _newbio2 = {"Name2", "Surname2", "Patronymic2"};
		String[] _newbio3 = {"Name3", "Surname3"};
		String[] _newbio4 = {"Name4"};
		String[] _empty = {};
		
        Student studentMain = new Student();
        Student student1 = 
			new Student("Name1", "Surname1", "Patronymic1");
        Student student2 = new Student(_newbio2);
        Student student3 = new Student(_newbio3);
        Student student4 = new Student(_newbio4);
        Student student5 = new Student(_empty); 
        
        System.out.println(studentMain);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        System.out.println(student4);
        System.out.println(student5);
        
        /* Block for testing failed calls
        String[] _test_to_fail = {"Field1", "Field2", "Field3", "Field4"};
        String[] _test_to_fail = {"Field1", "Field2", "Field3", "Field4"};
        Student _fail1 = new Student(_test_to_fail);
        Student _fail2 = new Student("Field1");
        Student _fail3 = 
			new Student("Field1", "Field2", "Field3", "Field4");
		*/
		
		System.out.println(studentMain.exp.getRandomColoredString());
    }
}
