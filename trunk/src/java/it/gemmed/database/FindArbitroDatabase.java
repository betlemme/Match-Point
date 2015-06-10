package it.gemmed.database;

import it.gemmed.resource.Arbitro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo arbitro
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindArbitroDatabase {
	/**
	 * Query usata per cercare l'arbitro tramite email
	 */
	private static final String STATEMENT_EMAIL = "SELECT * FROM arbitro WHERE email=?";
	
	/**
	 * Query usata per avere la lista di tutti gli arbitri
	 */
	private static final String SELECTALL = "SELECT * FROM arbitro";
	
	/**
	 * Lista degli arbitri con tessera scaduta
	 */
	private static final String SELECT_NOTESSERA = "SELECT * FROM arbitro WHERE scadenza_tessera < CURRENT_DATE";
	
	

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 *  Il risultato della ricerca
	 */
	private final List<Arbitro> arbitri;
	

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            la connessione con il database
	 */
	public FindArbitroDatabase(final Connection con) {
		this.con = con;
		this.arbitri = new ArrayList<Arbitro>();
	}

	/**
	 * Cerca tutti gli arbitri
	 * 
	 * @return Una lista di tutti gli arbitri
	 * @throws SQLException
	 */
	public List<Arbitro> findArbitro() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECTALL);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					arbitri.add(new Arbitro(rs.getString("nome"), rs.getString("cognome"), rs.getDate("data_nascita"),
							rs.getString("indirizzo"), rs.getString("telefono"),
							rs.getString("email"),rs.getString("password"), rs.getString("tessera"),
							rs.getDate("scadenza_tessera"), rs.getString("citta"), rs.getString("provincia")));
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
		return arbitri;

	}	
	
	/**
	 * Ricerca il singolo arbitro
	 * 
	 * @param email dell'arbitro
	 * @return
	 * @throws SQLException
	 */
	public Arbitro findArbitro(String email) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(STATEMENT_EMAIL);
				pstmt.setString(1, email);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					arbitri.add(new Arbitro(rs.getString("nome"), rs.getString("cognome"), rs.getDate("data_nascita"),
							rs.getString("indirizzo"), rs.getString("telefono"),
							rs.getString("email"),rs.getString("password"), rs.getString("tessera"),
							rs.getDate("scadenza_tessera"), rs.getString("citta"), rs.getString("provincia")));
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
		if(arbitri.size()>0)
			return arbitri.get(0);
		else
			return null;

	}	

	
	/**
	 * Cerca tutti gli arbitri con tessera scaduta
	 * 
	 * @return Una lista di arbitri 
	 * @throws SQLException
	 */
	public List<Arbitro> findArbitroScaduto() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_NOTESSERA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					arbitri.add(new Arbitro(rs.getString("nome"), rs.getString("cognome"), rs.getDate("data_nascita"),
							rs.getString("indirizzo"), rs.getString("telefono"),
							rs.getString("email"),rs.getString("password"), rs.getString("tessera"),
							rs.getDate("scadenza_tessera"), rs.getString("citta"), rs.getString("provincia")));
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
		return arbitri;

	}	
	
	
}
