package controler;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/cars")
public class VehicleListServlet extends HttpServlet {
	

	VehiculeService vehiculeService = VehiculeService.getInstance();

	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
		try {
			request.setAttribute("vehicles", vehiculeService.findAll());
		
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}