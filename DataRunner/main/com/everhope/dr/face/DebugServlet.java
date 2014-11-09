package com.everhope.dr.face;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.everhope.dr.models.Page;
import com.google.gson.Gson;

/**
 * Debug servlet for the system details
 * Servlet implementation class DebugServlet
 */
@WebServlet("/Debug")
public class DebugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DebugServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ServletContext cont = request.getServletContext();
		
		String resultContent = "";
		
		String type = request.getParameter("at");			//access type
		switch (type) {
		case "pages":
			Map<String, Page> mapPages = (Map<String, Page>)
				cont.getAttribute(FaceConstants.CTX_K_PAGES);
			Gson gson = new Gson();
			resultContent = gson.toJson(mapPages);
			break;

		default:
			break;
		}
		
		response.getWriter().write(resultContent);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
