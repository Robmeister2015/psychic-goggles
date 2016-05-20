package com.rob.movies;

import java.util.Calendar;

public class DataInputValidator {

	private static int EMPTY_VARIABLE = 1;
	private static int INVALID_VARIABLE = 2;
	private static int VARIABLE_IS_ACCEPTED = 3;
	
	private static int columnCounterForYearValidation;

	/*
	 * This method decides what datatype each object is and directs it to the
	 * appropriate method
	 */

	public static int validateInputData(Object objectToValidate) {
		
		if (columnCounterForYearValidation == 4 && objectToValidate instanceof Integer) {
			Integer integer = (Integer) objectToValidate;
			return validateYear(integer);
		}

		else if (objectToValidate instanceof String) {
			columnCounterForYearValidation++;
			String string = (String) objectToValidate;
			return validateString(string);

		} else if (objectToValidate instanceof Double) {
			Double doubleValue = (Double) objectToValidate;
			return validateDouble(doubleValue);

		} else if (objectToValidate instanceof Integer) {
			Integer integer = (Integer) objectToValidate;
			return validateInt(integer);
		}
		return EMPTY_VARIABLE;
	}

	/*
	 * Method used to reset counter at end of each query cycle
	 */

	public static void setColumnCounter() {
		columnCounterForYearValidation = 0;
	}

	private static int validateString(String validateMe) {

		if (!validateMe.equals(null) && !validateMe.equals(""))
			return 3;

		return EMPTY_VARIABLE;
	}

	private static int validateDouble(Double validateMe) {

		if(validateMe <= 0) return EMPTY_VARIABLE;
		

		return VARIABLE_IS_ACCEPTED;
	}

	private static int validateInt(int validateMe) {

		if (validateMe <= 0) return EMPTY_VARIABLE;

		return VARIABLE_IS_ACCEPTED;
	}

	private static int validateYear(int year) {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		if (year < 1900 || year > currentYear) return INVALID_VARIABLE;
		
		return VARIABLE_IS_ACCEPTED;
	}
}
