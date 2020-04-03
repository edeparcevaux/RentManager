package com.ensta.rentmanager.model;

import java.sql.Date;

public class Reservation {
	private int id;	//Private : seule cette class y a accès
	private int client_id;
	private int vehicule_id;
	private Date debut;
	private Date fin;
	
	public Reservation () {
		
	}
	
	public Reservation (int id, int Id_client, int Id_vehicule, Date debut, Date fin) {
		this.id = id;
		this.client_id = Id_client;
		this.vehicule_id= Id_vehicule;
		this.debut = debut;
		this.fin = fin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getVehicule_id() {
		return vehicule_id;
	}

	public void setVehicule_id(int vehicule_id) {
		this.vehicule_id = vehicule_id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

}