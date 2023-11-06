import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidSmithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("David Smith Dashboard");
	    	request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("davidSmithDashboard.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if(email.equals("davidsmith@gmail.com") && password.equals("david1234"))
	    	 {
	    		 System.out.println("Welcome David Smith! Redirecing to your dashboard");
	    		 request.getRequestDispatcher("davidSmithDashboard.jsp").forward(request, response);
	    	 }
	    	 
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginFailedStr","Login Failed: Wrong password or username.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String id = String.valueOf(request.getParameter("id"));
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String creditCardNumber = request.getParameter("creditCardNumber");
	   	    String phoneNumber = request.getParameter("phoneNumber");
	   	    String role = request.getParameter("role");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 	 
	   	 	String tree_amt = "0";
	   	 	String tree_size = "0";
	   	 	String tree_height = "0";
	   	 	String tree_distance = "0";
	   	 	String tree_location = "NULL";
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");
			Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
			Pattern phonePattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
			Pattern creditCardPattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
	 
			if (!namePattern.matcher(firstName).matches() || !namePattern.matcher(lastName).matches()) {
				request.setAttribute("errorOne", "Names can only contain letters.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!emailPattern.matcher(email).matches()) {
				request.setAttribute("errorOne", "Invalid email format.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!phonePattern.matcher(phoneNumber).matches()) {
				request.setAttribute("errorOne", "Phone number format should be XXX-XXX-XXXX.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	 
			if (!creditCardPattern.matcher(creditCardNumber).matches()) {
				request.setAttribute("errorOne", "Credit card format should be XXXX-XXXX-XXXX-XXXX.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	   	 	
	   	 	if (password.equals(confirm)) {
		   	 	if (userDAO.checkPhoneNumber(phoneNumber)) {
			   	     request.setAttribute("errorOne", "Phone number already in use.");
			   	     request.getRequestDispatcher("register.jsp").forward(request, response);
			   	     return;
			   	 }
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(id, email,firstName, lastName, password, creditCardNumber, phoneNumber, role, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, tree_amt, tree_size, tree_height, tree_distance, tree_location);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


