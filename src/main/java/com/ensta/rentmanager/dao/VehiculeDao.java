package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class VehiculeDao {
	
	private static VehiculeDao instance = null;
	private VehiculeDao() {}
	public static VehiculeDao getInstance() {
		if(instance == null) {
			instance = new VehiculeDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur,modele, nb_places) VALUES(?,?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String UPDATE_CONSTRUCTEUR_VEHICULE = "UPDATE Vehicle SET constructeur=? WHERE id=?";
	private static final String UPDATE_MODELE_VEHICULE = "UPDATE Vehicle SET modele=? WHERE id=?";
	private static final String UPDATE_SEATS_VEHICULE = "UPDATE Vehicle SET nb_places=? WHERE id=?";
	
	public long updateSeats (int nb_places, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_SEATS_VEHICULE);) {
			
			statement.setInt(1, nb_places);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	
	public long updateModele (String modele, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_MODELE_VEHICULE);) {
			
			statement.setString(1, modele);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	public long updateConstructeur (String constructeur, int id) throws DaoException {
		
		try (Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_CONSTRUCTEUR_VEHICULE);) {
			
			statement.setString(1, constructeur);
			statement.setInt(2, id);
			
			long result = statement.executeUpdate();
			return result;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
	}
	}
	
	public long create(Vehicule vehicule) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_QUERY);) 
		{
			statement.setString(1, vehicule.getConstructeur());
			statement.setString(2, vehicule.getModele());
			statement.setInt(3, vehicule.getNb_places());
			
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	public long delete(Vehicule vehicule) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY);) 
		{
			long id = vehicule.getId();
			statement.setLong(1,id);
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}
	
	public long delete(int Id) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY);) 
		{
			statement.setInt(1, Id);
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	public List<Vehicule> findById(int id) throws DaoException {
		List<Vehicule> resultList = new ArrayList<Vehicule>();
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLE_QUERY);)
		
		{	statement.setInt(1, id);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setId((int)id);
				vehicule.setConstructeur(resultSet.getString(1));
				vehicule.setModele(resultSet.getString(2));
				vehicule.setNb_places(resultSet.getInt(3));
				resultList.add(vehicule);
			} 
			return resultList;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	
	}
	public Vehicule findVehicule(int num) throws DaoException{
		 Vehicule vehicule= new Vehicule();
		try(Connection conn= ConnectionManager.getConnection();
				PreparedStatement statement=conn.prepareStatement(FIND_VEHICLE_QUERY);)
		{
			statement.setInt(1, num);
		ResultSet resultSet =  statement.executeQuery();
		if(resultSet.next()) {
			vehicule.setId(num);
			vehicule.setConstructeur(resultSet.getString(1));
			vehicule.setModele(resultSet.getString(2));
			vehicule.setNb_places(resultSet.getInt(3));
			
			}else {
				System.out.println("Le vehicule n'existe pas");
			}
			
			
		}catch(SQLException e) {
			throw new DaoException("Impossible de récupérer le vehicule"+e.getMessage());
		}
		return vehicule;
	}
	public List<Vehicule> findAll() throws DaoException {
		List<Vehicule> resultList = new ArrayList<Vehicule>();
		
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLES_QUERY);) 
		{
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Vehicule vehicule = new Vehicule();
				vehicule.setId(resultSet.getInt(1));
				vehicule.setConstructeur(resultSet.getString(2));
				vehicule.setModele(resultSet.getString(3));
				vehicule.setNb_places(resultSet.getInt(4));
				resultList.add(vehicule);
		}
			return resultList;			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	 
	}
	

}
