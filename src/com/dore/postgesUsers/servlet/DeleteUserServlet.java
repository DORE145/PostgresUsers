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

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3599740540984507789L;

	public DeleteUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		String error = null;
		try {
			connection = ConnectionUtils.getConnection();
			int id = Integer.parseInt(request.getParameter("id"));
			DBUtils.deleteUser(connection, id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
						
		//If error forwarding to error page
		if (error != null) {
			//Store info before forwarding to view
			request.setAttribute("errorString", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/deleteUserErrorPage.jsp");
			dispatcher.forward(request, response);
		} else {			//If all ok, redirect to users list page
			response.sendRedirect(request.getContextPath() + "/usersList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
