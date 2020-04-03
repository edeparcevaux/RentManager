/*
 * Etape avant la base de donnée, où sont effectuées les vérifications des données entrées par l'utilisateur
 * */

package com.ensta.rentmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.dao.ClientDao;

public class ClientService {
	
	private static ClientService instance = null;
	private ClientDao clientDao;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}

	public long updatePrenom (String prenom, int id) throws ServiceException {
		try {
			return clientDao.updatePrenom(prenom, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public long updateNom(String nom, int id) throws ServiceException {
		try {
			return clientDao.updateNom(nom, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public long updateEmail (String email, int id) throws ServiceException {
		try {
			return clientDao.updateEmail(email, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public long updateNaissance (Date naissance, int id) throws ServiceException {
		try {
			return clientDao.updateNaissance(naissance, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	
	
	
	public long create(Client client) throws ServiceException {
	
		checkAge(client);
		try {
			return clientDao.create(client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public void checkAge(Client client) throws ServiceException {
		long age = ChronoUnit.YEARS.between(client.getNaissance().toLocalDate(), LocalDate.now());
		if (age<18) {
			throw new ServiceException("le client doit avoir 18 ans");
		}
	}
	
	public void checkMail(String mail) throws ServiceException {

			List<String> allmail=new ArrayList<String>();
			try {
				List<Client> listallclients = clientDao.findAll();
				for(Client client:listallclients) {
					allmail.add(client.getEmail());
				}
				if(allmail.contains(mail)) {
					throw new ServiceException("Le mail existe déjà!");
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
	
	public long delete(int Id) throws ServiceException {
		try {
			return clientDao.delete(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	 
	}

	public void checkPrenom(String prenom) throws ServiceException {
		
		if (prenom.length() < 3) {
			throw new ServiceException("Le client doit avoir un prenom non vide");
		}
	}

	public void checkNom(String nom ) throws ServiceException {
		
		if (nom.length() <3 ) {
			throw new ServiceException("Le Nom doit contenir plus de 3 caractères !");
		}
	}


	public List<Client> findById(int id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Client findClient(int id) throws ServiceException {
		try {
			return clientDao.findClient(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
