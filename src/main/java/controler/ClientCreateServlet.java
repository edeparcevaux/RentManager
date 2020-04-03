package controler;


import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
	
		
		
		
		dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        String email = request.getParameter("email");
        Date birthdate = Date.valueOf(request.getParameter("birthdate"));
        Client client=new Client(last_name,first_name,email,birthdate);
        
		try {
			clientService.checkNom(last_name);
			clientService.checkPrenom(first_name);
			clientService.checkMail(email);
			clientService.create(client);
			response.sendRedirect("/RentManager/users");
			return;
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			if(e.getMessage()=="Le mail existe déjà!") {
				session.setAttribute("erroraddclient", "le mail existe déjà !");
			}else if(e.getMessage()=="le client doit avoir 18 ans"){
				session.setAttribute("erroraddclient", "le client doit avoir plus de 18 ans !");
			}else if(e.getMessage()=="Le Nom doit contenir plus de 3 caractères !"){
				session.setAttribute("erroraddclient", "Le Nom doit contenir plus de 3 caractères !");
			}else {
				session.setAttribute("erroraddclient", "Le Prénom doit contenir plus de 3 caractères !");
			}
			doGet(request,response);
		}
	}
}