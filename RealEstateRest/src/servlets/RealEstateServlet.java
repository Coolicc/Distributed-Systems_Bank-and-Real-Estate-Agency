package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.RealEstateType;
import realEstateService.UserType;

/**
 * Servlet implementation class RealEstateServlet
 */
@WebServlet("/realEstateServlet")
public class RealEstateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealEstateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int realEstateID = Integer.parseInt(request.getParameter("realEstateID"));
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/getRealEstate?realEstateID="+realEstateID);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		RealEstateType realEstates = null;
		try {
			realEstates = mapper.readValue(in, RealEstateType.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		conn.disconnect();
		int userID = realEstates.getSeller();
		URL url2 = new URL("http://localhost:8080/RealEstateRest/rest/services/getUser?userID="+userID);
		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.setRequestMethod("GET");
		InputStream in2 = conn2.getInputStream();
		ObjectMapper mapper2 = new ObjectMapper();
		UserType seller = mapper2.readValue(in2, UserType.class);
		conn2.disconnect();
		if (realEstates.isSold()) {
			int userID3 = realEstates.getBuyer();
			URL url3 = new URL("http://localhost:8080/RealEstateRest/rest/services/getUser?userID="+userID3);
			HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
			conn3.setRequestMethod("GET");
			InputStream in3 = conn3.getInputStream();
			ObjectMapper mapper3 = new ObjectMapper();
			UserType buyer = mapper3.readValue(in3, UserType.class);
			conn3.disconnect();
			request.setAttribute("buyer", buyer.getUsername());
		}
		System.out.println("SELLER: " +seller.getUsername() );
		request.setAttribute("seller", seller.getUsername());
		request.setAttribute("realEstate", realEstates);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../realEstate.jsp");
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
