package com.rob.movies;

import java.util.Calendar;

public class DataInputValidator {

	private static int columnCounterForYearValidation;

	/*
	 * This method decides what datatype each object is and directs it to the
	 * appropriate method
	 */

	public static boolean validateInputData(Object objectToValidate) {
		System.out.println("in data input validator validate data method");
		if (columnCounterForYearValidation == 4 && objectToValidate instanceof Integer) {
			Integer integer = (Integer) objectToValidate;
			if (validateYear(integer))
				return true;
		}

		else if (objectToValidate instanceof String) {
			System.out.println("in string");
			columnCounterForYearValidation++;
			String string = (String) objectToValidate;
			if (validateString(string))
				return true;

		} else if (objectToValidate instanceof Double) {
			Double doubleValue = (Double) objectToValidate;
			if (validateDouble(doubleValue))
				return true;

		} else if (objectToValidate instanceof Integer) {
			Integer integer = (Integer) objectToValidate;
			if (validateInt(integer))
				return true;
		}
		return false;
	}

	/*
	 * Method used to reset counter at end of each query cycle
	 */

	public static void setColumnCounter() {
		columnCounterForYearValidation = 0;
	}

	private static boolean validateString(String validateMe) {

		if (!validateMe.equals(null) && !validateMe.equals(""))
			return true;

		return false;
	}

	private static boolean validateDouble(Double validateMe) {

		if (validateMe < 0)
			return false;

		return true;
	}

	private static boolean validateInt(int validateMe) {

		if (validateMe < 0)
			return false;

		return true;
	}

	private static boolean validateYear(int year) {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (year < 1900 || year > currentYear)
			return false;

		return true;
	}
}
