package com.dore.postgesUsers.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dore.postgesUsers.beans.User;
import com.dore.postgesUsers.connection.ConnectionUtils;
import com.dore.postgesUsers.utilits.DBUtils;

@WebServlet(urlPatterns = {"/editUser"})
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = -9118731450135116047L;

	public EditUserServlet() {
        super();
    }

	//Show user edit page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		String error = null;
		User user = null;
		try {
			connection = ConnectionUtils.getConnection();
			int id = Integer.parseInt(request.getParameter("id"));
			user = DBUtils.findUser(connection, id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		//If no error and user not exists then redirect back to user list
		if (error == null && user == null) {
			response.sendRedirect(request.getContextPath() + "/usersList");
			return;
		}
		
		//Store info before forwarding to view
		request.setAttribute("errorString", error);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
        dispatcher.forward(request, response);
		
	}

	//This will be executed when data is modified and submit pressed
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		String error = null;
		User user = null;
		try {
			connection = ConnectionUtils.getConnection(); //getting info from creating page
			String name = (String) request.getParameter("name");
			String surname = (String) request.getParameter("surname");
			String login = (String) request.getParameter("login");
			String password = (String) request.getParameter("password");
			String birthdayStr = (String) request.getParameter("birthday");
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormatter.parse(birthdayStr);
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); 	// For security reasons instead of raw password storing it's SHA-512 digest
			byte[] passwordBytes = digest.digest(password.getBytes("UTF-8"));
			StringBuilder passwordHashBuilder = new StringBuilder();
			for (byte b : passwordBytes) {
				passwordHashBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			password = passwordHashBuilder.toString();
			
			user = new User(); 				// Creating and filling User object
			user.setName(name);
			user.setSurname(surname);
			user.setLogin(login);
			user.setPassword(password);
			user.setBirthday(birthday);
			
			DBUtils.updateUser(connection, user);
			
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | ParseException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		//Store info before forwarding to view
		request.setAttribute("errorString", error);
		request.setAttribute("user", user);
				
		//If error forwarding to edit page
		if (error != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
			dispatcher.forward(request, response);
		} else {			//If all ok, redirect to users list page
			response.sendRedirect(request.getContextPath() + "/usersList");
		}
	}

}
