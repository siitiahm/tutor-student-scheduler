package utilities;
/**
 * This program is used to test the accompanying document userStories.java
 */
    
// importing the required java SQL package:
import java.sql.*;
import java.util.Scanner;


/**
 * @author Hannah, Ariel, My, Romeo
 */
public class testUserStories {
    // These are global variables that all test methods should use
    static userStories testObj = new userStories();
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // for the options menu
        int choice;
        // to stop the loop
        boolean isRunning = true;
        
        String username = testObj.openConnection(); // opens the connection because why would we need the user to do this anyways.
        System.out.printf("\nSuccessfully logged in as: %s", username);

        while(isRunning){
            System.out.println();
            System.out.println("\n");
            optionsMenu(); // calls up the option menu display
            choice = getChoice(); // gets the user input.

            switch(choice){
            case 1:{
                testLogin();
                break;
            }
            case 2:{
                //testCreateAccount();
                break;
            }
            case 3:{
                testListAllStudyGroups();
                break;
            }
            case 4:{
                testlistCurrentGroups();
                break;          
            }
            case 5:{
                testCreateStudyGroup();
                break;
            }
            case 6:{
                testJoinStudyGroup();
                break;
            }
            case 7:{
                testUpdatePronouns();
                break;
            }
            case 8:{
                testUpdatePassword();
                break;
            }
            case 9:{
                testUpdateAvailability();
                break;
            }
            case 10:{
                testDeleteStudyGroup();
                break;
            }
            case 11:{
                testLeaveStudyGroup();
                break;
            }
            case 12:{
                testObj.closeConnection();
                isRunning = false;
                System.out.println("Alright, good night.");
                break;
            }
            }// switch case

        } // while loop

    } // end of Main

    static void optionsMenu(){
    	System.out.println("|=================================|");
        System.out.println("1 |  call login()");
		System.out.println("2 |  call createAccount()");
		System.out.println("3 |  call listAllStudyGroups()");
		System.out.println("4 |  call listCurrentGroups()");
		System.out.println("5 |  call createStudyGroup()");
		System.out.println("6 |  call joinStudyGroup()");
		System.out.println("7 |  call updatePronouns()");
		System.out.println("8 |  call updatePassword()");
		System.out.println("9 |  call updateAvailability()");
		System.out.println("10| call deleteStudyGroup()");
		System.out.println("11| call leaveStudyGroup()");
		System.out.println("12| call closeConnection(exit)");
		System.out.println("|=================================|");
    } // optionsMenu

    static int getChoice(){
        int choice = -1;
        while(choice < 1 || choice > 12){
            try{
                System.out.println("Please enter a number between 1 and 12: ");
                choice = Integer.parseInt(keyboard.nextLine());
                System.out.println();
            }
            catch(NumberFormatException e){
                System.out.println("That wasn't a number, silly.");
            }
        }
        return choice;
    }

    /**
    * @author Romeo Garcia
    * Co-Author Hannah Si'tia
    * 
    * Here is the test method for the login() method
    * 
    * DESCRIPTION: Prompts the user for their username and password,
    * then searches the database to see if they exist, if they do, they
    * are successfully 'logged in'. Will develop this further during HTML process.
    * 
    */
    static void testLogin() throws SQLException {
    	boolean success = false;
    	
    	// prompt for information needed for method
    	System.out.println("Please enter your username: ");
    	String username = keyboard.nextLine();
    	System.out.println("Please enter your password");
    	String password = keyboard.nextLine();
    	
    	// execute the update
    	System.out.println("\n Attempted Login: ");
    	System.out.println("|===================================================================|");
    	System.out.printf("%s %s-10 | %s %s\n", "USERNAME: ", username, "PASSWORD: ", "********");
    	System.out.println("|===================================================================|");
    	success = testObj.logIn(username, password);
    	
    	// determine the success (given there probably won't be one if no errors were thrown in userStories.java)
    	if(success) {
    		System.out.println("\n[[SUCCESSFUL OPERATION]]");
    	}
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    }

    /**
     * @author Romeo Garcia
     * Co-Author Hannah Si'itia
     * 
     * Here is the test method for the createAccount() method
     * 
     * DESCRIPTION: Prompts the user for their desired information upon
     * account creation, meaning username, first and last name, and password. Then
     * returns true if submission of account was successful.
    */
    /*
    static void testCreateAccount() throws SQLException {
    	boolean success = false;
    	
    	// prompt for information needed for method
    	System.out.println("Please enter your username: ");
    	String username = keyboard.nextLine();
    	System.out.println("Please enter your first name: ");
    	String fname = keyboard.nextLine();
    	System.out.println("Please enter your last name: ");
    	String lname = keyboard.nextLine();
    	System.out.println("Please enter your password");
    	String password = keyboard.nextLine();
    	
    	// execute the update
    	System.out.println("\n Attempted Account Creation: ");
    	System.out.println("|==============================================================================================|");
    	System.out.printf("%s %s | %s %s | %s %s | %s %s\n", "USERNAME: ", username, "FNAME: ", fname, "LNAME: ", lname, "PASSWORD: ", "********");
    	System.out.println("|==============================================================================================|");
    	success = testObj.createAccount(username, fname, lname, password);
    	
    	// determine the success (given there probably won't be one if no errors were thrown in userStories.java)
    	if(success) {
    		System.out.println("\n[[SUCCESSFUL OPERATION]]");
    	}
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    }
    */
    /**
    * @author Romeo Garcia
    * Co-Author Hannah Si'itia
    * 
    * Here is the test method for listAllStudyGroups() method
    * 
    * DESCRIPTION: This method will display all study groups that exist, 
    * requiring no further prompting. It will display all information of the groups
    * to the console.
    */
    static void testListAllStudyGroups() throws SQLException {
    	ResultSet rs;
    	
    	// print out all groups
    	System.out.println("\n All Existing StudyGroups: ");
    	System.out.println("|=======================================================================================|");
    	System.out.printf("%-13s | %-10s | %-15s | %-8s | %-5s | %s\n", "GROUP_ID", "COURSE", "CREATOR", "BUILDING", "ROOM", "VIRTUAL LINK");
    	System.out.println("|=======================================================================================|");
    	rs = testObj.listAllStudyGroups();
    	
    	// print out all information for groups
    	while(rs.next()) {
    		System.out.printf("%-13s | %-10s | %-15s | %-8s | %-5s | %s\n", rs.getString(1), rs.getString(2), 
    				rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    	}
    }

    /**
     * @author Romeo Garcia
     * Co-Author Hannah Si'itia
     * 
     * Here is the test method for listAllCurrentGroups() method
     * 
     * DESCRIPTION: Prompts the user for their username and finds what groups they
     * are currently in, then prints those groups information to the command prompt.
     */
    static void testlistCurrentGroups() throws SQLException {
    	ResultSet rs;
    	
    	// prompt the user for their information
    	System.out.println("Please enter your Username: ");
    	String username = keyboard.nextLine();
    	
    	// print out all information
    	System.out.println("\n All Current StudyGroups: ");
    	System.out.println("|=======================================================================================|");
    	System.out.printf("%-13s | %-10s | %-15s | %-8s | %-5s | %s\n", "GROUP_ID", "COURSE", "CREATOR", "BUILDING", "ROOM", "VIRTUAL LINK");
    	System.out.println("|=======================================================================================|");
    	rs = testObj.listCurrentStudyGroups(username);
    	
    	while(rs.next()) {
    		System.out.printf("%-13s | %-10s | %-15s | %-8s | %-5s | %s\n", rs.getString(1), rs.getString(2), 
    				rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    	}
    }

    /**
     * @author Romeo Garcia
     * 
     * Here is the test method for createStudyGroups()
     * 
     * DESCRIPTION: It asks questions for the user to fill out, which means using it can 
     * be tedious compared to just parsing info, but this was good practice 
     * for what worked and what didn't.
     * 
     * SNAPSHOT (SCREENSHOT): will be provided in the Sprint 4 document itself.
    */
    static void testCreateStudyGroup() throws SQLException {
    	int success = 0; // to determine if the study group was successfully pushed to the database.
    	int room = 0; // this can be null, so it is handled differently
    	
    	// prompt for information needed for method
    	System.out.println("Please enter the ID (number of length 8) of this study group: ");
    	String study_group_id = keyboard.nextLine();
    	System.out.println("Please enter the course this is for: ");
    	String course = keyboard.nextLine();
    	System.out.println("Who is making this group (username)? ");
    	String creator = keyboard.nextLine();
    	System.out.println("What building will this group meet? ");
    	String building = keyboard.nextLine();
    	System.out.println("And finally, what is the room number? ");
    	String hold = keyboard.nextLine();
    	if(!hold.equalsIgnoreCase("null")) {
    		room = Integer.parseInt(hold);
    	}
    	
    	// execute the update
    	System.out.println("\n Attempted Submission of Study Group: ");
    	System.out.println("|===================================================================|");
    	System.out.printf("%-15s | %-8s | %-8s | %-8s | %s\n", "study_group_id", "course", "creator", "building", "room");
    	System.out.println("|===================================================================|");
    	System.out.printf("%-15s | %-8s | %-8s | %-8s | %s\n", study_group_id, course, creator, building, room);
    	success = testObj.createStudyGroup(study_group_id, course, creator, building, room);
    	
    	// determine the success (given there probably won't be one if no errors were thrown in userStories.java)
    	if(success > 0) {
    		System.out.println("\n[[SUCCESSFUL OPERATION]]");
    	}
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    } // testCreateStudyGroup

    /**
     * @author Romeo Garcia
     * 
     * Here is the test method for joinStudyGroup()
     * 
     * DESCRIPTION: It will ask for the group_id that they will be joining (which is the name
     * of a study group, and for the user name of the student/tutor joining. This
     * was lightly used due to how simplistic the task was.
     * 
     * SNAPSHOT (SCREENSHOT): will be provided in the Sprint 4 document itself.
    */
    static void testJoinStudyGroup() throws SQLException {
    	int success = 0; // to determine if the join was successful or not.
    	
    	// prompt user for information needed for method.
    	System.out.println("What is the id of the group you'll be joining? ");
    	String group_id = keyboard.nextLine();
    	System.out.println("And what is your username? ");
    	String username = keyboard.nextLine();
    	
    	System.out.println("\n Joining Study Group: ");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", "GROUP_ID", "USERNAME");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", group_id, username);
    	success = testObj.joinStudyGroup(group_id, username);
    	
    	// determine the success (given there probably won't be one if no errors were thrown in userStories.java)
    	if(success > 0) {
    		System.out.println("\n[[SUCCESSFUL OPERATION]]");
    	}
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    }

    /**
     * @author Ariel Brice
     * You are able to give a username and new pronouns, then the sql will update the pronouns in the database. 
     * The test will return if it is sucessful or if it fails to execute the SQL commands
     * @throws SQLException
     */
    static void testUpdatePronouns() throws SQLException{
    	int success = 0;
    	String user = "arielb";
    	String pro = "She/Her";
    	System.out.println("What is your username?");
    	user = keyboard.nextLine();
    	System.out.println("What would you like your pronouns to be?");
    	pro = keyboard.nextLine();
    	
    	System.out.println("\n Updating Pronouns: ");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", "USERNAME", "PRONOUNS");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", user, pro);
    	success = testObj.updatePronouns(user, pro);
    	if(success > 0)
        {
           System.out.println("\n[[SUCCESSFUL OPERATION]]");
        }
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    	
    	
    }

    /**
     * @author Ariel Brice
     * You are able to give a username and new password, then the sql will update the pronouns in the database. 
     * The test will return if it is sucessful or if it fails to execute the SQL commands
     * @throws SQLException
     */
    
    static void testUpdatePassword() throws SQLException{
    	int success = 0;
    	String user = "arielb";
    	String pass = "newPassword";
    	
    	
    	System.out.println("What is your username?");
    	user = keyboard.nextLine();
    	System.out.println("What would you like your new password to be?");
    	pass = keyboard.nextLine();
    	
    	System.out.println("\n Updating Pronouns: ");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", "USERNAME", "PASSWORD");
    	System.out.println("|=====================================|");
    	System.out.printf("%-10s | %s\n", user, "*********");
    	
    	success = testObj.updatePassword(user, pass);
    	if(success > 0)
        {
           System.out.println("\n[[SUCCESSFUL OPERATION]]");
        }
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
    	
    	
    }
    /**
     * @author Ariel Brice
     * You are able to create a new avalibility set as a tutor. This test allows you to create a new avalibility for a specific day and time.
     * @throws SQLException
     */

     static void testUpdateAvailability() throws SQLException {
         int success = 0;
         int tid = 99999999;
         String ttutor = "arielb";
         String tday = "Monday";
         int ttime = 1230;
         
        System.out.println("What is a new time id?");
     	//tid = keyboard.nextInt();
     	keyboard.nextLine();
     	System.out.println("What is your username?");
     	//ttutor = keyboard.nextLine();
     	System.out.println("What day would you like to add?");
     	//tday = keyboard.nextLine();
     	System.out.println("What time would you like to add?");
     	//ttime = keyboard.nextInt();
     	keyboard.nextLine();
     	
     	System.out.println("\n Updating Avalibility: ");
     	System.out.println("|=====================================|");
     	System.out.printf("%-10s | %-8s | %-8s | %s\n", "TIME_ID", "TUTOR", "DAY", "TIME");
     	System.out.println("|=====================================|");
     	System.out.printf("%-10s | %-8s | %-8s | %s\n", tid, ttutor, tday, ttime);
     	
     	String stid = Integer.toString(tid);
         success = testObj.updateAvailability(tday, stid, ttutor);
         if(success > 0)
         {
            System.out.println("\n[[SUCCESSFUL OPERATION]]");
    	}
    	else {
    		System.out.println("\n[[OH NO]]");
    	}
     }

    /**
     * My's Story:
    * Delete Study_Group
    */
     static void testDeleteStudyGroup() throws SQLException {
    	 int success = 0;
    	 int study_group_id = 99999988;
    	 
    	 System.out.println("What is the studygroup id?");
    	 study_group_id = keyboard.nextInt();
    	 keyboard.nextLine();
    	 
    	 testObj.deleteStudyGroup(study_group_id);
    	 ResultSet rs = testObj.testDeletion();
    	 if(rs.next() == false) {
    		 success = testObj.recoveryDeletion();
    	 }
    	 
    	 if(success > 0) {
    		 System.out.println("\n[[SUCCESSFUL OPERATION]]");
    		 System.out.println("[[STUDY GROUP DELETED]]");
    		 System.out.println("\n[[TUPLE DELETED ADDED BACK INTO DATABASE]]");
    		 System.out.println("\n[[  :)  ]]");
    	 }
    	 else {
    		 System.out.println("\n[[OH NO]]");
    	 }
     }

    /**
     * My's Story:
     * Leave Study_Group
    */
     static void testLeaveStudyGroup() throws SQLException {
    	 int success = 0;
    	 int group_id = 00001111;
    	 String student = "ramen23";
    	 
    	 System.out.println("What is the group id?");
    	 group_id = keyboard.nextInt();
    	 keyboard.nextLine();
    	 System.out.println("what is your username?");
    	 student = keyboard.nextLine();
    	 
    	 testObj.leaveStudyGroup(group_id, student);
    	 ResultSet rs = testObj.testLeaving();
    	 if(rs.next() == false) {
    		 success = testObj.recoveryLeaving();
    	 }
    	 
    	 if(success > 0) {
    		 System.out.println("\n[[SUCCESSFUL OPERATION]]");
    		 System.out.println("[[" + student +" HAS LEFT THE STUDY GROUP]]");
    		 System.out.println("\n[[TUPLE DELETED ADDED BACK INTO DATABASE]]");
    		 System.out.println("\n[[  :)  ]]");
    	 }
    	 else {
    		 System.out.println("\n[[OH NO]]");
    	 }
     }
    
}
