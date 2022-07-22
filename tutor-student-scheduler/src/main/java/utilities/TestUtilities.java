package utilities;
/**
 * This program is used to test the Utilities class
 */
 
// You need to import the java.sql package to use JDBC
import java.sql.*; 
 
import java.util.Scanner;

/**
 * @author Dr. Blaha
 * 
 */
public class TestUtilities {

	// Global variables
	static userStories testObj = new userStories(); 		// Utilities object for testing
	static Scanner keyboard = new Scanner(System.in); 	// standard input

	public static void main(String[] args) throws SQLException {
		testObj.openConnection();
		testObj.isTutor("bonjour83");
		
	}// main
		
}//MyUtilitiesTest	
