package it.gemmed.database;

import it.gemmed.resource.Arbitro;
import it.gemmed.resource.UtenteFederale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo utentefederale
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindUtenteFederaleDatabase {
	/**
	 * Query che ritorna l'utente federale con data email 
	 */
	private static final String STATEMENT = "SELECT * FROM federazione WHERE email=?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultati della ricerca.
	 */
	private final List<UtenteFederale> utenti;
	

	private String email;

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            connessione al database
	 */
	public FindUtenteFederaleDatabase(final Connection con, String email) {
		this.con = con;
		this.email = email;
		this.utenti = new ArrayList<UtenteFederale>();
	}
	
	public FindUtenteFederaleDatabase(final Connection con) {
		this.con = con;
		this.utenti = new ArrayList<UtenteFederale>();
	}

	/**
	 * Aggiunge un utente federale alla lista di utenti federali
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca dell'utente federale
	 */
	public List<UtenteFederale> findUtente() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				utenti.add(new UtenteFederale(rs.getString("email"), rs.getString("password")));
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
		return utenti;

	}
	/**
	 * Ricerca il singolo arbitro
	 * 
	 * @param email
	 * @throws SQLException
	 */
	public UtenteFederale findUtente(String email) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, email);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					utenti.add(new UtenteFederale(rs.getString("email"), rs.getString("password")));
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
		if(utenti.size()>0)
			return utenti.get(0);
		else
			return null;

	}	

}



