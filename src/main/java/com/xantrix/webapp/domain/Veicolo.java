package com.xantrix.webapp.domain;

import java.io.Serializable;

public class Veicolo implements Serializable {

	private static final long serialVersionUID = 463942261651698573L;

	private Long idVeicolo;
	private String marca;
	private String modello;
	private String targa;
	private int anno;
	
	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	private int disponibilita;
	
	public Veicolo() {
		
	}

	public Long getIdVeicolo() {
		return idVeicolo;
	}

	public void setIdVeicolo(Long idVeicolo) {
		this.idVeicolo = idVeicolo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
}
