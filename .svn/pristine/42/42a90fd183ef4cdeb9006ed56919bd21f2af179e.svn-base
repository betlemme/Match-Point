package it.gemmed.resource;

import java.sql.Date;

/**
 * Rappresenta i dati di un giocatore a partire da tutti i suoi
 * dati anagrafici (nome, cognome, data di nascita, indirizzo di residenza,
 * città di residenza, provincia di residenza, numero di telefono, sesso), ai dati
 * per il login (email, password), a quelli più tecnici relativi al tesseramento
 * (scadenza tessera, circolo di appartenenza), ed infine a quelli relativi alla
 * sua posizione in classifica (classifica, classifica dinamica, punti aggiuntivi) 
 * 
 * @author GEMMED
 */

public class Giocatore {
	private final String nome;
	private final String cognome;
	private final Date data_nascita;
	private String indirizzo;
	private String citta;
	private String provincia;
	private String telefono;
	private final String email;
	private String password;
	private String tessera;
	private Date scadenza_tessera;
	private String classifica;
	private String circolo;
	private String classifica_dinamica;
	private int atp_wta;
	private int itf;
	private boolean ismaschio;
	private int punti_aggiuntivi;
	
	/**
	 * Costruttore non completo per registrazione nuovo giocatore
	 * 
	 * @param nome
	 * @param cognome
	 * @param data_nascita
	 * @param email funzionante anche come nome utente per il login
	 * @param password
	 * @param ismaschio
	 */
	public Giocatore(String nome, String cognome, Date data_nascita,
			String email, String password, boolean ismaschio, String telefono, String indirizzo, String citta, String provincia) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.email = email;
		this.password = password;
		this.ismaschio = ismaschio;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.provincia = provincia;
	}
	
	/**
	 * Costruttore non completo
	 * 
	 * @param nome
	 * @param cognome
	 * @param data_nascita
	 * @param indirizzo
	 * @param telefono
	 * @param email funzionante anche come nome utente per il login
	 * @param password
	 * @param tessera
	 * @param scadenza_tessera
	 * @param classifica contenente il punteggio della classifica attuale
	 * @param circolo
	 */
	public Giocatore(String nome, String cognome, Date data_nascita,
			String indirizzo, String telefono, String email, String password,
			String tessera, Date scadenza_tessera, String classifica,
			String circolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.tessera = tessera;
		this.scadenza_tessera = scadenza_tessera;
		this.classifica = classifica;
		this.circolo = circolo;
	}
	
	/**
	 * Costruttore non completo per modifica profilo
	 * 
	 * @param nome
	 * @param cognome
	 * @param data_nascita
	 * @param indirizzo
	 * @param emali funzionante anche come nome utente per il login
	 * @param password
	 * @param telefono
	 * @param citta
	 * @param provincia

	 */
	public Giocatore(String email, String nome, String cognome, Date data_nascita,
			String telefono, String indirizzo, String citta, String provincia, String password) {
		super();
		this.email= email;
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.indirizzo = indirizzo;
		this.citta= citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.password = password;
		
	}

	
	/**
	 * Costruttore completo
	 * 
	 * @param nome
	 * @param cognome
	 * @param data_nascita
	 * @param indirizzo
	 * @param citta
	 * @param provincia
	 * @param telefono
	 * @param email funzionante anche come nome utente per il login
	 * @param password
	 * @param tessera
	 * @param scadenza_tessera
	 * @param classifica contenente il punteggio della classifica attuale
	 * @param circolo
	 * @param classifica_dinamica
	 * @param atp_wta
	 * @param itf
	 * @param ismaschio
	 * @param punti_aggiuntivi
	 */
	public Giocatore(String nome, String cognome, Date data_nascita,
			String indirizzo, String citta, String provincia, String telefono, String email, String password,
			String tessera, Date scadenza_tessera, String classifica,
			String circolo, String classifica_dinamica, int atp_wta, int itf, boolean ismaschio, int punti_aggiuntivi) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.tessera = tessera;
		this.scadenza_tessera = scadenza_tessera;
		this.classifica = classifica;
		this.circolo = circolo;
		this.classifica_dinamica = classifica_dinamica;
		this.atp_wta = atp_wta;
		this.itf = itf;
		this.ismaschio = ismaschio;
		this.punti_aggiuntivi = punti_aggiuntivi;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @return data_nascita
	 */
	public Date getData_nascita() {
		return data_nascita;
	}
	/**
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	/**
	 * @return citta
	 */
	public String getCitta() {
		return citta;
	}
	/**
	 * @return provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return tessera
	 */
	public String getTessera() {
		return tessera;
	}
	/**
	 * @return scadenza_tessere
	 */
	public Date getScadenza_tessera() {
		return scadenza_tessera;
	}
	/**
	 * @return classifica
	 */
	public String getClassifica() {
		return classifica;
	}
	/**
	 * @return circolo
	 */
	public String getCircolo() {
		return circolo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @param tessera the tessera to set
	 */
	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	/**
	 * @param scadenza_tessere the scadenza_tessere to set
	 */
	public void setScadenza_tessera(Date scadenza_tessera) {
		this.scadenza_tessera = scadenza_tessera;
	}

	/**
	 * @param classifica the classifica to set
	 */
	public void setClassifica(String classifica) {
		this.classifica = classifica;
	}

	/**
	 * @param circolo the circolo to set
	 */
	public void setCircolo(String circolo) {
		this.circolo = circolo;
	}
	
	/**
	 * @param classifica_dinamica
	 */
	public void setClassificaDinamica(String classifica_dinamica) {
		this.classifica_dinamica = classifica_dinamica;
	}

	/**
	 * @param atp_wta
	 */
	public void setAtpWta(int atp_wta) {
		this.atp_wta = atp_wta;
	}
	
	/**
	 * @param itf
	 */
	public void setItf(int itf) {
		this.itf = itf;
	}

	/**
	 * @return classifica_dinamica
	 */
	public String getClassificaDinamica() {
		return classifica_dinamica;
	}
	
	/**
	 * @return atp_wta
	 */
	public int getAtpWta() {
		return atp_wta;
	}
	
	/**
	 * @return itf
	 */
	public int getItf() {
		return itf;
	}

	/**
	 * @return ismaschio
	 */
	public boolean getIsMaschio() {
		return ismaschio;
	}
	
	/**
	 * @return punti_aggiuntivi
	 */
	public int getPuntiAggiuntivi() {
		return punti_aggiuntivi;
	}
	
	/**
	 * @param punti_aggiuntivi
	 */
	public void setPuntiAggiuntivi(int punti_aggiuntivi) {
		this.punti_aggiuntivi = punti_aggiuntivi;
	}
	
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Controlla se la tessera è scaduta.
	 * 
	 * @return True se la tessera è scaduta.
	 */
	public boolean getIsTesseraScaduta(){
		java.util.Date now = new java.util.Date();
		return now.after(scadenza_tessera);
	}

}
