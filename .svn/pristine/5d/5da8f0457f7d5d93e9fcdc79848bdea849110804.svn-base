package it.gemmed.database;

import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Iscrizione;
import it.gemmed.resource.Torneo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo iscrizione
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindIscrizioneDatabase {
	
	/**
	 * Query che ritorna le iscrizioni di un torneo
	 */
	private static final String SELECT_ISCRIZIONI = "SELECT * FROM iscrizione WHERE torneo = ?";
	
	/**
	 * Query che ritorna la iscrizione di un torneo per un giocatore
	 */
	private static final String SELECT_ISCRIZIONE_GIOCATORE = "SELECT * FROM iscrizione WHERE torneo = ? AND giocatore = ?";

	/**
	 * Query usata per ottenere una lista di tornei attivi in cui un giocatore si trova iscritto 
	 */
	private static final String SELECT_TORNEO_INCORSO = "SELECT nome, data_inizio, data_fine, categoria, circolo, tipologia, arbitro, federazione, convalidato, id, iscrizione_inizio, iscrizione_fine FROM iscrizione as I INNER JOIN torneo AS T ON I.torneo = T.id WHERE giocatore = ? AND  data_inizio < CURRENT_DATE AND CURRENT_DATE < data_fine AND convalidato ='si' ";
	
	/**
	 * Query usata per ottenere una lista di tornei da disputare in cui un giocatore si trova iscritto 
	 */
	private static final String SELECT_TORNEO_DADISPUTARE = "SELECT nome, data_inizio, data_fine, categoria, circolo, tipologia, arbitro, federazione, convalidato, id, iscrizione_inizio, iscrizione_fine FROM iscrizione as I INNER JOIN torneo AS T ON I.torneo = T.id WHERE giocatore = ? AND CURRENT_DATE < data_inizio AND iscrizione_inizio < CURRENT_DATE AND convalidato = 'si' ";
	
	/**
	 * Query usata per ottenere una lista di tornei disponibili in cui un giocatore può iscriversi
	 */
	private static final String SELECT_TORNEO_DISPONIBILI = "SELECT nome, data_inizio, data_fine, categoria, circolo, tipologia, arbitro, federazione, convalidato, id, iscrizione_inizio, iscrizione_fine FROM torneo WHERE convalidato='si' AND iscrizione_inizio < CURRENT_DATE AND CURRENT_DATE < iscrizione_fine  EXCEPT SELECT nome, data_inizio, data_fine, categoria, circolo, tipologia, arbitro, federazione, convalidato, id, iscrizione_inizio, iscrizione_fine FROM iscrizione as I INNER JOIN torneo AS T ON I.torneo = T.id WHERE giocatore = ? AND CURRENT_DATE < iscrizione_fine AND  iscrizione_inizio < CURRENT_DATE AND convalidato = 'si' ";
	
	
	/**
	 * Query usata per ottenere una lista di tornei conclusi in cui il giocatore ha partecipato.
	 */
	private static final String SELECT_TORNEO_CONCLUSO = "SELECT nome, data_inizio, data_fine, categoria, circolo, tipologia, arbitro, federazione, convalidato, id, iscrizione_inizio, iscrizione_fine FROM iscrizione as I INNER JOIN torneo AS T ON I.torneo = T.id WHERE giocatore = ? AND CURRENT_DATE > data_fine AND convalidato ='si' ";

	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultati della ricerca
	 */
	private final List<Torneo> tornei;
	private final List<Iscrizione> isc;

	/**
	 * Crea una nuova connessione per ricercare informazioni nel database.
	 * 
	 * @param con
	 *            connessione al database
	 */
	public FindIscrizioneDatabase(final Connection con) {
		this.con = con;
		this.tornei = new ArrayList<Torneo>();
		this.isc = new ArrayList<Iscrizione>();
	}//FindIscrizioneDatabase
	

	/**
	 * Metodo
	 * 
	 * @param giocatore 
	 * @return list Una lista contenente i tornei attivi in cui un giocatore si trova iscritto
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneoInCorso(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_TORNEO_INCORSO);
				pstmt.setString(1, giocatore);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs
							.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"),
							rs.getDate("iscrizione_fine")
							));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return tornei;
	
	}//findTorneoInCorso

	/**
	 * Metodo
	 * 
	 * @param giocatore 
	 * @return list Una lista contenente i tornei da disputare in cui un giocatore si trova iscritto
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneoDaDisputare(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_TORNEO_DADISPUTARE);
				pstmt.setString(1, giocatore);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs
							.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"),rs.getDate("iscrizione_inizio"),
							rs.getDate("iscrizione_fine")
							));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return tornei;
	
	}//findTorneoDaDisputare
	
	/**
	 * Metodo
	 * 
	 * @param giocatore 
	 * @return list Una lista contenente i tornei disponibili in cui un giocatore può iscriversi
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneoDisponibile(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_TORNEO_DISPONIBILI);
				pstmt.setString(1, giocatore);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs
							.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"),
							rs.getDate("iscrizione_fine")
							));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return tornei;
	
	}//findTorneoDisponibile

	
	/**
	 * Metodo
	 * 
	 * @param giocatore 
	 * @return list Una lista contenente i tornei conclusi in cui il giocatore ha partecipato.
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Torneo> findTorneoConcluso(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_TORNEO_CONCLUSO);
				pstmt.setString(1, giocatore);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tornei.add(new Torneo(rs.getString("nome"), rs
							.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("categoria"), rs.getInt("id"),
							rs.getString("circolo"), rs.getString("tipologia"), rs.getString("arbitro"), 
							rs.getString("federazione"), rs.getString("convalidato"), rs.getDate("iscrizione_inizio"),
							rs.getDate("iscrizione_fine")
							));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return tornei;
	
	}//findTorneoConcluso

	/**
	 * 
	 * @param torneo 
	 * @return list Una lista contenente le iscrizioni.
	 * 
	 * @throws SQLException
	 *             Errore nella ricerca del torneo
	 */
	public List<Iscrizione> findIscrizioni(String id) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_ISCRIZIONI);
				pstmt.setInt(1, Integer.parseInt(id));
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					isc.add(new Iscrizione(rs.getString("giocatore"), (Boolean[])rs.getArray("disponibilita").getArray(), rs
							.getInt("torneo")));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return isc;
	
	}//findTorneoConcluso
	
	public Iscrizione findIscrizioneGiocatore(String giocatore, int id) throws SQLException {

		Iscrizione isc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = con.prepareStatement(SELECT_ISCRIZIONE_GIOCATORE);
				pstmt.setInt(1, id);
				pstmt.setString(2, giocatore);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					isc = new Iscrizione(rs.getString("giocatore"), (Boolean[])rs.getArray("disponibilita").getArray(), rs
							.getInt("torneo"));
				}//while

		} finally {
			if (rs != null) {
				rs.close();
			}//if
			
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
		return isc;
	
	}//findTorneoConcluso
	
}//FindIscrizioneDatabase
 
