package controler;


import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		
		
		
		try {
			request.setAttribute("vehicles", vehiculeService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("nbVehicles", "Une erreur est survenue");
		}
		try {
			request.setAttribute("users", clientService.findAll());
		} catch (ServiceException e) {
			request.setAttribute("nbUtilisateurs", "Une erreur est survenue");
		}
		dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Date debut = Date.valueOf(request.getParameter("begin"));
		Date fin = Date.valueOf(request.getParameter("end"));
		int car = Integer.parseInt(request.getParameter("car"));
		int client = Integer.parseInt(request.getParameter("client"));
		
		Reservation newreservation = new Reservation();
		newreservation.setClient_id(client);
		newreservation.setVehicule_id(car);
		newreservation.setDebut(debut);
		newreservation.setFin(fin);
		
		RequestDispatcher dispatcher ;
		
		try {
			reservationService.create(newreservation);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		}
		response.sendRedirect("/RentManager/rents");
		return;
	}
}