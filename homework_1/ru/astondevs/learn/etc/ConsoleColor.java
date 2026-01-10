/*
 * File: ConsoleColor.java
 * Path: (Main)/ru/astondevs/ru/etc/
 * Build: javac (Main)/Main.java
 * Date: 2025-12-24
 * Author: Aleksandr Karpenko Ivanovich
 * 
 * Thesis: Модуль содержит описание изменяемого class ConsoleColor
 * (спецификатор public ).
 */

package ru.astondevs.learn.etc;

import java.util.Random;

public class ConsoleColor {
	
	private static long seedForRandom = System.currentTimeMillis();
	private static Random randomGenerator = new Random(seedForRandom);
	
	public static enum ANSI_COLOR {
		RESET,
		BLACK,
		RED,
		GREEN,
		YELLOW,
		BLUE,
		PURPLE,
		CYAN,
		WHITE
	}

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";	

	public static final String getANSIColor(int numberOfColor) {		
		switch (numberOfColor) {
			case 1 : return ANSI_BLACK;
			case 2 : return ANSI_RED;
			case 3 : return ANSI_GREEN;
			case 4 : return ANSI_YELLOW;
			case 5 : return ANSI_BLUE;
			case 6 : return ANSI_PURPLE;
			case 7 : return ANSI_CYAN;
			case 8 : return ANSI_WHITE;
			
			default : return ANSI_RESET;
			}
	}

	public static final String getANSIColor(ANSI_COLOR color) {		
		switch (color) {
			case BLACK : return ANSI_BLACK;
			case RED : return ANSI_RED;
			case GREEN : return ANSI_GREEN;
			case YELLOW : return ANSI_YELLOW;
			case BLUE : return ANSI_BLUE;
			case PURPLE : return ANSI_PURPLE;
			case CYAN : return ANSI_CYAN;
			case WHITE : return ANSI_WHITE;
			
			default : return ANSI_RESET;
			}
	}

	public String getRandomColoredString(String... ss) {
		StringBuilder _build = new StringBuilder();
		
		int _textColor;
		for (String s : ss) {
			do {
				_textColor = randomGenerator.nextInt(8);
			} while (_textColor == 0);	
			
			_textColor += 1;
			
			_build.append(
				ConsoleColor.getANSIColor(_textColor) + s + 
				ConsoleColor.getANSIColor(ANSI_COLOR.RESET));		
		}
		
		return _build.toString();
	}
}
	
