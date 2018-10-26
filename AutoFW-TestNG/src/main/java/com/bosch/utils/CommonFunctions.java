package com.bosch.utils;

import java.util.Random;

public class CommonFunctions {
	 public static int randomInteger(int min, int max) {
	        Random randomno = new Random();
	        return randomno.nextInt((max - min) + 1) + min;
	    }
}
