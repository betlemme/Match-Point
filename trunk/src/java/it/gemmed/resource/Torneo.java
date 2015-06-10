package it.gemmed.resource;

import java.sql.Date;

/**
 * Classe di definizione per un torneo
 * Vengono definiti il nome del torneo, le date di inizio
 * e di fine, le date di inizio e fine iscrizione, la categoria,
 * il circolo promotore e quindi la città di svolgimento, la provincia,
 * l'indirizzo ed il numero di telefono del referente.
 * Vengono poi definiti dei campi per la federazione, per l'approvazione
 * ed una eventuale riduzione per il calcolo dei punteggi per la classifica
 * dei giocatori.
 * 
 * @author GEMMED
 *
 */
public class Torneo {
	private final String nome;
	private final Date data_inizio;
	private final Date data_fine;
	private final Date iscrizione_inizio;
	private final Date iscrizione_fine;
	private String categoria;
	// Farlo auto-increment?
	private int id;
	private final String circolo;
	private final String tipologia;
	private String arbitro;
	private String federazione;
	private String convalidato = null;
	private int riduzione;
	
	private String indirizzo;
	private String citta;
	private String provincia;
	private String telefono;
	
	/**
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @return provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @param convalidato the convalidato to set
	 */
	public void setConvalidato(String convalidato) {
		this.convalidato = convalidato;
	}
	
	/**
	 *  Costruttore con tutti i campi, da usare nelle fasi di ricerca
	 * @param nome
	 * @param data_inizio
	 * @param data_fine
	 * @param categoria
	 * @param id
	 * @param circolo
	 * @param tipologia
	 * @param arbitro
	 * @param federazione
	 * @param convalidato
	 * @param iscrizione_inizio
	 * @param iscrizione_fine
	 * @param riduzione
	 */
	public Torneo(String nome, Date data_inizio, Date data_fine,
			String categoria, int id, String circolo, String tipologia,
			String arbitro, String federazione, String convalidato, Date iscrizione_inizio, Date iscrizione_fine, int riduzione) {
		super();
		this.nome = nome;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.categoria = categoria;
		this.id = id;
		this.circolo = circolo;
		this.tipologia = tipologia;
		this.arbitro = arbitro;
		this.federazione = federazione;
		this.convalidato = convalidato;
		this.iscrizione_inizio = iscrizione_inizio;
		this.iscrizione_fine = iscrizione_fine;
		this.riduzione = riduzione;
	}	
	
	
	/**
	 * ---Nota---
	 * Costruttore obsoleto, utilizzare quello completo.
	 * Costruttore con quasi tutti i campi, tranne riduzione
	 *  
	 * @param nome
	 * @param data_inizio
	 * @param data_fine
	 * @param categoria
	 * @param id
	 * @param circolo
	 * @param tipologia
	 * @param arbitro
	 * @param federazione
	 * @param convalidato
	 * @param iscrizione_inizio
	 * @param iscrizione_fine
	 */
	public Torneo(String nome, Date data_inizio, Date data_fine,
			String categoria, int id, String circolo, String tipologia,
			String arbitro, String federazione, String convalidato, Date iscrizione_inizio, Date iscrizione_fine) {
		super();
		this.nome = nome;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.categoria = categoria;
		this.id = id;
		this.circolo = circolo;
		this.tipologia = tipologia;
		this.arbitro = arbitro;
		this.federazione = federazione;
		this.convalidato = convalidato;
		this.iscrizione_inizio = iscrizione_inizio;
		this.iscrizione_fine = iscrizione_fine;
	}
	
	/**
	 *  ---Nota---
	 * Costruttore obsoleto, utilizzare quello completo.
	 * Costruttore senza id, da usare solo durante la creazione del torneo
	 * @param nome
	 * @param data_inizio
	 * @param data_fine
	 * @param circolo
	 * @param tipologia
	 * @param convalidato
	 */
	public Torneo(String nome, Date data_inizio, Date data_fine, Date iscrizione_inizio, Date iscrizione_fine,
			String circolo, String tipologia, String convalidato) {
		super();
		this.nome = nome;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.circolo = circolo;
		this.tipologia = tipologia;
		this.convalidato = convalidato;
		this.iscrizione_inizio = iscrizione_inizio;
		this.iscrizione_fine = iscrizione_fine;
	}
	
	
	
	/**
	 * ---Nota---
	 * Costruttore obsoleto, utilizzare quello completo.
	 * Costruttore con quasi tutti i campi, mancano inscrizione_inizio, iscrizione_fine e riduzione
	 * @param nome
	 * @param data_inizio
	 * @param data_fine
	 * @param categoria
	 * @param id
	 * @param circolo
	 * @param tipologia
	 * @param arbitro
	 * @param federazione
	 * @param convalidato
	 */
	public Torneo(String nome, Date data_inizio, Date data_fine,
			String categoria, int id, String circolo, String tipologia,
			String arbitro, String federazione, String convalidato) {
		super();
		this.nome = nome;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.categoria = categoria;
		this.id = id;
		this.circolo = circolo;
		this.tipologia = tipologia;
		this.arbitro = arbitro;
		this.federazione = federazione;
		this.convalidato = convalidato;
		this.iscrizione_inizio = null;
		this.iscrizione_fine = null;
	}
	
	
	/**
	 * Costruttore completo
	 * 
	 * @param nome
	 * @param data_inizio
	 * @param data_fine
	 * @param iscrizione_inizio
	 * @param iscrizione_fine
	 * @param categoria
	 * @param id
	 * @param circolo
	 * @param tipologia
	 * @param arbitro
	 * @param federazione
	 * @param convalidato
	 * @param riduzione
	 * @param indirizzo
	 * @param citta
	 * @param provincia
	 * @param telefono
	 */
	public Torneo(String nome, Date data_inizio, Date data_fine,
			String categoria, int id, String circolo, String tipologia,
			String arbitro, String federazione, String convalidato, Date iscrizione_inizio, Date iscrizione_fine, int riduzione,
			String indirizzo, String citta, String provincia, String telefono) {
		super();
		this.nome = nome;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.iscrizione_inizio = iscrizione_inizio;
		this.iscrizione_fine = iscrizione_fine;
		this.categoria = categoria;
		this.id = id;
		this.circolo = circolo;
		this.tipologia = tipologia;
		this.arbitro = arbitro;
		this.federazione = federazione;
		this.convalidato = convalidato;
		this.riduzione = riduzione;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
	}

	/**
	 * @return iscrizione_inizio
	 */
	public Date getIscrizioneInizio() {
		return iscrizione_inizio;
	}
	
	/**
	 * @return iscrizione_fine
	 */
	public Date getIscrizioneFine() {
		return iscrizione_fine;
	}
	
	/**
	 * @return iscrizione_inizio
	 */
	public Date getIscrizione_inizio() {
		return iscrizione_inizio;
	}
	
	/**
	 * @return iscrizione_fine
	 */
	public Date getIscrizione_fine() {
		return iscrizione_fine;
	}
	
	/**
	 * @return categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * @return arbitro
	 */
	public String getArbitro() {
		return arbitro;
	}
	
	/**
	 * @param arbitro the arbitro to set
	 */
	public void setArbitro(String arbitro) {
		this.arbitro = arbitro;
	}
	
	/**
	 * @return federazione
	 */
	public String getFederazione() {
		return federazione;
	}
	
	/**
	 * @param federazione the federazione to set
	 */
	public void setFederazione(String federazione) {
		this.federazione = federazione;
	}
	
	/**
	 * @param riduzione the riduzione to set
	 */
	public void setRiduzione(int riduzione) {
		this.riduzione = riduzione;
	}
	
	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return data_inizio
	 */
	public Date getData_inizio() {
		return data_inizio;
	}
	/**
	 * @return data_fine
	 */
	public Date getData_fine() {
		return data_fine;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return circolo
	 */
	public String getCircolo() {
		return circolo;
	}
	/**
	 * @return tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}
	/**
	 * @return convalidato
	 */
	public String getConvalidato() {
		return convalidato;
	}
	
	/**
	 * @return riduzione
	 */
	public int getRiduzione(){
		return riduzione;
	}
	
	
	/**
	 * Metodo di controllo per la fine iscrizione
	 * 
	 * @return True se la fase di iscrizione è finita.
	 */
	public boolean getIsIscrizioneConclusa(){
		java.util.Date now = new java.util.Date();
		return now.after(iscrizione_fine);
	}
	
	
}
