package it.gemmed.database;

import it.gemmed.resource.Torneo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo torneo
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindTorneoDatabase {
	/**
	 * Query usata per cercare il torneo tramite circolo
	 */
	private static final String STATEMENT = "SELECT * FROM torneo WHERE circolo=?";
	
	/**
	 * Query usata per cercare il torneo tramite id
	 */
	private static final String STATEMENT_ID = "SELECT * FROM torneo WHERE id=?";
		
	/**
	 * Query usata per cercare il torneo tramite id con le informazioni geografiche
	 */
	private static final String STATEMENT_ID_INFO = "SELECT * FROM torneo JOIN circolo ON torneo.circolo = circolo.email WHERE id=?";
	
	/**
	 * Query usata per avere la lista di tutti i tornei
	 */
	private static final String SELECTALL = "SELECT * FROM torneo";

	/**
	 * Query usata per avere la lista dei tornei approvati
	 */
	private static final String SELECTAPP = "SELECT * FROM torneo WHERE convalidato = 'si'";
	
	/**
	 * Query usata per avere la lista dei tornei non approvati
	 */
	private static final String SELECTNOAPP = "SELECT * FROM torneo WHERE convalidato = 'no'";
	
	/**
	 * Query usata per avere la lista dei tornei associati a un arbitro
	 */
	private static final String SELECT_ARBITRO = "SELECT * FROM torneo WHERE arbitro = ?";	

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultati della ricerca
	 */
	private final List<Torneo> tornei;
	

	private String circolo;

	/**
	 * Crea un nuovo oggetto per ricercare informazioni nel dababase.
	 * 
	 * @param con la connessione al database
	 *           
	 */
	public FindTorneoDatabase(final Connection con) {
		this.con = con;
		this.tornei = new ArrayList<Torneo>();
	}

	/**
	 * Cerca tutti i tornei
	 * 
	 * @param circolo Circolo a cui appartiene il torneo (ignorato se selectALL è a true)
	 * @return list Una lista con i tornei
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneo() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECTALL);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione")
							));
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
		return tornei;

	}	
	
	/**
	 * Ricerca tutti i tornei di un circolo
	 * 
	 * @param circolo
	 * @throws SQLException
	 */
	public List<Torneo> findTorneo(String circolo) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(STATEMENT);
				pstmt.setString(1, circolo);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione")
							));
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
		return tornei;

	}	
	
	/**
	 * Ricerca di un torneo con un dato id
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public Torneo findTorneo(int id) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(STATEMENT_ID);
				pstmt.setInt(1, id);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione")
							));
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
		if(tornei.size()>0)
			return tornei.get(0);
		else
			return null;

	}	
	
	/**
	 * Ritorna tutte le info geografiche di un torneo con dato id
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public Torneo findTorneoConInfo(int id) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(STATEMENT_ID_INFO);
				pstmt.setInt(1, id);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione"), rs.getString("indirizzo"), 
							rs.getString("citta"), rs.getString("provincia"), rs.getString("telefono") 
							));
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
		if(tornei.size()>0)
			return tornei.get(0);
		else
			return null;

	}
	
	/**
	 * Cerca tutti i tornei approvati
	 * 
	 * @param approvato true se il toeneo è approvato
	 * @return list Una lista con i tornei approvati
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneo(boolean approvato) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (approvato == true){
		try {
				pstmt = con.prepareStatement(SELECTAPP);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione")
							));
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
		}
		return tornei;
		
	}
	
	/**
	 * Ricerca tutti i tornei a cui è associato un dato arbitro
	 * @param circolo
	 * @throws SQLException
	 */
	public List<Torneo> findTorneoPerArbitro(String arbitro) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_ARBITRO);
				pstmt.setString(1, arbitro);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs.getDate("data_inizio"), rs.getDate("data_fine"), 
							rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"), 
							rs.getDate("iscrizione_fine"), rs.getInt("riduzione")
							));
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
		return tornei;

	}		
	
}
