package utilities;
/**
* This program is core for the methods to be used for 
* adding or manipulating data in our personal database.
*/

// import the required SQL package
import java.sql.*;
import java.util.Random;


/**
 * @author Hannah, Ariel, My, Romeo
 */
public class userStories {
    // connection object 
    private Connection conn = null;
    private String username = null;
    private boolean tflag = false;

    /**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}
	
	/**
	 * @return the current user that is logged in
	 */
	public String getUser() {
		return username;
	}
	
	/**
	 * This method sets the username after a successful login
	 */
	public void setUser(String user) {
		username = user;
	}
	
	/**
	 * @return value of tflag
	 */
	public boolean getTFlag() {
		return tflag;
	}
	
	/**
	 * This method sets the tflag after a successful createAcc
	 */
	public void setTFlag(boolean tf) {
		tflag = tf;
	}

    /**
	 * Open a MariaDB DB connection where user name and password are predefined
	 * (hardwired).
	 */
	public String openConnection() {

		// Connect to the database using your team user name and team password	
		String url = "jdbc:mariadb://localhost:2000/hammr367_2022";
		//String url = "jdbc:mariadb://mal.cs.plu.edu:3306/company367_2022";
		String user = "hammr367";
		String password = "hammr367";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("using url:"+url + " user:"+user + " password:*****");
			System.out.println("problem connecting to MariaDB: "+ e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}// openConnection

    /**
	 * Close the connection to the DB
	 */
	public void closeConnection() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			System.err.println("Failed to close database connection: " + e);
		}
	}// closeConnection

  /**
   * @author My Lam.
   * coauthor: Hannah Si'tia.
   * logIn() checks if user exists in the database and if their password matches.
   * @param username is the username of the individual.
   * @param password is the password the individual makes up for the account.
   * @return boolean true if SQL is successful, or false if SQL is unsuccessful.
   */
	public boolean logIn(String username, String password) {
		ResultSet rset = null;
		String sql = null;
		boolean success = false;
		try {
			sql = "SELECT * "
					+ "FROM STUDENT "
					+ "WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			if (rset.next() != false) {
				success = true;
				setUser(username);
			}
		} catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		return success;
	}
	
	/**
	 * @author Romeo Garcia
	 * isTutor() checks if the user is a tutor, and then updates the tFlag in this application
	 */
	public void isTutor(String username) {
		ResultSet rset = null;
		String sql = null;
		
		try {
			sql = "SELECT tflag "
					+ "FROM STUDENT "
					+ "WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, username);
			rset = pstmt.executeQuery();
			
			if (rset.next() != false) {
				if(rset.getString(1).equalsIgnoreCase("1")) {
					setTFlag(true);
				}
			}
		} catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
	}

    /**
     * @author My Lam.
     * coauthor: Hannah Si'tia.
     * createAccount() creates a new account for individual to join/create study groups.
     * @param username which is the username of the individual.
     * @param fname which is the first name of the individual
     * @param lname which is the second name of the individual
     * @param password which is the password the individual makes up for the account.
     * @return boolean true if the SQL is successful. Or false if it is unsuccessful.
     */
	public boolean createAccount(String username, String fname, String lname, String password, boolean tflag) {
		String sql = null;
		boolean success = false;
		try {
			sql = "INSERT into STUDENT(username, first_name, last_name, password, tflag) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, username);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, password);
			pstmt.setBoolean(5, tflag);
			pstmt.executeUpdate();
			success = true;
			setTFlag(tflag);
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		return success;
	}
    /**
     * @author My Lam.
     * coauthor: Hannah Si'tia.
     * listAllStudyGroups() lists all of study groups available for students to join.
     * @return result set of all study groups available to join. 
     */
	public ResultSet listAllStudyGroups(){
		ResultSet rset = null;
		String sql = null;
		try {
			sql = "select * " 
					+ "from STUDY_GROUP";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		return rset;
	}
	
	/**
	 * @author My Lam.
	 * Coauthor: Hannah Si'tia.
	 * listCurrentStudyGroups() lists all of the study groups the student is currently in.
	 * @param username is the user name of the individual. It is passed in from the system. 
	 * @return result set of the current study groups the user is in.
	 */
	public ResultSet listCurrentStudyGroups(String username){
		ResultSet rset = null;
		String sql = null;
		try {
			sql = "select * " 
					+ "from STUDY_GROUP, IS_IN "
					+ "where student = ? and group_id = study_group_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  username);
			rset = pstmt.executeQuery();
			
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		return rset;
	}
	
	/**
	 * @author Romeo Garcia
	 * getThisStudyGroup() gives the corresponding Study group's course 
	 */
	public ResultSet getCourse(String groupId) {
		ResultSet rset = null;
		String sql = null;
		try {
			sql = "select course " 
					+ "from STUDY_GROUP "
					+ "where study_group_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  groupId);
			rset = pstmt.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		return rset;
	}
	
    /**
    * @a)uthor Romeo Garcia
    * createStudyGroup(): Creates a study group with given parameters. This is really 
    * just inserting into our Study_group tuple in the HAMMR database. This is an INSERT method.
    * 
    * @param study_group_id which is the name of the study group that the user
    * gives as input.
    * @param course is the name of the course
    * @param creator is the username of the individual making the course
    * @param building is the name of the meeting place
    * @param room is the room number (related to the building)
    * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
    * SQL statements that return nothing. Or it returns the number of statements within the SQL.
    */
	public int createStudyGroup(String study_group_id, String course, String creator, String building, int room) {
		String sql = null;
		int numChanged = -1;
		
		try {
			sql = "INSERT INTO STUDY_GROUP(study_group_id, course, creator, building, room) " +
				  "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, study_group_id);
			pstmt.setString(2, course);
			pstmt.setString(3, creator);
			pstmt.setString(4, building);
			pstmt.setInt(5, room);
			numChanged = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		
		return numChanged;
	}
	
	/**
	 * Overloaded method of the same type, just inputs an additional virtual_link (zoom link)
	 * @param study_group_id
	 * @param course
	 * @param creator
	 * @param building
	 * @param room
	 * @param virtual_link
	 * @return
	 */
	public int createStudyGroup(String study_group_id, String course, String creator, String building, int room, String virtual_link) {
		String sql = null;
		int numChanged = -1;
		
		try {
			sql = "INSERT INTO STUDY_GROUP(study_group_id, course, creator, building, room, virtual_link) " +
				  "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, study_group_id);
			pstmt.setString(2, course);
			pstmt.setString(3, creator);
			pstmt.setString(4, building);
			pstmt.setInt(5, room);
			pstmt.setString(6, virtual_link);
			numChanged = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		
		return numChanged;
	}

	/**
    * @author Romeo Garcia
    * joinStudyGroup: This is another INSERT method, where the user inputs themselves
    * into a new study group using their username and the group_id for the study group.
    * 
    * @param group_id is the name of the study group that the user
    * gives as input.
    * @param student is the username of the individual making the course
    * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
    * SQL statements that return nothing. Or it returns the number of statements within the SQL.
    */
	public int joinStudyGroup(String group_id, String student) {
		String sql = null;
		int numChanged = -1;
		
		try {
			sql = "INSERT INTO IS_IN(group_id, student) " +
				  "VALUES (?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, group_id);
			pstmt.setString(2, student);
			numChanged = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("SQL: " + sql);
			System.out.println("e.getMessage: " + e.getMessage());
		}
		
		return numChanged;
	}

    /**
    * Ariel's Story:
    * Update pronouns
    * @author Ariel Brice
    * updatePronouns(): This is an UPDATE method. It updates the pronouns of the user to the new chosen pronouns
    * @param un is the username of the user - This is gotten from the system
    * @param pronoun is the new pronouns of the user - This is gotten from the user via a drop down menu
    * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
    * SQL statements that return nothing. Or it returns the number of statements within the SQL.
    */
    public int updatePronouns(String un, String pn) {
        String sql = null;
        int numChanged = -1;

        try{
            sql = "UPDATE STUDENT SET pronouns = ? WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setString(1, pn);
            pstmt.setString(2, un);
            numChanged = pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
        return numChanged;
    }
    
    public String getPronouns(String un)
    {
    	String sql = null;
    	ResultSet rs = null;
    	String pro = null;
    	try {
    		sql = "SELECT pronouns FROM STUDENT where username = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.clearParameters();
    		pstmt.setString(1, un);
    		rs = pstmt.executeQuery();
    		while(rs.next()) {
    		pro = rs.getString(1);
    		}
    	}
    	catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
    	return pro;
    }

    /**
    * Ariel's Story:
    * Update password
    * @author Ariel Brice
    * updatePassword(): This is an UPDATE method. It updates the password of the user to the new chosen password
    * @param un is the username of the user - This is gotten from the system
    * @param pw is the new password of the user - This is gotten from the user via a text field
    * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
    * SQL statements that return nothing. Or it returns the number of statements within the SQL.
    */
    public int updatePassword(String un, String pw){
        String sql = null;
        int numChanged = -1;
        try{
            sql = "UPDATE STUDENT SET password = ? , hashpw = sha2(?, 256) WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setString(1, pw);
            pstmt.setString(2, pw);
            pstmt.setString(3, un);
            numChanged = pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
        return numChanged;
    }
    
    /** 
     * Helper Method to check if the PW is correct
     */
    
    public ResultSet checkPassword(String un, String opw, String npw)
    {
    	String sql = null;
    	ResultSet rs = null;
    	boolean correct = false; 
    	
    	try {
    		sql = "SELECT * FROM STUDENT where hashpw = sha2(?, 256) and username = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.clearParameters();
    		pstmt.setString(1, opw);
    		pstmt.setString(2, un);
    		rs = pstmt.executeQuery();
    		
    	}
    	catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
    	return rs;
    }

    /**
    * Ariel's Story:
    * Update avalibility
    * @author Ariel Brice
    * updateAvalibility(): This is an UPDATE method. It updates the avalibility of the user to the new chosen avalibility
    * @param id this is a randomly generated number for a timeslot
    * @param day this is the day of the week - this will be given from the user via a drop down menu
    * @param time this is the time of the day - this will be given from the user via a drop down menu
    * @param tutor this is the username of the tutor - this will be automatically given by the system
    * 
    * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
    * SQL statements that return nothing. Or it returns the number of statements within the SQL.
    */
    public int updateAvailability(String day, String time, String tutor){
        String sql = null;
        int numChanged = -1;
        try{
        	int id = getRandomTimeID();
            timeslotHelper(id, day, time);
            sql = "INSERT INTO AVAILABLE_AT VALUES(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setString(1, tutor);
            pstmt.setInt(2, id);
            numChanged = pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
        return numChanged;
    }
    
    
    public int updateAvailabilityTuples(String data[][]){
        String sql = null;
        int numChanged = -1;
        int success = 0;
        for(int i = 0; i < data.length; i++) {
        	String day = data[i][0];
        	String time = data[i][1];
        	String tutor = data[i][2];
        
        	try{
        	
        		int id = getRandomTimeID();
        		timeslotHelper(id, day, time);
        		sql = "INSERT INTO AVAILABLE_AT VALUES(?, ?)";
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		pstmt.clearParameters();
            	pstmt.setString(1, tutor);
            	pstmt.setInt(2, id);
            	numChanged = pstmt.executeUpdate();
            	success++;
            
        	}
        	catch(SQLException e){
        		System.out.println("SQL: " + sql);
        		System.out.println("e.getMessage: " + e.getMessage());
        	}
        }
        return success;
    }
    
    //get the tutors time availabilities?
    
    public ResultSet getTimeSlotID(String tutor)
    {
    	String sql = null;
    	ResultSet rs = null;
    	try {
    		sql = "";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    	}
    	catch(SQLException e){
    		System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
    	return rs;
    }
    
    public int getRandomTimeID() {
    	String sql = null;
    	ResultSet rs = null;
    	int result = -1;
    	Random rand = new Random();
    	try {
    		int number = rand.nextInt(99999999);
    		int num = Integer.parseInt(String.format("%08d", number));
    		sql = "SELECT timeslot_id from TIMESLOT WHERE timeslot_id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.clearParameters();
    		pstmt.setInt(1, num);
    		rs = pstmt.executeQuery();
    		if(rs.next())
    		{
    			getRandomTimeID();
    		}
    		result = num;
    	}
    	catch(SQLException e) {
    		System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
    	}
    	return result;
    }

    /**
     * This is a helper method to create the timeslots, to be able to update a users unavailable times
     * @param id a randomly generated number for a timeslot
     * @param day the day of the week
     * @param time the time of the day
     * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
     * SQL statements that return nothing. Or it returns the number of statements within the SQL.
     */
    private int timeslotHelper(int id, String day, String time) {
        String sql = null;
        int numChanged = -1;
    
        try{
            sql = "INSERT INTO TIMESLOT VALUES(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            pstmt.setString(3, time);
            numChanged = pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("SQL: " + sql);
            System.out.println("e.getMessage: " + e.getMessage());
        }
        
        return numChanged;
    }

    /**
     * @author My Lam.
     * deleteStudyGroup() deletes study groups that are no longer needed.
     * @param group_id is a randomly generated number for each group.
     * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
     * SQL statements that return nothing. Or it returns the number of statements within the SQL.
     */
    public int deleteStudyGroup(int group_id) {
    	String sql = null;
    	int numChanged = -1;
    	try {
    		sql = "delete from STUDY_GROUP where study_group_id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.clearParameters();
    		pstmt.setInt(1, group_id);
    		numChanged = pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return numChanged;
    }
    
    /**
     * @author My Lam
     * leaveStudyGroup() leaves study groups that are no longer needed.
     * @param group_id is a randomly generated number for each group.
     * @param username is the username of the individual.
     * @return the number of successful updates, essentially. -1 means that no changes were made successfully. 0 for'
     * SQL statements that return nothing. Or it returns the number of statements within the SQL.
     */
    public int leaveStudyGroup(int group_id, String username) {
    	String sql = null;
    	int numChanged = -1;
    	try {
    		sql = "delete from IS_IN where group_id = ? and student = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.clearParameters();
    		pstmt.setInt(1, group_id);
    		pstmt.setString(2, username);
    		numChanged = pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return numChanged;
    }
    
    /**
     * Helper method for testing purposes for leaveStudyGroup
     */
    public ResultSet testLeaving() {
    	ResultSet rs = null;
    	String sql = null;
    	
    	try {
    		sql = "SELECT * " +
   				 "FROM IS_IN " +
   				 "WHERE group_id = 00001111 and student = 'ramen23'";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    	}
    	catch(SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return rs;
    }
    
    /**
     * An additional Helper method for leaveStudyGroup
     */
    public int recoveryLeaving() {
    	String sql = null;
    	int numChanged = -1;
    	
    	try {
    		sql = "INSERT INTO IS_IN " +
   				 "VALUES ('00001111', 'ramen23')";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		numChanged = pstmt.executeUpdate();
    	}
    	catch(SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return numChanged;
    }
    
    /**
     * Helper method for testing purposes for deleteStudyGroup
     */
    public ResultSet testDeletion() {
    	ResultSet rs = null;
    	String sql = null;
    	
    	try {
    		sql = "SELECT * " +
   				 "FROM STUDY_GROUP " +
   				 "WHERE study_group_id = 99999988";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    	}
    	catch(SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return rs;
    }
    
    /**
     * An additional Helper method for deleteStudyGroup
     */
    public int recoveryDeletion() {
    	String sql = null;
    	int numChanged = -1;
    	
    	try {
    		sql = "INSERT INTO STUDY_GROUP " +
   				 "VALUES ('99999988', 'COMA302', 'spideysense', 'Ingram', '101', 'zoom.us/thisisfake')";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		numChanged = pstmt.executeUpdate();
    	}
    	catch(SQLException e) {
    		System.out.println("sql: " + sql);
    		System.out.println("e.getMessage:" + e.getMessage());
    	}
    	
    	return numChanged;
    }
}
