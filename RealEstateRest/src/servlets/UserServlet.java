package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.CommentPOJO;
import realEstateService.RealEstateType;
import realEstateService.UserType;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/getUser?userID="+userID);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		UserType userPage = mapper.readValue(in, UserType.class);
		conn.disconnect();
		request.setAttribute("userPage", userPage);
		URL url2 = new URL("http://localhost:8080/RealEstateRest/rest/services/getCommentsOn?userID="+userID);
		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.setRequestMethod("GET");
		InputStream in2 = conn2.getInputStream();
		ObjectMapper mapper2 = new ObjectMapper();
		List<CommentPOJO> comments = mapper2.readValue(in2, mapper.getTypeFactory().constructCollectionType(List.class, CommentPOJO.class));
		conn.disconnect();
		request.setAttribute("comments", comments);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../user.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
