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
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;



@WebServlet("/cars/create")
public class VehiculeCreateServlet extends HttpServlet {
	
	ClientService clientService = ClientService.getInstance();
	VehiculeService vehiculeService = VehiculeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
		
		
		
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String manufacturer = request.getParameter("manufacturer");
        String modele = request.getParameter("modele");
        int seats = Integer.parseInt(request.getParameter("seats"));
  
        Vehicule newVehicule= new Vehicule();
        newVehicule.setConstructeur(manufacturer);
        newVehicule.setModele(modele);
        newVehicule.setNb_places(seats);     
        
        RequestDispatcher dispatcher ;

        try {
        	vehiculeService.checkPlace(seats);
            vehiculeService.create(newVehicule);
            response.sendRedirect("/RentManager/cars");
            return;
            
        } catch (ServiceException e) 
            // TODO Auto-generated catch block
        	{
    			HttpSession session = request.getSession(true);	
    			if(e.getMessage()=="Le vehicule doit avoir entre 2 et 9 places") {
    				session.setAttribute("erroraddclient", "Le nombre de place n'est pas conforme !");
    			
    			}else {
    				session.setAttribute("erroraddclient", "Vehicule non crée!");
    			}
    			doGet(request,response);
    		}
        	
        }
       
   
	

}