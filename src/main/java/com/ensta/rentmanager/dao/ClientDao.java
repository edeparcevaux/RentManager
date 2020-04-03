/*
 * Fait le lien entre l'application et les données
 * 
 * */

package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
//import com.ensta.rentmanager.model.Vehicle;// à remettre
import com.ensta.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private ClientDao() {}
	/*private ClientDao(boolean test) {
		this.test = test;
	}*/
	//private boolean test;
	private static ClientDao instance = null;
	//private static ClientDao instanceTest = null;

	/*public static ClientDao getInstance(boolean test) {
		if (test ){
			if(instanceTest == null) {
				instanceTest = new ClientDao(true);
			}
		}

		return instance;
	}*/
	
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	  
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_PRENOM_CLIENT = "UPDATE Client SET prenom=? WHERE id=?";
	private static final String UPDATE_NOM_CLIENT = "UPDATE Client SET nom=? WHERE id=?";
	private static final String UPDATE_EMAIL_CLIENT = "UPDATE Client SET email=? WHERE id=?";
	private static final String UPDATE_NAISSANCE_CLIENT = "UPDATE Client SET naissance=? WHERE id=?";
	
	public long updateNaissance (Date naissance, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_NAISSANCE_CLIENT);) {
			
			statement.setDate(1, naissance);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	public long updateEmail (String email, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_EMAIL_CLIENT);) {
			
			statement.setString(1, email);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	public long updateNom (String nom, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_NOM_CLIENT);) {
			
			statement.setString(1, nom);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	
	
	
	public long updatePrenom (String prenom, int id) throws DaoException {
	
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_PRENOM_CLIENT);) {
			
			statement.setString(1, prenom);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
		
	public long create(Client client) throws DaoException {
		//Connection conn = test ? ConnectionManager.get
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_CLIENT_QUERY);) 
		{
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setDate(4, client.getNaissance());
			
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	
	public long delete(int Id) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_CLIENT_QUERY);) 
		{
			statement.setInt(1, Id);
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}
	
	public void delete (Client client) throws DaoException {
        try(Connection conn = ConnectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(DELETE_CLIENT_QUERY);){
        long id = client.getId();
        statement.setLong(1,id);
        statement.executeUpdate();
        } catch(SQLException e) {
                System.out.println("Erreur lors du select " + e.getMessage());
            }
        }
	
	public List<Client> findById(int id) throws DaoException {
		List<Client> resultList = new ArrayList<Client>();
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_CLIENT_QUERY);)
		
		{	statement.setInt(1, id);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				Client client = new Client();
				client.setId((int)id);
				client.setNom(resultSet.getString(1));
				client.setPrenom(resultSet.getString(2));
				client.setEmail(resultSet.getString(3));
				client.setNaissance(resultSet.getDate(4));
				resultList.add(client);
			} 
			return resultList;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	
	}
	
	public Client findClient(int num) throws DaoException{
		Client client= new Client();
		try(Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_CLIENT_QUERY);)
		{
			statement.setInt(1, num);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				client.setId(num);
				client.setNom(resultSet.getString(1));
				client.setPrenom(resultSet.getString(2));
				client.setEmail(resultSet.getString(3));
				client.setNaissance(resultSet.getDate(4));
			}else {
				System.out.println("Le client n'existe pas");
			}
			
			
		}catch(SQLException e) {
			throw new DaoException("Impossible de récupérer le client"+e.getMessage());
		}
		return client;
	}

	public List<Client> findAll() throws DaoException {
		List<Client> resultList = new ArrayList<Client>();
		
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_CLIENTS_QUERY);) 
		{
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Client client = new Client();
				client.setId(resultSet.getInt(1));
				client.setNom(resultSet.getString(2));
				client.setPrenom(resultSet.getString(3));
				client.setEmail(resultSet.getString(4));
				client.setNaissance(resultSet.getDate(5));
				resultList.add(client);
		}
			return resultList;			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	 
	}

	public static void main (String... args) {
		ClientDao dao = ClientDao.getInstance();
		
		try {
			//List<Client> list = dao.findById(2);
			List<Client> list = dao.findAll();

			for (Client c : list) {
				System.out.println(c);
			}
		} catch (DaoException e) {
			System.out.println("Erreur lors du Select All " +e.getMessage());
		
		}
		/*Optional<Client> optClient = dao.findById(0);
		
		if (optClient.isPresent()) {
			Client c = optClient.get();
			System.out.println(c);
		}*/
		
	}
	


	/*public Optional<Client> findById(long id) { //à changer
		//wrapper contenant un objet. Sert quand il faut vérifier qu'un obj est non null ex : null[4] ==> Error index out of range
		Client c = new Client();
		
		Optional<Client> optClient = Optional.of(c);
			try (Connection conn = ConnectionManager.getConnection();) {
				
			} catch(SQLException e) {
				return Optional.empty();
			}
		return optClient;
	}*/
	
}







