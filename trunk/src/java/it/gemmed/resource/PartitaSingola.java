package it.gemmed.resource;

import java.sql.Date;
import java.sql.Time;


/**
 * Rappresentazione di una partita singola con dati inerenti alla data
 * di svolgimento, al luogo di svolgimento, al torneo a cui appartiene
 * ed al tipo di campo su cui si gioca. Contiene anche le informazioni
 * dei giocatori che la disputano, dei risultati dei vari set e del
 * vincitore finale.
 * 
 * @author GEMMED
 */
public class PartitaSingola {
	private final int numeroPartita;
	private Date data;
	private Time ora;
	private int campo;
	private String circolo;
	private String sfidanteA;
	private String sfidanteB;
	private boolean vittoriaSfidanteA;
	private Risultato set1;
	private Risultato set2;
	private Risultato set3;
	private final int torneo;
	private boolean forfait;
	private String nomeTorneo;

	/**
	 * Costruttore di PartitaSingola priva di dati all'interno di un torneo
	 * 
	 * @param numeroPartita
	 * @param torneo
	 */
	public PartitaSingola(int numeroPartita, int torneo) {
		super();
		this.numeroPartita = numeroPartita;
		this.torneo = torneo;
	}
			
	/**
	 * Costruttore di PartitaSingola con dati i soli sfidanti all'interno di un torneo
	 * 
	 * @param numeroPartita
	 * @param torneo
	 * @param sfidanteA
	 * @param sfidanteB
	 */
	public PartitaSingola(int numeroPartita, int torneo, String sfidanteA, String sfidanteB) {
		super();
		this.numeroPartita = numeroPartita;
		this.torneo = torneo;
		this.sfidanteA = sfidanteA;
		this.sfidanteB = sfidanteB;
	}
	

	/**
	 * @return data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return ora
	 */
	public Time getOra() {
		return ora;
	}

	/**
	 * @param ora the ora to set
	 */
	public void setOra(Time ora) {
		this.ora = ora;
	}

	/**
	 * @return campo
	 */
	public int getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(int campo) {
		this.campo = campo;
	}

	/**
	 * @return circolo
	 */
	public String getCircolo() {
		return circolo;
	}

	/**
	 * @param circolo the circolo to set
	 */
	public void setCircolo(String circolo) {
		this.circolo = circolo;
	}

	/**
	 * @return sfidanteA
	 */
	public String getSfidanteA() {
		return sfidanteA;
	}

	/**
	 * @param sfidanteA the sfidanteA to set
	 */
	public void setSfidanteA(String sfidanteA) {
		this.sfidanteA = sfidanteA;
	}

	/**
	 * @return sfidanteB
	 */
	public String getSfidanteB() {
		return sfidanteB;
	}

	/**
	 * @param sfidanteB the sfidanteB to set
	 */
	public void setSfidanteB(String sfidanteB) {
		this.sfidanteB = sfidanteB;
	}

	/**
	 * @return vittoriaSfidanteA
	 */
	public boolean isvittoriaSfidanteA() {
		return vittoriaSfidanteA;
	}

	
	/**
	 * @return vittoriaSfidanteA
	 */
	public boolean getVittoriaSfidanteA() {
		return vittoriaSfidanteA;
	}
	
	
	/**
	 * @param vittoriaSfidanteA the vittoriaSfidanteA to set
	 */
	public void setvittoriaSfidanteA(boolean vittoriaSfidanteA) {
		this.vittoriaSfidanteA = vittoriaSfidanteA;
	}

	/**
	 * @return set1
	 */
	public Risultato getSet1() {
		return set1;
	}

	/**
	 * @param set1 the set1 to set
	 */
	public void setSet1(Risultato set1) {
		this.set1 = set1;
	}

	/**
	 * @return set2
	 */
	public Risultato getSet2() {
		return set2;
	}

	/**
	 * @param set2 the set2 to set
	 */
	public void setSet2(Risultato set2) {
		this.set2 = set2;
	}

	/**
	 * @return set3
	 */
	public Risultato getSet3() {
		return set3;
	}

	/**
	 * @param set3 the set3 to set
	 */
	public void setSet3(Risultato set3) {
		this.set3 = set3;
	}

	/**
	 * @return numeroPartita
	 */
	public int getNumeroPartita() {
		return numeroPartita;
	}

	/**
	 * @return torneo
	 */
	public int getTorneo() {
		return torneo;
	}

	/**
	 * @param vittoriaSfidanteA
	 */
	public void setVittoriaSfidanteA(boolean vittoriaSfidanteA) {
		this.vittoriaSfidanteA = vittoriaSfidanteA;
	}
	
	/**
	 * @return forfait
	 */
	public boolean getForfait() {
		return forfait;
	}

	/**
	 * @param vittoriaSfidanteA
	 */
	public void setForfait(boolean forfait) {
		this.forfait = forfait;
	}
	
	/**
	 * @return nomeTorneo
	 */
	public String getNomeTorneo() {
		return nomeTorneo;
	}

	/**
	 * @param nomeTorneo the nomeTorneo to set
	 */
	public void setNomeTorneo(String nomeTorneo) {
		this.nomeTorneo = nomeTorneo;
	}

}
