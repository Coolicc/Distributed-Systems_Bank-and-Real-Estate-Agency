package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.RealEstateType;
import realEstateService.UserType;

/**
 * Servlet implementation class AddRealEstateServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/addRealEstateServlet")
public class AddRealEstateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRealEstateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../addRealEstate.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("NAME: " + request.getParameter("name"));
		System.out.println("PRICE: " + request.getParameter("price"));
		double price = Double.parseDouble(request.getParameter("price"));
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		int bankAccount = Integer.parseInt(request.getParameter("bankAccount"));
		String description = request.getParameter("description");
		int seller = ((UserType) request.getSession().getAttribute("user")).getUserID();
		System.out.println("SELLER: " + seller);
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/addRealEstate");
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
		queryParam.append("name=");
		queryParam.append(name);
		queryParam.append("&");
		queryParam.append("description=");
		queryParam.append(description);
		queryParam.append("&");
		queryParam.append("price=");
		queryParam.append(price);
		queryParam.append("&");
		queryParam.append("address=");
		queryParam.append(address);
		queryParam.append("&");
		queryParam.append("city=");
		queryParam.append(city);
		queryParam.append("&");
		queryParam.append("bankAccount=");
		queryParam.append(bankAccount);
		queryParam.append("&");
		queryParam.append("seller=");
		queryParam.append(seller);
		OutputStream output = conn.getOutputStream();
		output.write(queryParam.toString().getBytes());
		output.flush();
		conn.connect();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		RealEstateType ret = null;
		try {
			ret = mapper.readValue(in, RealEstateType.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (ret == null) {
			request.setAttribute("errorP", "Unable to add real estate. Try again.");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../addRealEstate.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		String uploadPath = "D:\\Programiranje(Java)\\Eclipse2019\\DS\\RealEstateRest\\WebContent\\img";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();
		for (Part part : request.getParts()) {
		    part.write(uploadPath + File.separator + ret.getRealEstateID() + ".jpg");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../index.jsp");
		requestDispatcher.forward(request, response);
	}

}
