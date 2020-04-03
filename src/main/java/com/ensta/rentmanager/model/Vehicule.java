package com.ensta.rentmanager.model;
public class Vehicule {
	private int id;	//Private : seule cette class y a accès
	private String constructeur;
	private String modele;
	private int nb_places;
	
	public Vehicule () {
		
	}
	public Vehicule (int id, String constructeur, String modele, int nb_places ) {
		this.id = id;
		this.constructeur = constructeur;
		this.modele =modele;
		this.nb_places = nb_places;
	}
	
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public int getNb_places() {
		return nb_places;
	}
	public void setNb_places(int nb_place) {
		this.nb_places = nb_place;
	}	
	@Override
	public String toString() {
		return "vehicule [id=" + id + ", constructeur=" + constructeur + ", nb_places="
				+ nb_places + "]";
	}	
}
