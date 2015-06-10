package it.gemmed.database;

import it.gemmed.resource.Circolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo circolo
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindCircoloDatabase {
	/**
	 * Query che seleziona il circolo con una data email
	 */
	private static final String STATEMENT = "SELECT * FROM circolo WHERE email=?";
	
	/**
	 * Query che seleziona tutti i dati di tutti i circoli
	 */
	private static final String STATEMENT_ALL = "SELECT * FROM circolo";
	
	/**
	 * Query che seleziona il circolo che ha proposto un dato torneo
	 */
	private static final String STATEMENTTORNEO = "SELECT circolo FROM torneo WHERE id = ?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultato della ricerca.
	 */
	private final List<Circolo> circoli;
	
	private String email;

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            connessione al database
	 */
	public FindCircoloDatabase(final Connection con) {
		this.con = con;
		this.circoli = new ArrayList<Circolo>();
	}

	/**
	 * Seleziona tutti i circoli
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public List<Circolo> findCircolo() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				circoli.add(new Circolo(rs.getString("nome"), rs.getString("indirizzo"),rs.getString("citta"),
						rs.getString("provincia"), rs.getString("telefono"),
						rs.getString("email"), rs.getString("password")));
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
		return circoli;

	}	
	
	/**
	 * Seleziona il circolo con una data email
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public List<Circolo> findCircolo(String email) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				circoli.add(new Circolo(rs.getString("nome"), rs.getString("indirizzo"),rs.getString("citta"),
						rs.getString("provincia"), rs.getString("telefono"),
						rs.getString("email"), rs.getString("password")));
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
		return circoli;

	}	
	
	
	/**
	 * Seleziona tutti il circolo che ha proposto un dato torneo
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public String findCircolo(int torneo) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String circolo = null;

		try {
			pstmt = con.prepareStatement(STATEMENTTORNEO);
			pstmt.setInt(1, torneo);

			rs = pstmt.executeQuery();
			while (rs.next()) {circolo = rs.getString("circolo");}
			

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}
		return circolo;

	}

}
