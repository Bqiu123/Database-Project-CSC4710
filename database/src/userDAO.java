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
    

	protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
	
	
//---------------------------------------------------------------------------------------------------------------------------------------------
//CRUD methods for User
	
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());			preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getCreditCardNumber());
		preparedStatement.setString(6, users.getPhoneNumber());
		preparedStatement.setString(7, users.getRole());
		preparedStatement.setString(8, users.getAdress_street_num());		
		preparedStatement.setString(9, users.getAdress_street());		
		preparedStatement.setString(10, users.getAdress_city());		
		preparedStatement.setString(11, users.getAdress_state());		
		preparedStatement.setString(12, users.getAdress_zip_code());	
	
	
		preparedStatement.executeUpdate();
        preparedStatement.close();
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

    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String id = resultSet.getString("id");
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

            user = new user(id, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    
    public boolean update(user users) throws SQLException {
    	PreparedStatement preparedStatement = null;
        String sql = "update User set firstName=?, lastName =?,password = ?, creditCardNumber=?, phoneNumber=?, role=?, adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, where email = ?";
        connect_func();
        preparedStatement = connect.prepareStatement(sql);
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
    

    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(2, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     

    
 //--------------------------------------------------------------------------------------------------------------------------------------------
 //CRUD methods for Tree
    
    public void insertTree(Tree tree) throws SQLException{
    	PreparedStatement preparedStatement = null;
    	try {
        	connect_func("root", "pass1234");
        	String sql= "insert into Tree(size, height, location, proximityToHouse, clientID, quoteID) values (?, ?, ?, ?, ?, ?)";
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	preparedStatement.setString(1, tree.getSize());
        	preparedStatement.setString(2, tree.getHeight());
        	preparedStatement.setString(3, tree.getLocation());
        	preparedStatement.setString(4, tree.getProximityToHouse());
        	preparedStatement.setString(5, tree.getClientID());
        	preparedStatement.setString(6, tree.getQuoteID());
        	
        	preparedStatement.executeUpdate();
    	}catch (SQLException e) {
    		//Handle exception
    		throw e;
    	}finally {
    		if (preparedStatement != null) {
    			preparedStatement.close();
    		}
    	}
    }
    
    public List<Tree> listAllTrees() throws SQLException{
    	List<Tree> listTree = new ArrayList<Tree>();
    	String sql = "SELECT * FROM Tree ORDER BY treeID";
    	connect_func();
    	statement = (Statement)connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		String treeID = resultSet.getString("treeID");
    		String size = resultSet.getString("size");
    		String height = resultSet.getString("height");
    		String location = resultSet.getString("location");
    		String proximityToHouse = resultSet.getString("proximityToHouse");
    		String clientID = resultSet.getString("clientID");
    		String quoteID = resultSet.getString("quoteID");
    		
    		Tree tree = new Tree(treeID, size, height, location, proximityToHouse, clientID, quoteID);
    		listTree.add(tree);
    	}
    	resultSet.close();
    	disconnect();
    	return listTree;
    }
    
    public Tree getTree(String treeID)throws SQLException{
    	Tree tree = null;
    	String sql = "SELECT * FROM Tree WHERE treeID = ?";
    	
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, treeID);
    	
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		String size = resultSet.getString("size");
    		String height = resultSet.getString("height");
    		String location = resultSet.getString("location");
    		String proximityToHouse = resultSet.getString("proximityToHouse");
    		String clientID = resultSet.getString("clientID");
    		String quoteID = resultSet.getString("quoteID");    		
    		tree = new Tree(treeID, size, height, location, proximityToHouse, clientID, quoteID);
    		
    	}
    	resultSet.close();
    	statement.close();
    	
    	return tree;
    }
    
    public boolean updateTree(Tree tree) throws SQLException{
    	PreparedStatement preparedStatement = null;
    	String sql = "update Tree set size = ?, height = ?, location = ?, proximityToHouse = ?, clientID = ?, quoteID = ?,  where treeID = ?";
    	connect_func();
    	preparedStatement = connect.prepareStatement(sql);
    	preparedStatement.setString(1, tree.getSize());
    	preparedStatement.setString(2, tree.getHeight());
    	preparedStatement.setString(3, tree.getLocation());
    	preparedStatement.setString(4, tree.getProximityToHouse());
    	preparedStatement.setString(5, tree.getClientID());
    	preparedStatement.setString(6, tree.getQuoteID());
    	
    	boolean rowUpdated = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowUpdated;	
    }
    
    public boolean deleteTree(String treeID) throws SQLException{
    	String sql = "DELETE FROM Tree Where treeID = ?";
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, treeID);
    	
    	boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowDeleted;
    }
    
    
    
  //---------------------------------------------------------------------------------------------------------------------------------------------------------
  //CRUD methods for Quote;
    public void insertQuote(Quote quote) throws SQLException{
    	PreparedStatement preparedStatement = null;
    	try {
        	connect_func("root", "pass1234");
        	String sql= "insert into Quote(initialPrice, timeWindow, status, clientID, contractorID) values (?, ?, ?, ?, ?)";
        	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        	preparedStatement.setString(1, quote.getInitialPrice());
        	preparedStatement.setString(2, quote.getTimeWindow());
        	preparedStatement.setString(3, quote.getStatus());
        	preparedStatement.setString(4, quote.getClientID());
        	preparedStatement.setString(5, quote.getContractorID());
        	
        	preparedStatement.executeUpdate();
    	}catch (SQLException e) {
    		//Handle exception
    		throw e;
    	}finally {
    		if (preparedStatement != null) {
    			preparedStatement.close();
    		}
    	}
    }
    
    public List<Quote> listAllQuotes() throws SQLException{
    	List<Quote> listQuote = new ArrayList<Quote>();
    	String sql = "SELECT * FROM Quote ORDER BY quoteID";
    	connect_func();
    	statement = (Statement)connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		String quoteID = resultSet.getString("quoteID");
    		String initialPrice = resultSet.getString("initialPrice");
    		String timeWindow = resultSet.getString("timeWindow");
    		String status = resultSet.getString("status");
    		String clientID = resultSet.getString("clientID");
    		String contractorID = resultSet.getString("contractorID");
    		
    		Quote quote = new Quote(quoteID, initialPrice, timeWindow, status, clientID, contractorID);
    		listQuote.add(quote);
    	}
    	resultSet.close();
    	disconnect();
    	return listQuote;
    }
    
    public Quote getQuote(String quoteID)throws SQLException{
    	Quote quote = null;
    	String sql = "SELECT * FROM Quote WHERE quoteID = ?";
    	
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, quoteID);
    	
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		String initialPrice = resultSet.getString("intialPrice");
    		String timeWindow = resultSet.getString("timeWindow");
    		String status = resultSet.getString("status");
    		String clientID = resultSet.getString("clientID");
    		String contractorID = resultSet.getString("contractorID");
    		
    		quote = new Quote(quoteID, initialPrice, timeWindow, status, clientID, contractorID);
    		
    	}
    	resultSet.close();
    	statement.close();
    	
    	return quote;
    }
    
    public boolean updateQuote(Quote quote) throws SQLException{
    	PreparedStatement preparedStatement = null;
    	String sql = "update Quote set initialPrice = ?, timeWindow = ?,status = ?, clientID = ?,contractorID = ?, where quoteID = ?";
    	connect_func();
    	preparedStatement = connect.prepareStatement(sql);
    	preparedStatement.setString(1, quote.getInitialPrice());
    	preparedStatement.setString(2, quote.getTimeWindow());
    	preparedStatement.setString(3, quote.getStatus());
    	preparedStatement.setString(4, quote.getClientID());
    	preparedStatement.setString(5, quote.getContractorID());
    	
    	boolean rowUpdated = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowUpdated;	
    }
    
    public boolean deleteQuote(String quoteID) throws SQLException{
    	String sql = "DELETE FROM Quote Where quoteID = ?";
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, quoteID);
    	
    	boolean rowDeleted = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowDeleted;
    }
    
   //----------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
    // CRUD methods for QuoteMessages

    // Create (Insert)
    public void insertQuoteMessage(QuoteMessages quoteMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "INSERT INTO QuoteMessages (userID, quoteID, msgTime, price, scheduleStart, scheduleEnd, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, quoteMessage.getUserID());
            preparedStatement.setString(2, quoteMessage.getQuoteID());
            preparedStatement.setString(3, quoteMessage.getMsgTime());
            preparedStatement.setString(4, quoteMessage.getPrice());
            preparedStatement.setString(5, quoteMessage.getScheduleStart());
            preparedStatement.setString(6, quoteMessage.getScheduleEnd());
            preparedStatement.setString(7, quoteMessage.getNote());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<QuoteMessages> listAllQuoteMessages() throws SQLException {
        List<QuoteMessages> listQuoteMessage = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func();
            String sql = "SELECT * FROM QuoteMessages";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String quoteMessageID = resultSet.getString("quoteMessageID");
                String userID = resultSet.getString("userID");
                String quoteID = resultSet.getString("quoteID");
                String msgTime = resultSet.getString("msgTime");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String note = resultSet.getString("note");

                QuoteMessages quoteMessage = new QuoteMessages(quoteMessageID, userID, quoteID, msgTime, price, scheduleStart, scheduleEnd, note);
                listQuoteMessage.add(quoteMessage);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listQuoteMessage;
    }

    // Read (Get One)
    public QuoteMessages getQuoteMessage(String quoteMessageID) throws SQLException {
        QuoteMessages quoteMessage = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM QuoteMessages WHERE quoteMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, quoteMessageID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String quoteID = resultSet.getString("quoteID");
                String msgTime = resultSet.getString("msgTime");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String note = resultSet.getString("note");

                quoteMessage = new QuoteMessages(quoteMessageID, userID, quoteID, msgTime, price, scheduleStart, scheduleEnd, note);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return quoteMessage;
    }

    // Update
    public boolean updateQuoteMessage(QuoteMessages quoteMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "UPDATE QuoteMessages SET userID = ?, quoteID = ?, msgTime = ?, price = ?, scheduleStart = ?, scheduleEnd = ?, note = ? WHERE quoteMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, quoteMessage.getUserID());
            preparedStatement.setString(2, quoteMessage.getQuoteID());
            preparedStatement.setString(3, quoteMessage.getMsgTime());
            preparedStatement.setString(4, quoteMessage.getPrice());
            preparedStatement.setString(5, quoteMessage.getScheduleStart());
            preparedStatement.setString(6, quoteMessage.getScheduleEnd());
            preparedStatement.setString(7, quoteMessage.getNote());
            preparedStatement.setString(8, quoteMessage.getQuoteMessageID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Delete
    public boolean deleteQuoteMessage(String quoteMessageID) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "DELETE FROM QuoteMessages WHERE quoteMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, quoteMessageID);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    
 // CRUD methods for Bills

    // Create (Insert)
    public void insertBill(Bills bill) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "INSERT INTO Bills (orderID, price, discount, balance, status) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, bill.getOrderID());
            preparedStatement.setString(2, bill.getPrice());
            preparedStatement.setString(3, bill.getDiscount());
            preparedStatement.setString(4, bill.getBalance());
            preparedStatement.setString(5, bill.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<Bills> listAllBills() throws SQLException {
        List<Bills> listBills = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Bills";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String billID = resultSet.getString("billID");
                String orderID = resultSet.getString("orderID");
                String price = resultSet.getString("price");
                String discount = resultSet.getString("discount");
                String balance = resultSet.getString("balance");
                String status = resultSet.getString("status");

                Bills bill = new Bills(billID, orderID, price, discount, balance, status);
                listBills.add(bill);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listBills;
    }

    // Read (Get One)
    public Bills getBill(String billID) throws SQLException {
        Bills bill = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Bills WHERE billID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, billID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String orderID = resultSet.getString("orderID");
                String price = resultSet.getString("price");
                String discount = resultSet.getString("discount");
                String balance = resultSet.getString("balance");
                String status = resultSet.getString("status");

                bill = new Bills(billID, orderID, price, discount, balance, status);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return bill;
    }

    // Update
    public boolean updateBill(Bills bill) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func();
            String sql = "UPDATE Bills SET orderID = ?, price = ?, discount = ?, balance = ?, status = ? WHERE billID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, bill.getOrderID());
            preparedStatement.setString(2, bill.getPrice());
            preparedStatement.setString(3, bill.getDiscount());
            preparedStatement.setString(4, bill.getBalance());
            preparedStatement.setString(5, bill.getStatus());
            preparedStatement.setString(6, bill.getBillID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Delete
    public boolean deleteBill(String billID) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "DELETE FROM Bills WHERE billID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, billID);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    
 // CRUD methods for Order

    // Create (Insert)
    public void insertOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "INSERT INTO Order (quoteID, price, scheduleStart, scheduleEnd) VALUES (?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, order.getQuoteID());
            preparedStatement.setString(2, order.getPrice());
            preparedStatement.setString(3, order.getScheduleStart());
            preparedStatement.setString(4, order.getScheduleEnd());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<Order> listAllOrders() throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Orders";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String orderID = resultSet.getString("orderID");
                String quoteID = resultSet.getString("quoteID");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");

                Order order = new Order(orderID, quoteID, price, scheduleStart, scheduleEnd);
                listOrders.add(order);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listOrders;
    }

    // Read (Get One)
    public Order getOrder(String orderID) throws SQLException {
        Order order = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM Orders WHERE orderID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, orderID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String quoteID = resultSet.getString("quoteID");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");

                order = new Order(orderID, quoteID, price, scheduleStart, scheduleEnd);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return order;
    }

    // Update
    public boolean updateOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "UPDATE Orders SET quoteID = ?, price = ?, scheduleStart = ?, scheduleEnd = ? WHERE orderID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, order.getQuoteID());
            preparedStatement.setString(2, order.getPrice());
            preparedStatement.setString(3, order.getScheduleStart());
            preparedStatement.setString(4, order.getScheduleEnd());
            preparedStatement.setString(5, order.getOrderID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Delete
    public boolean deleteOrder(String orderID) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "DELETE FROM Orders WHERE orderID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, orderID);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
 
    
 // CRUD methods for BillMessages

    // Create (Insert)
    public void insertBillMessage(BillMessages billMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "INSERT INTO BillMessages (userID, billID, msgTime, price, scheduleStart, scheduleEnd, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, billMessage.getUserID());
            preparedStatement.setString(2, billMessage.getBillID());
            preparedStatement.setString(3, billMessage.getMsgTime());
            preparedStatement.setString(4, billMessage.getPrice());
            preparedStatement.setString(5, billMessage.getScheduleStart());
            preparedStatement.setString(6, billMessage.getScheduleEnd());
            preparedStatement.setString(7, billMessage.getNote());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Read (List All)
    public List<BillMessages> listAllBillMessages() throws SQLException {
        List<BillMessages> listBillMessages = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM BillMessages";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String billMessageID = resultSet.getString("billMessageID");
                String userID = resultSet.getString("userID");
                String billID = resultSet.getString("billID");
                String msgTime = resultSet.getString("msgTime");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String note = resultSet.getString("note");

                BillMessages billMessage = new BillMessages(billMessageID, userID, billID, msgTime, price, scheduleStart, scheduleEnd, note);
                listBillMessages.add(billMessage);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return listBillMessages;
    }

    // Read (Get One)
    public BillMessages getBillMessage(String billMessageID) throws SQLException {
        BillMessages billMessage = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connect_func(); 
            String sql = "SELECT * FROM BillMessages WHERE billMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, billMessageID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String billID = resultSet.getString("billID");
                String msgTime = resultSet.getString("msgTime");
                String price = resultSet.getString("price");
                String scheduleStart = resultSet.getString("scheduleStart");
                String scheduleEnd = resultSet.getString("scheduleEnd");
                String note = resultSet.getString("note");

                billMessage = new BillMessages(billMessageID, userID, billID, msgTime, price, scheduleStart, scheduleEnd, note);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return billMessage;
    }

    // Update
    public boolean updateBillMessage(BillMessages billMessage) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "UPDATE BillMessages SET userID = ?, billID = ?, msgTime = ?, price = ?, scheduleStart = ?, scheduleEnd = ?, note = ? WHERE billMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setString(1, billMessage.getUserID());
            preparedStatement.setString(2, billMessage.getBillID());
            preparedStatement.setString(3, billMessage.getMsgTime());
            preparedStatement.setString(4, billMessage.getPrice());
            preparedStatement.setString(5, billMessage.getScheduleStart());
            preparedStatement.setString(6, billMessage.getScheduleEnd());
            preparedStatement.setString(7, billMessage.getNote());
            preparedStatement.setString(8, billMessage.getBillMessageID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Delete
    public boolean deleteBillMessage(String billMessageID) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connect_func(); 
            String sql = "DELETE FROM BillMessages WHERE billMessageID = ?";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, billMessageID);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
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
    
    public boolean checkPhoneNumber(String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM User WHERE phoneNumber = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    
    public String getUserIDByEmail(String email) throws SQLException {
        String id = null;
        String sql = "SELECT id FROM User WHERE email = ?";

        try {
            connect_func();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return id;
    }

    public List<Tree> listTreesWithoutQuote() throws SQLException {
        List<Tree> listTree = new ArrayList<>();
        String sql = "SELECT * FROM Tree WHERE quoteID IS NULL OR quoteID = ''";
        connect_func(); 
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Tree tree = new Tree(
                    resultSet.getString("treeID"),
                    resultSet.getString("size"),
                    resultSet.getString("height"),
                    resultSet.getString("location"),
                    resultSet.getString("proximityToHouse"),
                    resultSet.getString("clientID"),
                    resultSet.getString("quoteID")
                );
                listTree.add(tree);
            }
        }
        return listTree;
    }

    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists Tree;",
					        "drop table if exists Quote;",
					        "drop table if exists User; ",
					        "drop table if exists QuoteMessages;",
					        "drop table if exists Orders;",
					        "drop table if exists Bills;",
					        "drop table if exists BillMessages;",
					        "CREATE TABLE if not exists User( " + 
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
					            "UNIQUE(email),"+
					            "PRIMARY KEY (id) "+
					        "); ",
					        "CREATE TABLE if not exists Quote(" +
					        	"quoteID INT AUTO_INCREMENT PRIMARY KEY, " +
					            "initialPrice DOUBLE, " +
					            "timeWindow VARCHAR(255), " +
					            "status VARCHAR(50), " +
					            "clientID INT, " +
					            "contractorID INT, "+
					            "FOREIGN KEY (contractorID) REFERENCES User(id),"+
					            "FOREIGN KEY (clientID) REFERENCES User(id) " +
					         ");",
					         "CREATE TABLE if not exists Tree( " +
					            "treeID INT AUTO_INCREMENT PRIMARY KEY, " +
					            "size DOUBLE, " +
					            "height DOUBLE, " +
					            "location VARCHAR(255), " +
					            "proximityToHouse DOUBLE, " +
					            "clientID INT, " +
					            "quoteID INT," +
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)," +
					            "FOREIGN KEY (clientID) REFERENCES User(id)" +
					         ");",
					         "CREATE TABLE if not exists QuoteMessages( "+
					          	"quoteMessageID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"userID INTEGER, "+
					          	"quoteID INTEGER, "+
					        	"msgTime DATETIME, "+
					          	"price DOUBLE, "+
					        	"scheduleStart DATETIME, "+
					          	"scheduleEnd DATETIME, "+
					        	"note VARCHAR(200), "+
					          	"FOREIGN KEY(userID) REFERENCES User(id),"+
					        	"FOREIGN KEY(quoteID) REFERENCES Quote(quoteID)"+
					         ");",
					         "CREATE TABLE if not exists Orders( "+
					            "orderID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"quoteID INTEGER, "+
					            "price DOUBLE, "+
					        	"scheduleStart DATETIME, "+
					            "scheduleEnd DATETIME, "+
					            "FOREIGN KEY(quoteID) REFERENCES Quote(quoteID)"+
					         ");",
					         "CREATE TABLE if not exists Bills( "+
					            "billID INT AUTO_INCREMENT PRIMARY KEY, "+
					        	"orderID INTEGER, "+
					            "price DOUBLE, "+
					        	"discount DOUBLE, "+
					            "balance DOUBLE, "+
					        	"status VARCHAR(20), "+
					            "FOREIGN KEY(orderID) REFERENCES Orders(orderID)"+
					         ");",
					         "CREATE TABLE if not exists BillMessages( "+
					            "billMessageID INT AUTO_INCREMENT PRIMARY KEY, "+
					            "userID INTEGER, "+
					            "billID INTEGER, "+
					            "msgTime DATETIME, "+
					            "price DOUBLE, "+
					            "scheduleStart DATETIME, "+
					            "scheduleEnd DATETIME, "+
					            "note VARCHAR(200), "+
					            "FOREIGN KEY(userID) REFERENCES User(id), "+
					            "FOREIGN KEY(billID) REFERENCES Bills(billID)"+
					         ");",
					            
					          	
					          
        					};
        String[] TUPLES = {("insert into User(id, email, firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code)"+
        			"values (id,'root', 'default', 'default','pass1234', '1234-3456-4567-5678','123-234-4567', 'root', '0000', 'Default', 'Default', '0', '00000'),"+
        					"(id,'davidsmith@gmail.com', 'David', 'Smith','david1234', '0000-0000-0000-0000','000-000-0000', 'David Smith', '0000', 'Default', 'Default', '0', '00000')," +
			    		 	"(id,'don@gmail.com', 'Don', 'Cummings','don1234', '4972-9215-7861-2529','453-921-1233', 'Client', '1000', 'hi street', 'mama', 'MO', '12345'),"+
			    	 	 	"(id,'margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '0551-1797-8053-2646','281-966-2201', 'Client', '1234', 'ivan street', 'tata','CO','12561'),"+
			    		 	"(id,'jo@gmail.com', 'Jo', 'Brady','jo1234', '1558-7516-4276-7312','121-717-1085', 'Client', '3214','marko street', 'brat', 'DU', '54321'),"+
			    		 	"(id,'wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '7798-1489-2513-1024','859-738-0661', 'Client', '4500', 'frey street', 'sestra', 'MI', '48202'),"+
			    		 	"(id,'amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '3939-6766-6025-7909','157-417-5433', 'Client', '1245', 'm8s street', 'baka', 'IL', '48000'),"+
			    			"(id,'sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '2821-0436-9879-0137','814-722-1464', 'Client', '2468', 'yolos street', 'ides', 'CM', '24680'),"+
			    			"(id,'angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '8275-0022-7017-0368','767-805-6058', 'Client', '4680', 'egypt street', 'lolas', 'DT', '13579'),"+
			    			"(id,'rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '0860-6191-8026-5295','408-200-2252', 'Client', '1234', 'sign street', 'samo ne tu','MH', '09876'),"+
			    			"(id,'jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '7738-9880-5284-3227','606-059-0950', 'Client', '0981', 'snoop street', 'kojik', 'HW', '87654'),"+
			    			"(id, 'susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2038-7418-0996-1404','215-638-3029', 'Client', '1234', 'whatever street', 'detroit', 'MI', '48202');"),
			        		("INSERT INTO Quote (initialPrice, timeWindow, status, clientID, contractorID) VALUES" +
			    		 	"(200.50, '2023-11-15 to 2023-11-16', 'Pending', 1, 1),"+
			    		 	"(150.75, '2023-11-20 to 2023-11-21', 'Confirmed', 2, 1),"+
			    		 	"(300.00, '2023-11-25 to 2023-11-26', 'Pending', 3, 1),"+
			    		 	"(175.25, '2023-12-01 to 2023-12-02', 'Confirmed', 4, 1),"+
			    		 	"(220.40, '2023-12-05 to 2023-12-06', 'Pending', 5, 1),"+
			    		 	"(180.90, '2023-12-10 to 2023-12-11', 'Confirmed', 6, 1),"+
			    		 	"(250.30, '2023-12-15 to 2023-12-16', 'Pending', 7, 1),"+
			    		 	"(160.00, '2023-12-20 to 2023-12-21', 'Confirmed', 8, 1),"+
			    		 	"(190.85, '2023-12-25 to 2023-12-26', 'Pending', 9, 1),"+
			    		 	"(210.60, '2023-12-30 to 2023-12-31', 'Confirmed', 10, 1);"),
        		 			("INSERT INTO Tree (size, height, location, proximityToHouse, clientID, quoteID) VALUES" +
        				    "(5.5, 20, '123 Oak Street', 10, 1, 1)," +
        				    "(3.2, 15, '456 Pine Lane', 15, 2, 2)," +
        				    "(6.7, 25, '789 Maple Ave', 5, 3, 3)," +
        				    "(4.5, 18, '321 Birch Road', 8, 4, NULL)," +
        				    "(2.8, 10, '654 Cedar Blvd', 12, 5, NULL)," +
        				    "(5.0, 22, '987 Elm Street', 7, 6, NULL)," +
        				    "(3.6, 16, '123 Spruce Lane', 11, 7, NULL)," +
        				    "(4.8, 20, '456 Fir Avenue', 9, 8, NULL)," +
        				    "(7.1, 28, '789 Redwood Drive', 4, 9, NULL)," +
        				    "(3.0, 14, '321 Willow Way', 13, 10, NULL);"),
        		 			("INSERT INTO QuoteMessages (userID, quoteID, msgTime, price, scheduleStart, scheduleEnd, note) VALUES" +
        		 			"(1, 1, '2023-11-12 10:00:00', 200.50, '2023-11-15 08:00:00', '2023-11-16 18:00:00', 'First message note')," +
        		 			"(2, 2, '2023-11-13 11:00:00', 150.75, '2023-11-20 09:00:00', '2023-11-21 17:00:00', 'Second message note')," +
        		 			"(3, 3, '2023-11-14 12:00:00', 300.00, '2023-11-25 10:00:00', '2023-11-26 16:00:00', 'Third message note')," +
        		 			"(4, 4, '2023-11-15 13:00:00', 175.25, '2023-12-01 08:00:00', '2023-12-02 18:00:00', 'Fourth message note')," +
        		 			"(5, 5, '2023-11-16 14:00:00', 220.40, '2023-12-05 09:00:00', '2023-12-06 17:00:00', 'Fifth message note')," +
        		 			"(6, 6, '2023-11-17 15:00:00', 180.90, '2023-12-10 10:00:00', '2023-12-11 16:00:00', 'Sixth message note')," +
        		 			"(7, 7, '2023-11-18 16:00:00', 250.30, '2023-12-15 08:00:00', '2023-12-16 18:00:00', 'Seventh message note')," +
        		 			"(8, 8, '2023-11-19 17:00:00', 160.00, '2023-12-20 09:00:00', '2023-12-21 17:00:00', 'Eighth message note')," +
        		 			"(9, 9, '2023-11-20 18:00:00', 190.85, '2023-12-25 10:00:00', '2023-12-26 16:00:00', 'Ninth message note')," +
        		 			"(10, 10, '2023-11-21 19:00:00', 210.60, '2023-12-30 08:00:00', '2023-12-31 18:00:00', 'Tenth message note');"),        		 			
        		 			("INSERT INTO Orders (quoteID, price, scheduleStart, scheduleEnd) VALUES" +
        		 			"(1, 200.50, '2023-11-15 08:00:00', '2023-11-16 18:00:00')," +
        		 			"(2, 150.75, '2023-11-20 09:00:00', '2023-11-21 17:00:00')," +
        		 			"(3, 300.00, '2023-11-25 10:00:00', '2023-11-26 16:00:00')," +
        		 			"(4, 175.25, '2023-12-01 08:00:00', '2023-12-02 18:00:00')," +
        		 			"(5, 220.40, '2023-12-05 09:00:00', '2023-12-06 17:00:00')," +
        		 			"(6, 180.90, '2023-12-10 10:00:00', '2023-12-11 16:00:00')," +
        		 			"(7, 250.30, '2023-12-15 08:00:00', '2023-12-16 18:00:00')," +
        		 			"(8, 160.00, '2023-12-20 09:00:00', '2023-12-21 17:00:00')," +
        		 			"(9, 190.85, '2023-12-25 10:00:00', '2023-12-26 16:00:00')," +
        		 			"(10, 210.60, '2023-12-30 08:00:00', '2023-12-31 18:00:00');"),       		 			
        		 			("INSERT INTO Bills (orderID, price, discount, balance, status) VALUES" +
        		 			"(1, 200.50, 10.00, 190.50, 'Unpaid')," +
        		 			"(2, 150.75, 5.00, 145.75, 'Unpaid')," +
        		 			"(3, 300.00, 15.00, 285.00, 'Unpaid')," +
        		 			"(4, 175.25, 8.50, 166.75, 'Paid')," +
        		 			"(5, 220.40, 11.00, 209.40, 'Unpaid')," +
        		 			"(6, 180.90, 9.00, 171.90, 'Paid')," +
        		 			"(7, 250.30, 12.50, 237.80, 'Unpaid')," +
        		 			"(8, 160.00, 8.00, 152.00, 'Unpaid')," +
        		 			"(9, 190.85, 9.50, 181.35, 'Paid')," +
        		 			"(10, 210.60, 10.50, 200.10, 'Unpaid');"),        		 			
        		 			("INSERT INTO BillMessages (userID, billID, msgTime, price, scheduleStart, scheduleEnd, note) VALUES" +
        		 			"(1, 1, '2023-11-12 10:30:00', 200.50, '2023-11-15 08:00:00', '2023-11-16 18:00:00', 'First bill message')," +
        		 			"(2, 2, '2023-11-13 11:30:00', 150.75, '2023-11-20 09:00:00', '2023-11-21 17:00:00', 'Second bill message')," +
        		 			"(3, 3, '2023-11-14 12:30:00', 300.00, '2023-11-25 10:00:00', '2023-11-26 16:00:00', 'Third bill message')," +
        		 			"(4, 4, '2023-11-15 13:30:00', 175.25, '2023-12-01 08:00:00', '2023-12-02 18:00:00', 'Fourth bill message')," +
        		 			"(5, 5, '2023-11-16 14:30:00', 220.40, '2023-12-05 09:00:00', '2023-12-06 17:00:00', 'Fifth bill message')," +
        		 			"(6, 6, '2023-11-17 15:30:00', 180.90, '2023-12-10 10:00:00', '2023-12-11 16:00:00', 'Sixth bill message')," +
        		 			"(7, 7, '2023-11-18 16:30:00', 250.30, '2023-12-15 08:00:00', '2023-12-16 18:00:00', 'Seventh bill message')," +
        		 			"(8, 8, '2023-11-19 17:30:00', 160.00, '2023-12-20 09:00:00', '2023-12-21 17:00:00', 'Eighth bill message')," +
        		 			"(9, 9, '2023-11-20 18:30:00', 190.85, '2023-12-25 10:00:00', '2023-12-26 16:00:00', 'Ninth bill message')," +
        		 			"(10, 10, '2023-11-21 19:30:00', 210.60, '2023-12-30 08:00:00', '2023-12-31 18:00:00', 'Tenth bill message');"),

			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
