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


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");

		try {
			request.setAttribute("nbUtilisateurs", clientService.findAll().size());
		} catch (ServiceException e) {
			request.setAttribute("nbUtilisateurs", "Une erreur est survenue");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("nbVoitures", vehiculeService.findAll().size());
		} catch (ServiceException e) {
			request.setAttribute("nbVoitures", "Une erreur est survenue");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("nbReservations", reservationService.findAll().size());
		} catch (ServiceException e) {
			request.setAttribute("nbReservations", "Une erreur est survenue");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}