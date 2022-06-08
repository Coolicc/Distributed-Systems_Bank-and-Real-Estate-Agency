package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import realEstateService.RealEstateType;

/**
 * Servlet implementation class SearchRealEstateNameServlet
 */
@WebServlet("/searchRealEstateNameServlet")
public class SearchRealEstateNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRealEstateNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = 1;
		if (request.getParameter("page") != null) {
			currPage = Integer.parseInt(request.getParameter("page"));
		}
		String query = "";
		if (request.getParameter("search") != null) {
			query = request.getParameter("search").trim();
		}
		URL url = new URL("http://localhost:8080/RealEstateRest/rest/services/searchRealEstateByName?query="+query+"&a=a");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		List<RealEstateType> realEstates = mapper.readValue(in, mapper.getTypeFactory().constructCollectionType(List.class, RealEstateType.class));
		conn.disconnect();
		List<RealEstateType> realEstatesPage = new ArrayList<>();
		for (int i = (currPage-1)*5; i < (currPage-1)*5+5 && i < realEstates.size(); i++) {
			realEstatesPage.add(realEstates.get(i));
		}
		int maxPage = (int)((realEstates.size()+4)/5);
		request.getSession().setAttribute("page", currPage);
		request.getSession().setAttribute("realEstatePage", realEstatesPage);
		request.getSession().setAttribute("maxPage", maxPage);
		request.getSession().setAttribute("prevPage", currPage-1);
		request.getSession().setAttribute("nextPage", currPage+1);
		request.getSession().setAttribute("search", query);
		request.getSession().removeAttribute("userRE");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../realEstates.jsp");
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
