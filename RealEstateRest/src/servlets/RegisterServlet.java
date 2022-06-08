package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.UserType;

/**
 * Servlet implementation class RegisterServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../register.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/register");
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
		queryParam.append("&");
		queryParam.append("email=");
		queryParam.append(email);
		queryParam.append("&");
		queryParam.append("name=");
		queryParam.append(name);
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
			request.setAttribute("errorP", "Unable to register. Try again.");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../register.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		System.out.println(user.toString());
		if (user.getUsername().equals("admin")) {
			request.getSession().setAttribute("admin", "admin");
		}
		String uploadPath = "D:\\Programiranje(Java)\\Eclipse2019\\DS\\RealEstateRest\\WebContent\\img";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();
		for (Part part : request.getParts()) {
		    part.write(uploadPath + File.separator + username + ".jpg");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../index.jsp");
		requestDispatcher.forward(request, response);
	}

}
