import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User ORDER BY id";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String id = resultSet.getString("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditCardNumber = resultSet.getString("creditCardNumber");
            String phoneNumber = resultSet.getString("phoneNumber");
            String role = resultSet.getString("role");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 


             
            user users = new user(id, email,firstName, lastName, password, creditCardNumber,phoneNumber,role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }


	protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, creditCardNumber, phoneNumber, role,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getFirstName());
			preparedStatement.setString(4, users.getLastName());
			preparedStatement.setString(5, users.getPassword());
			preparedStatement.setString(6, users.getCreditCardNumber());
			preparedStatement.setString(7, users.getPhoneNumber());
			preparedStatement.setString(8, users.getRole());
			preparedStatement.setString(9, users.getAdress_street_num());		
			preparedStatement.setString(10, users.getAdress_street());		
			preparedStatement.setString(11, users.getAdress_city());		
			preparedStatement.setString(12, users.getAdress_state());		
			preparedStatement.setString(13, users.getAdress_zip_code());		
	
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?, creditCardNumber=?, phoneNumber=?, role=?, adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=? where email = ?";
        connect_func();
        
		preparedStatement.setString(2, users.getEmail());
		preparedStatement.setString(3, users.getFirstName());
		preparedStatement.setString(4, users.getLastName());
		preparedStatement.setString(5, users.getPassword());
		preparedStatement.setString(6, users.getCreditCardNumber());
		preparedStatement.setString(7, users.getPhoneNumber());
		preparedStatement.setString(8, users.getRole());
		preparedStatement.setString(9, users.getAdress_street_num());		
		preparedStatement.setString(10, users.getAdress_street());		
		preparedStatement.setString(11, users.getAdress_city());		
		preparedStatement.setString(12, users.getAdress_state());		
		preparedStatement.setString(13, users.getAdress_zip_code());			
		
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditCardNumber = resultSet.getString("creditCardNumber");
            String phoneNumber = resultSet.getString("phoneNumber");
            String role = resultSet.getString("role");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            user = new user(null, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User"; 
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " + 
					        	"id INT AUTO_INCREMENT UNIQUE, " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "creditCardNumber VARCHAR(20) NOT NULL, " +
					            "phoneNumber VARCHAR(20) NOT NULL, " +
					            "role VARCHAR(20) NOT NULL, " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(id, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code)"+
        			"values (id,'root', 'default', 'default','pass1234', '1234-3456-4567-5678','123-234-4567', 'root', '0000', 'Default', 'Default', '0', '00000'),"+
			    		 	"(id,'don@gmail.com', 'Don', 'Cummings','don123', '4972-9215-7861-2529','453-921-1233', 'Client', '1000', 'hi street', 'mama', 'MO', '12345'),"+
			    	 	 	"(id,'margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '0551-1797-8053-2646','281-966-2201', 'Client', '1234', 'ivan street', 'tata','CO','12561'),"+
			    		 	"(id,'jo@gmail.com', 'Jo', 'Brady','jo1234', '1558-7516-4276-7312','121-717-1085', 'Client', '3214','marko street', 'brat', 'DU', '54321'),"+
			    		 	"(id,'wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '7798-1489-2513-1024','859-738-0661', 'Client', '4500', 'frey street', 'sestra', 'MI', '48202'),"+
			    		 	"(id,'amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '3939-6766-6025-7909','157-417-5433', 'Client', '1245', 'm8s street', 'baka', 'IL', '48000'),"+
			    			"(id,'sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '2821-0436-9879-0137','814-722-1464', 'Client', '2468', 'yolos street', 'ides', 'CM', '24680'),"+
			    			"(id,'angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '8275-0022-7017-0368','767-805-6058', 'Client', '4680', 'egypt street', 'lolas', 'DT', '13579'),"+
			    			"(id,'rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '0860-6191-8026-5295','408-200-2252', 'Client', '1234', 'sign street', 'samo ne tu','MH', '09876'),"+
			    			"(id,'jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '7738-9880-5284-3227','606-059-0950', 'Client', '0981', 'snoop street', 'kojik', 'HW', '87654'),"+
			    			"(id, 'susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2038-7418-0996-1404','215-638-3029', 'Client', '1234', 'whatever street', 'detroit', 'MI', '48202');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
