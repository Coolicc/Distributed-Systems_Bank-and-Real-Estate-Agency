package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.UserType;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/commentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int userID = Integer.parseInt(request.getParameter("userID"));
		int posterID = ((UserType) request.getSession().getAttribute("user")).getUserID();
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/postComment");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("charset", "utf-8");
		conn.setDoOutput(true);
		UserType user = (UserType) request.getSession().getAttribute("user");
		String cred = user.getUsername()+":"+user.getPassword();
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(cred.getBytes()));
		conn.setRequestProperty("Authorization", basicAuth);
		StringBuffer queryParam = new StringBuffer();
		queryParam.append("userID=");
		queryParam.append(userID);
		queryParam.append("&");
		queryParam.append("posterID=");
		queryParam.append(posterID);
		queryParam.append("&");
		queryParam.append("text=");
		queryParam.append(text);
		queryParam.append("&");
		queryParam.append("rating=");
		queryParam.append(rating);
		OutputStream output = conn.getOutputStream();
		output.write(queryParam.toString().getBytes());
		output.flush();
		conn.connect();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		boolean ok = mapper.readValue(in, Boolean.class);
		response.sendRedirect("/RealEstateRest/userServlet?userID="+userID);
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../index.jsp");
//		requestDispatcher.forward(request, response);
	}

}
