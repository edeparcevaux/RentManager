/*
 * Interface de commande pour gérer clients et véhicules
 * */

package controler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehiculeService;
import com.ensta.rentmanager.service.ReservationService;
//import com.ensta.rentmanager.exception;

public class CliControler {
	private ClientService clientservice = ClientService.getInstance();
	private VehiculeService vehiculeservice = VehiculeService.getInstance();
	private ReservationService reservationservice = ReservationService.getInstance();

	public static void main(String[] args) {
		CliControler cli = new CliControler();
		

		Scanner sc = new Scanner(System.in);
		
		boolean done = false;
		while(!done) {
			
			System.out.println("Liste des opérations");
			System.out.println("0 - Quitter");
			System.out.println("1 - Affiche la liste des clients");
			System.out.println("2 - Affiche un seul client (Entrer ID)");			
			System.out.println("3 - Ajoute un client");
			System.out.println("4 - Supprime un client");
			System.out.println("5 - Affiche la liste des véhicules");
			System.out.println("6 - Affiche un seul véhicule (Entrer ID)");
			System.out.println("7 - Ajoute un véhicule");
			System.out.println("8 - Supprime un véhicule");
			System.out.println("9 - Affiche la liste des réservations");
			System.out.println("10 - Affiche les réservations d'un client (Entrer ID)");
			System.out.println("11 - Affiche les réservations d'un véhicule (Entrer ID)");
			System.out.println("12 - Ajouter une réservation");
			System.out.println("13 - Supprime une réservation");
			
			int choix = sc.nextInt();
			sc.nextLine();
			
			switch(choix) {
			case 0:
				done=true;
				break;
			case 1:
				afficherListeClients(cli);
				break;	//Nécessaire, sinon execute case 2
			case 2:
				afficherClientId(cli, sc);
				break;
			case 3:
				ajouterClient(cli, sc);
				break;
			case 4:
				supprimerClientId(cli, sc);				
				break;
			case 5:
				afficherListeVehicules(cli);
				break;
			case 6:
				afficherVehiculeId(cli, sc);
				break;	
			case 7:
				ajouterVehicule(sc);
				break;
			case 8:
				System.out.println("Supprimer un véhicule");
				break;
			case 9:
				afficherReservations(cli);
				break;
			case 10:
				afficherReservationId(cli, sc);
				break;
			case 11:
				System.out.println("Entrez l'Id du véhicule poour voir ses réservations");
				try {
					List<Reservation> list = cli.reservationservice.findResaByVehiculeId(sc.nextInt());
					for(Reservation reservation : list) {
						System.out.println(reservation);
					}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				break;
			case 12:
				Reservation reservation= new Reservation();
				System.out.println("Entrer le client faisant la réservation [Id]");
				reservation.setClient_id(sc.nextInt());
				sc.nextLine();
				System.out.println("Entrer le véhicule à réserver [Id]");
				reservation.setVehicule_id(sc.nextInt());
				sc.nextLine();
				System.out.println("Entrer date de début de réservation");
				reservation.setDebut(Date.valueOf(sc.nextLine()));
				System.out.println("Entrer date de fin de réservation");
				reservation.setFin(Date.valueOf(sc.nextLine()));
				break;
			case 13:
				supprimerReservationId(cli, sc);
				break;
		default:
			System.out.println("PAS LE BON CHOIX");
		}
	}
		sc.close();
}

	private static void supprimerReservationId(CliControler cli, Scanner sc) {
		System.out.println("Entrer l'id de la réservation à supprimer");
		try {
			cli.reservationservice.delete(Integer.parseInt(sc.nextLine()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void afficherReservationId(CliControler cli, Scanner sc) {
		System.out.println("Entrez l'Id du client poour voir ses réservations");
		try {
			List<Reservation> list = cli.reservationservice.findResaByClientId(sc.nextInt());
			for(Reservation reservation : list) {
				System.out.println(reservation);
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void afficherReservations(CliControler cli) {
			try {
				List<Reservation> list = cli.reservationservice.findAll();
				for(Reservation reservation : list) {
					System.out.println(reservation);
				}
				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
	}

	private static void afficherVehiculeId(CliControler cli, Scanner sc) {
		try {
			System.out.println("Entrer l'id du véhicule à afficher");
			List<Vehicule> list = cli.vehiculeservice.findById(sc.nextInt());
			for(Vehicule vehicule : list) {
				System.out.println(vehicule);
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void ajouterVehicule(Scanner sc) {
		Vehicule vehicule= new Vehicule();
		System.out.println("Entrer le modèle");
		vehicule.setConstructeur((sc.nextLine().toUpperCase()));
		System.out.println("Entrer le nombre de places");
		vehicule.setNb_places((byte)sc.nextInt());
	}
	private static void afficherListeVehicules(CliControler cli) {
		try {
			List<Vehicule> list = cli.vehiculeservice.findAll();
			for(Vehicule vehicule : list) {
				System.out.println(vehicule);
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	private static void supprimerClientId(CliControler cli, Scanner sc) {
		System.out.println("Entrer l'id du client à supprimer");
		try {
			cli.clientservice.delete(Integer.parseInt(sc.nextLine()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void ajouterClient(CliControler cli, Scanner sc) {
		Client client = new Client();
		System.out.println("Entrer le nom");
		client.setNom((sc.nextLine().toUpperCase()));
		System.out.println("Entrer le prénom");
		client.setPrenom(sc.nextLine());
		System.out.println("Entrer l'email");
		client.setEmail(sc.nextLine());
		System.out.println("Entrer la date");
		client.setNaissance(Date.valueOf(sc.nextLine()));
		
		try {
			cli.clientservice.create(client);
		} catch(ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void afficherClientId(CliControler cli, Scanner sc) {
		try {
			System.out.println("Entrer l'id du client à afficher");
			List<Client> list = cli.clientservice.findById(Integer.parseInt(sc.nextLine()));
			for(Client client : list) {
				System.out.println(client);
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private static void afficherListeClients(CliControler cli) {
		try {
			List<Client> list = cli.clientservice.findAll();
			for(Client client : list) {
				System.out.println(client);
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
