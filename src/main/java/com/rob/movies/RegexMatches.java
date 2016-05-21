package com.rob.movies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

    	
      // String to be scanned to find the pattern.
    	String s = "C:\\Windows\\ThisThing\\jenkins.war";
      boolean atleastOneAlpha = s.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
      System.out.println(atleastOneAlpha);
   }
}