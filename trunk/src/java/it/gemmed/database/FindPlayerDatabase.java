package it.gemmed.database;

import it.gemmed.resource.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo giocatore
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindPlayerDatabase {
	/**
	 * Query che ritorna il giocatore con una data email
	 */
	private static final String STATEMENT_EMAIL = "SELECT * FROM giocatore WHERE email=?";
	/**
	 * Query che ritorna tutti i giocatori con un dato nome
	 */
	private static final String STATEMENT_NOME = "SELECT * FROM giocatore WHERE nome=?";
	/**
	 * Query che ritorna il giocatore con una data tessera
	 */
	private static final String STATEMENT_TESSERA = "SELECT * FROM giocatore WHERE tessera=?";
	/**
	 * Query che ritorna tutti i giocatori con un dato cognome
	 */
	private static final String STATEMENT_COGNOME = "SELECT * FROM giocatore WHERE cognome=?";
	/**
	 * Query che ritorna tutti i giocatori
	 */
	private static final String STATEMENT_ALL = "SELECT * FROM giocatore";
	/**
	 * Query che ritorna tutti i giocatori iscritti ad un dato torneo
	 */
	private static final String STATEMENT_TORNEO = "SELECT nome, cognome, data_nascita, indirizzo, telefono, email, password, tessera, scadenza_tessera, classifica, circolo, classifica_dinamica, atp_wta, itf, ismaschio, punti_aggiuntivi, citta, provincia  FROM iscrizione AS I INNER JOIN giocatore AS G ON I.giocatore = G.email WHERE I.torneo = ?";
	
	/**
	 * Query che ritorna tutti i giocatori senza tessera o con tessera scaduta
	 */
	private static final String STATEMENT_NOTESSERA = "SELECT * FROM giocatore WHERE tessera is NULL OR scadenza_tessera < CURRENT_DATE";
	
	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultati della ricerca
	 */
	private final List<Giocatore> giocatori;

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            connessione al database
	 */
	public FindPlayerDatabase(final Connection con) {
		this.con = con;
		this.giocatori = new ArrayList<Giocatore>();
	}

	/**
	 * Cerca tutti i giocatori
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 *             if any error occurs while storing the employee.
	 */
	public List<Giocatore> findPlayer() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}	
	
	/**
	 * Cerca per email
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 */
	public Giocatore findPlayerByEmail(String email) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_EMAIL);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		if(giocatori.size()>0)
			return giocatori.get(0);
		else
			return null;
	}	
	
	/**
	 * Cerca per nome
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 */
	
	public List<Giocatore> findPlayerByNome(String nome) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_NOME);
			pstmt.setString(1, nome);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}	
	
	/**
	 * Cerca per cognome
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 */
	
	public List<Giocatore> findPlayerByCognome(String cognome) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_COGNOME);
			pstmt.setString(1, cognome);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}	
	
	/**
	 * Cerca per numero di tessera
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 */
	
	public List<Giocatore> findPlayerByTessera(String tessera) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_TESSERA);
			pstmt.setString(1, tessera);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}	
	
	/**
	 * Cerca per torneo
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 */
	public List<Giocatore> findPlayerByTorneo(int id) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_TORNEO);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}
	
	/**
	 * Cerca tutti i giocatori senza tessera o con tessera scaduta
	 * 
	 * @return list Una lista di giocatori
	 * @throws SQLException
	 *             if any error occurs while storing the employee.
	 */
	public List<Giocatore> findPlayerNoTessera() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_NOTESSERA);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				giocatori.add(new Giocatore(rs.getString("nome"), rs
						.getString("cognome"), rs.getDate("data_nascita"), rs.getString("indirizzo"),
						rs.getString("citta"), rs.getString("provincia"),
						rs.getString("telefono"),rs.getString("email"), rs.getString("password"),
						rs.getString("tessera"), rs.getDate("scadenza_tessera"), rs.getString("classifica"), rs.getString("circolo"),
						rs.getString("classifica_dinamica"), rs.getInt("atp_wta"),  rs.getInt("itf"), 
						rs.getBoolean("ismaschio"), rs.getInt("punti_aggiuntivi")));
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return giocatori;

	}	
	
	
	

}
