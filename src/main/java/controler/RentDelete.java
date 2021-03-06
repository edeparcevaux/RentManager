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



@WebServlet("/rents/delete")
public class RentDelete extends HttpServlet{
ReservationService reservationService = ReservationService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				reservationService.delete(id);
			} catch (ServiceException e) {
				HttpSession session = request.getSession(true);	
				session.setAttribute("errordeleteclient", "le client ne peut pas être supprimé car il possede des commandes!");
			}
			//dispatcher.forward(request, response);
			response.sendRedirect("/RentManager/rents");
			return;
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}
}