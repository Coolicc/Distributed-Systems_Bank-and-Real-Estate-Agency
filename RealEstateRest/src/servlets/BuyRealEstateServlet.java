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
 * Servlet implementation class BuyRealEstateServlet
 */
@WebServlet("/buyRealEstateServlet")
public class BuyRealEstateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyRealEstateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int realEstateID = Integer.parseInt(request.getParameter("realEstateID"));
		request.setAttribute("realEstateID", realEstateID);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../buyRealEstate.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int realEstateID = Integer.parseInt(request.getParameter("realEstateID"));
		int fromBankAccount = Integer.parseInt(request.getParameter("account"));
		int buyerID = ((UserType) request.getSession().getAttribute("user")).getUserID();
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/buyRealEstate");
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
		queryParam.append("username=");
		queryParam.append(username);
		queryParam.append("&");
		queryParam.append("password=");
		queryParam.append(password);
		queryParam.append("&");
		queryParam.append("buyerID=");
		queryParam.append(buyerID);
		queryParam.append("&");
		queryParam.append("fromBankAccount=");
		queryParam.append(fromBankAccount);
		queryParam.append("&");
		queryParam.append("realEstateID=");
		queryParam.append(realEstateID);
		OutputStream output = conn.getOutputStream();
		output.write(queryParam.toString().getBytes());
		output.flush();
		conn.connect();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		boolean ok = mapper.readValue(in, Boolean.class);
		if (ok) {
			request.setAttribute("errorP", "Success!");
		} else {
			request.setAttribute("errorP", "Unable to buy real estate. Please check that"
					+ " you have enough money on that account");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../buyRealEstate.jsp");
		requestDispatcher.forward(request, response);
	}

}
