package controler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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



@WebServlet("/users/details")
public class ClientDetailServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {

				request.setAttribute("monClient", clientService.findClient(id));
				List<Reservation> clientsReservation=reservationService.findResaByClientId(id);
				request.setAttribute("ListReservations", clientsReservation);
				request.setAttribute("nbReservation", clientsReservation.size());
				HashSet<Vehicule> vehiculeReservation = new HashSet<Vehicule>();
				
				if( clientsReservation.size() !=0);
				{	
					ArrayList<Integer> vehiculeId = new ArrayList<Integer>();
					for( int i=0; i<clientsReservation.size(); i++)
					{
						Reservation rersa = new Reservation();
						rersa=clientsReservation.get(i);
						
						vehiculeId.add(rersa.getVehicule_id());
					}
						for (int j=0; j<vehiculeId.size(); j++)
						{
							
							vehiculeReservation.add(vehiculeService.findVehicule(vehiculeId.get(j)));
							
						}
						
						
				request.setAttribute("nbVehicule", vehiculeReservation.size());	
				}
				
				request.setAttribute("ListVehicule", vehiculeReservation);	
			}catch(ServiceException e) {
				System.out.println("Error users details");
			}
			dispatcher.forward(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}

	}