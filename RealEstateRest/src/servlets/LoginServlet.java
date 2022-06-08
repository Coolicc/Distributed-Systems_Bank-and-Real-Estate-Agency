package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.UserType;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../login.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("charset", "utf-8");
		conn.setDoOutput(true);
		StringBuffer queryParam = new StringBuffer();
		queryParam.append("username=");
		queryParam.append(username);
		queryParam.append("&");
		queryParam.append("password=");
		queryParam.append(password);
		OutputStream output = conn.getOutputStream();
		output.write(queryParam.toString().getBytes());
		output.flush();
		conn.connect();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		UserType user = null;
		try {
			user = mapper.readValue(in, UserType.class);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		if (user == null) {
			request.setAttribute("errorP", "Username and password are not correct.");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../login.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		if (user.getUsername().equals("admin")) {
			request.getSession().setAttribute("admin", "admin");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../index.jsp");
		requestDispatcher.forward(request, response);
	}

}
