package it.gemmed.database;

import it.gemmed.resource.Campo;
import it.gemmed.resource.Tiposuperficie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo campo
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindCampoDatabase {
	/**
	 * Query che seleziona tutti i campi di un dato circolo
	 */
	private static final String STATEMENT = "SELECT numero, superficie, circolo FROM campo WHERE circolo=?";
	
	
	/**
	 * Query che seleziona tutti i campi di un dato circolo
	 */
	private static final String STATEMENT2 = "INSERT INTO campo VALUES (?, ?::tiposup, ?)";
	

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultato della ricerca
	 */
	private final List<Campo> campi;
	
	/**
	 * la chiave primaria di campo
	 */
	private String email;

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            connessione al database
	 */
	public FindCampoDatabase(final Connection con) {
		this.con = con;
		this.campi = new ArrayList<Campo>();
	}

	/**
	 * Seleziona tutti i campi di un dato circolo
	 * 
	 * @return campi lista di campi
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the employee.
	 */
	public List<Campo> findCampi(String email_circolo) throws SQLException {
		
		email=email_circolo;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				campi.add(new Campo(rs.getInt("numero"), Tiposuperficie.valueOf(rs.getString("superficie")), rs.getString("circolo")));
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
		return campi;

	}	
	
	
	/**
	 * Crea un nuoco campo
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the employee.
	 */
	public void createCampo(Campo campo) throws SQLException {
		
		email = campo.getCircolo();
		int numero = campo.getNumero();
		String superficie = campo.getSuperficie().name();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(STATEMENT2);
			pstmt.setInt(1, numero);
			pstmt.setString(2, superficie);
			pstmt.setString(3, email);

			pstmt.execute();

		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	


}
