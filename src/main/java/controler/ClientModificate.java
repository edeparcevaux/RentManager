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
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/users/modificate")
public class ClientModificate extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/modificate.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
	    try {
	    	String last_name = request.getParameter("last_name");
	    	String first_name = request.getParameter("first_name");
		    String email = request.getParameter("email");
		    String birthdate =request.getParameter("birthdate");
		
	    	if (last_name!="")
	    	{
	    		clientService.updateNom(last_name, id);
	    	}
	    	if (first_name!="")
	    	{
	    		clientService.updatePrenom(first_name, id);
	    	}
	    	if (email!="")
	    	{
	    		clientService.updateEmail(email, id);
	    	}
	    	if (birthdate.length()>0)
	    	{
	    		clientService.updateNaissance(Date.valueOf(birthdate), id);
	    	}

	 } catch (ServiceException e) {
        // TODO Auto-generated catch block

        System.out.println("Exec");
      
    }
    
	    response.sendRedirect("/RentManager/users");
        return;
    
		
	}
}