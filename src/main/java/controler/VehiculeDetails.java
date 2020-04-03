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



@WebServlet("/cars/details")
public class VehiculeDetails extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {

				request.setAttribute("monVehicule", vehiculeService.findVehicule(id));
				List<Reservation> vehiculeReservation=reservationService.findResaByVehiculeId(id);
				request.setAttribute("ListReservations", vehiculeReservation);
				request.setAttribute("nbReservation", vehiculeReservation.size());
				List<Client> clientReservation = new ArrayList<Client>();
				
				if( vehiculeReservation.size() !=0);
				{	
					ArrayList<Integer> clientId = new ArrayList<Integer>();
					for( int i=0; i<vehiculeReservation.size(); i++)
					{
						Reservation rersa = new Reservation();
						rersa=vehiculeReservation.get(i);
						
						clientId.add(rersa.getClient_id());
					}
						for (int j=0; j<clientId.size(); j++)
						{
							
							clientReservation.add(clientService.findClient(clientId.get(j)));
							
						}
						
						
				request.setAttribute("nbClient", clientReservation.size());	
				}
				
				request.setAttribute("ListClient", clientReservation);	
			}catch(ServiceException e) {
				System.out.println("Error users details");
			}
			dispatcher.forward(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}

	}