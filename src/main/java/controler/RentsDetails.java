package controler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/rents/details")
public class RentsDetails extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {

				request.setAttribute("maReservation", reservationService.findReservation(id));
				Reservation newReservation=reservationService.findReservation(id);				
				
				List<Vehicule> vehicule=vehiculeService.findById(newReservation.getVehicule_id());
				request.setAttribute("ListVehicule", vehicule);
				request.setAttribute("nbVehicule", vehicule.size());
				
				List<Client> client=clientService.findById(	newReservation.getClient_id());
				request.setAttribute("ListClient", client);
				request.setAttribute("nbClient", client.size());
				

			
			}catch(ServiceException e) {
				System.out.println("Error users details");
			}
			dispatcher.forward(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}

	}