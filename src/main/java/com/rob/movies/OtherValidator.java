package com.rob.movies;

import java.util.ArrayList;

public class OtherValidator {

	private static StringBuilder recordOfErroneousData;
	private static ArrayList<Integer> invalidColumns = new ArrayList<Integer>();

	public static StringBuilder ValidateData(Object[] inputFromUser) {

		System.out.println("in the first method");
		recordOfErroneousData = new StringBuilder();

		if (!validateTitle(inputFromUser[0])){
			recordOfErroneousData
					.append("title: " + inputFromUser[0] + " is empty or contains no alphanumeric characters.\n");
			invalidColumns.add(0);
		}
		if (!validateDescription(inputFromUser[1])){
			recordOfErroneousData.append(
					"description: " + inputFromUser[1] + " is too short or contains no alphanumeric characters.\n");
			invalidColumns.add(1);
		}
		if (!validateDirector(inputFromUser[2])){
			recordOfErroneousData
					.append("director: " + inputFromUser[2] + " is empty or contains no alphanumeric characters.\n");
			invalidColumns.add(2);
		}
		if (!validateCountry(inputFromUser[3])){
			recordOfErroneousData
					.append("country: " + inputFromUser[3] + " is too short or contains no alphanumeric characters.\n");
			invalidColumns.add(3);
		}
		if (!validateYearMade(inputFromUser[4])){
			recordOfErroneousData
					.append("yearMade: " + inputFromUser[4] + " is too short or contains no alphanumeric characters.\n");
			invalidColumns.add(4);
		}
		if (!validateBudget(inputFromUser[5])){
			recordOfErroneousData.append("Budget: " + inputFromUser[5] + " is empty or is out of range.\n");
			invalidColumns.add(5);
		}
		if (!validateRentalPrice(inputFromUser[6])){
			recordOfErroneousData.append("Rental Price: " + inputFromUser[6] + " is empty or out of range.\n");
			invalidColumns.add(6);
		}
		if (!validateOnLoan(inputFromUser[7])){
			recordOfErroneousData.append("On Loan: " + inputFromUser[7] + " is empty or an invalid value.\n");
			invalidColumns.add(7);
		}
		if (!validatePicturePath(inputFromUser[8])){
			recordOfErroneousData.append("Picture: " + inputFromUser[8] + " is empty or an invalid path or file doesn't exist.\n");
			invalidColumns.add(0);
		}
		return recordOfErroneousData;

	}

	private static boolean validateTitle(Object title) {

		System.out.println("this place now");
		String stringValueOfObject = "";

		if (title instanceof String)
			stringValueOfObject = (String) title;

		return stringValueOfObject.matches(".*[a-zA-Z1-9]+.*");

	}

	private static boolean validateDescription(Object description) {

		String stringValueOfObject = "";

		if (description instanceof String)
			stringValueOfObject = (String) description;

		return stringValueOfObject.matches(".*[a-zA-Z1-9]+.*") && stringValueOfObject.length() > 30;

	}

	private static boolean validateDirector(Object director) {

		String stringValueOfObject = "";

		if (director instanceof String)
			stringValueOfObject = (String) director;

		return stringValueOfObject.matches("^[a-zA-Z0-9-]*$");

	}

	private static boolean validateCountry(Object country) {

		String stringValueOfObject = "";

		if (country instanceof String)
			stringValueOfObject = (String) country;

		return stringValueOfObject.matches(".*[a-zA-Z1-9]+.*");

	}

	private static boolean validateYearMade(Object yearMade) {

		String stringValueOfObject = "";

		if (yearMade instanceof Integer)
			stringValueOfObject = String.valueOf(yearMade);

		return stringValueOfObject.matches("^\\d{0,4}");

	}

	private static boolean validateBudget(Object budget) {

		double doubleValueOfObject = 0.0;

		if (budget instanceof Double)
			doubleValueOfObject = (Double) budget;

		return String.valueOf(doubleValueOfObject).matches("^\\d{0,7}(\\.\\d{1,2})?$");

	}

	private static boolean validateRentalPrice(Object rentalPrice) {

		double doubleValueOfObject = 0.0;

		if (rentalPrice instanceof Double)
			doubleValueOfObject = (Double) rentalPrice;

		return String.valueOf(doubleValueOfObject).matches("^\\d{0,2}(\\.\\d{1,2})?$") && doubleValueOfObject > 0;

	}

	private static boolean validateOnLoan(Object onLoan) {

		String stringValueOfObject = "";

		if (onLoan instanceof String)
			stringValueOfObject = String.valueOf(onLoan);

		if (stringValueOfObject.equalsIgnoreCase("y") || stringValueOfObject.equalsIgnoreCase("n"))
			return true;

		return false;

	}
	
	private static boolean validatePicturePath(Object picture){
		
		String stringValueOfObject = "";
		
		if(picture instanceof String) stringValueOfObject = (String) picture;
		
		return stringValueOfObject.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
	}
	
	public static ArrayList<Integer> getInvalidColumns(){
		return invalidColumns;
	}

}
