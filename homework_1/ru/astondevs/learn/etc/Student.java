/*
 * File: Student.java
 * Path: (Main)/ru/astondevs/ru/etc/
 * Build: javac (Main)/Main.java
 * Date: 2025-12-24
 * Author: Aleksandr Karpenko Ivanovich
 * 
 * Thesis: Модуль содержит описание иммутабельного класса Student
 * (спецификаторы public final) содержащий в себе изменяеммый 
 * класс поле cc изменяемого класса ConsoleColor (спецификатор public),
 * метод этого класса getRandomColoredString() можно переопределить,
 * это демонстрирует class Experiment (спецификатор public).
 */
 
package ru.astondevs.learn.etc;

// import java.lang.String by default

import java.util.Random;

public final class Student {
	static final String TARGET = "Homework exercise for stage 1";
	static final String MODULE_INFO =
		"This module not for production use. Only for studing purpese.";
	
	private final String[] anthroponym;

	private final String firstName;
	private final String lastName;
	private final String patronymic;
	
	private static final String defaultFirstName = "Aleksandr";
	private static final String defaultLastName = "Karpenko";
	private static final String defaultPatronymic = "Ivanovich";
	
	private ConsoleColor cc = new ConsoleColor();

	private static long seedForRandom = System.currentTimeMillis();
	private static Random randomGenerator = new Random(seedForRandom);

	static int objectCounter = 0;
	
	private boolean joke;
	
	static {
		System.out.println(MODULE_INFO);
	};

	public final String getFirstName() {
		return this.firstName;
	}

	public final String getLastName() {
		return this.lastName;
	}

	public final String getPatronymic() {
		return this.patronymic;
	}
	
	public String[] getAnthroponym() {
		return this.anthroponym;
	}

	public static final String getClassTarget() {
		return Student.TARGET;
	}
	
	public static final 
	String[] transformToArray(String s1, String s2,	String s3) {
			
		String[] array = new String[3];
		
		array[0] = s1;
		array[1] = s2;
		array[2] = s3;
		
		return array;
	}
	
	public class Experiment extends ConsoleColor {
		@Override
		public String getRandomColoredString(String... ss) {
			return
			ConsoleColor.getANSIColor(
				ConsoleColor.ANSI_COLOR.YELLOW) +
			"Hello" + ConsoleColor.getANSIColor(
				ConsoleColor.ANSI_COLOR.RESET) +
			" " + ConsoleColor.getANSIColor(
				ConsoleColor.ANSI_COLOR.GREEN) +
			"World!" + ConsoleColor.getANSIColor(
				ConsoleColor.ANSI_COLOR.RESET);
		}
	}
	
	public Experiment exp = new Experiment();
	
	@Override
	public String toString() {
		StringBuilder _message = new StringBuilder();
		
		int _textColor;
		
		String[] _tempString = new String[3];
		String _coloredString;
		
		if (this.firstName != "") {
			_tempString[0] = this.firstName;
			_tempString[1] = this.lastName;
			_tempString[2] = this.patronymic;
		} else {
			_tempString[0] = "?";
			_tempString[1] = "?";
			_tempString[2] = "?";
		}
		
		if (_tempString[2] != "") { 
			_coloredString = cc.getRandomColoredString(
			_tempString[0], " ", _tempString[1], " ", _tempString[2], " ");
		} else if (_tempString[1] != "") {
			_coloredString = cc.getRandomColoredString(
			_tempString[0], " ", _tempString[1], " ", _tempString[2], " ");
		} else if (_tempString[0] != "") {
			_coloredString = cc.getRandomColoredString(
			_tempString[0], " ");
		} else _coloredString = cc.getRandomColoredString("");
		
		_message.append(_coloredString);	
		_message.append("is good programmist?" +
			cc.getANSIColor(ConsoleColor.ANSI_COLOR.CYAN) + 
			" Is " + this.joke +
			cc.getANSIColor(ConsoleColor.ANSI_COLOR.RESET));
		
		return _message.toString();
	}
	
	public Student(String firstName, String lastName, String patronymic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		 
		this.anthroponym = Student.transformToArray(
			this.firstName,
			this.lastName,
			this.patronymic);
			
		this.joke = randomGenerator.nextBoolean();
			
		Student.objectCounter += 1;
	}

	public Student(String[] anthroponym) {
		if (anthroponym == null) {
			this.firstName = Student.defaultFirstName;
			this.lastName = Student.defaultLastName;
			this.patronymic = Student.defaultPatronymic;
		
			this.anthroponym = Student.transformToArray(
			Student.defaultFirstName,
			Student.defaultLastName,
			Student.defaultPatronymic);

		} else {
		
			String[] _temp = new String[3];
			int _size = anthroponym.length;
			int _k = 0;
			
			for (String s : anthroponym) {
					_temp[_k] = anthroponym[_k];
					_temp[_k] = anthroponym[_k];
					_k += 1;
			}
			
			for (; _k < 3; _k++) {
				_temp[ _k ] = "";
			}
			
			this.anthroponym = _temp;
			
			this.firstName = _temp[0];
			this.lastName = _temp[1];
			this.patronymic = _temp[2];
				
		}
		this.joke = randomGenerator.nextBoolean();
		Student.objectCounter += 1;
	}
	
	public Student() {
		this.firstName = Student.defaultFirstName;
		this.lastName = Student.defaultLastName;
		this.patronymic = Student.defaultPatronymic;
		
		this.anthroponym = Student.transformToArray(
			Student.defaultFirstName,
			Student.defaultLastName,
			Student.defaultPatronymic);

		this.joke = true;
		Student.objectCounter += 1;
	};
	
	/* Block for building module locally and 
	 * automatic check program syntax and semantics
	public static void main(String[] args) {
		System.out.println("Start...");
	}
	*/
}
