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



@WebServlet("/cars/update")
public class VehiculeUpdateServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		
	    try {
			String manufacturer = request.getParameter("manufacturer");
	        String modele = request.getParameter("modele");
	        String seats= request.getParameter("seats");
	        
		
	    	if (manufacturer!="")
	    	{
	    		vehiculeService.updateConstructeur(manufacturer, id);
	    	}
	    	if ( modele!="")
	    	{
	    		vehiculeService.updateModele(modele, id);
	    	}
	    
	    	
	    	if (seats !="")
	    	{
	    		
	    		int nb_places = Integer.parseInt(seats);
	    		vehiculeService.updateSeats(nb_places, id);
	    	}

	 } catch (ServiceException e) {
        // TODO Auto-generated catch block

        System.out.println("Exec");
      
    }
    
	    response.sendRedirect("/RentManager/cars");
        return;
    
		
	}
}