package com.ensta.rentmanager.service;

import java.sql.Date;
import java.util.List;

import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicule;

public class ReservationService {
	private static ReservationService instance = null;
	private ReservationDao reservationDao;
	
	private ReservationService() {
		this.reservationDao = ReservationDao.getInstance();
	}
	
	public static ReservationService getInstance() {
		if (instance == null) {
			instance = new ReservationService();
		}
		
		return instance;
	}
	public long create(Reservation reservation) throws ServiceException {
		try {
			return reservationDao.create(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public long updateDebut(Date debut, int id) throws ServiceException {
		try {
			return reservationDao.updateDebut(debut, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public long updateClient(int client_id, int id) throws ServiceException {
		try {
			return reservationDao.updateClient(client_id, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	public long updateVehicule(int vehicule_id, int id) throws ServiceException {
		try {
			return reservationDao.updateVehicule(vehicule_id, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	
	
	public long updateFin(Date Fin, int id) throws ServiceException {
		try {
			return reservationDao.updateFin(Fin, id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public long delete(int Id) throws ServiceException {
		try {
			return reservationDao.delete(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	 
	}
	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Reservation> findResaByVehiculeId(int Id_vehicule) throws ServiceException {
		try {
			return reservationDao.findResaByVehiculeId(Id_vehicule);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<Reservation> findResaByClientId(int Id_client) throws ServiceException {
		try {
			return reservationDao.findResaByClientId(Id_client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public Reservation findReservation(int id) throws ServiceException {
		try {
			return reservationDao.findReservation(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
