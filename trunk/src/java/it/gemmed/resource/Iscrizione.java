package it.gemmed.resource;

import java.util.BitSet;

/**
 * Rappresenta l'iscrizione a un torneo
 * 
 * @author GEMMED
 *
 */
public class Iscrizione {
	private final String giocatore;
	private final Boolean[] disponibilita;
	private final int torneo;
	
	/**
	 * Costruttore completo di iscrizione
	 * 
	 * @param giocatore colui che effettua l'iscrizione
	 * @param disponibilita la sua disponibilità oraria per poter disputare le partite
	 * @param torneo il torneo a cui si sta iscrivendo
	 */
	public Iscrizione(String giocatore, Boolean[] disponibilita, int torneo) {
		super();
		this.giocatore = giocatore;
		this.disponibilita = disponibilita;
		this.torneo = torneo;
	}

	/**
	 * @return giocatore
	 */
	public String getGiocatore() {
		return giocatore;
	}

	/**
	 * @return disponibilita
	 */
	public Boolean[] getDisponibilita() {
		return disponibilita;
	}

	/**
	 * @return torneo
	 */
	public int getTorneo() {
		return torneo;
	}
	
	
}
