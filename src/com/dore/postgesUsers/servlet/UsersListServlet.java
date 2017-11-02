package com.dore.postgesUsers.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dore.postgesUsers.beans.User;
import com.dore.postgesUsers.connection.ConnectionUtils;
import com.dore.postgesUsers.utilits.DBUtils;

import java.util.List;

/**
 * Servlet implementation class UsersList
 */
@WebServlet("/usersList")
public class UsersListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7792314590345585901L;


	public UsersListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		String error = null;
		List<User> users = null; 
		
		try {
			connection = ConnectionUtils.getConnection();	//Receiving information from db 
			users = DBUtils.queryUsers(connection);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		//Store info before forwarding to view
		request.setAttribute("errorString", error);
		request.setAttribute("usersList", users);
		
		//Forward to user list view
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/usersListView.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
