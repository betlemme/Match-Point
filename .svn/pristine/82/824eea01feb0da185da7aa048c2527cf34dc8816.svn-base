package it.gemmed.database;

import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Risultato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.SizeLimitExceededException;

/**
 * Classe usata per le ricerche nel database di elementi di tipo partita
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindPartitaDatabase {
	/**
	 * Query per cercare le partite di un torneo
	 */
	private static final String SELECT_PARTITATORNEO = "SELECT * FROM partita_singola WHERE torneo=? ORDER BY numero_partita ASC";
	
	/**
	 * Query per cercare i dati di una partita 
	 */
	private static final String SELECT_PARTITATORNEO2 = "SELECT * FROM partita_singola WHERE torneo=? AND numero_partita=?";
	
	/**
	 * Query per cercare tutte le partite effettuato da un giocatore
	 */
	private static final String SELECT_PARTITADISPUTATA = "SELECT * FROM partita_singola WHERE (vittoriasfidantea is not NULL) AND ( sfidantea = ? OR sfidanteb = ?) ";
	
	/**
	 * Query per cercare tutte le partite da disputare per un giocatore 
	 */
	private static final String SELECT_PARTITADISPUTARE = "SELECT * FROM partita_singola WHERE (vittoriasfidantea is NULL) AND ( sfidantea = ? OR sfidanteb = ?) ";
	
	/**
	 * Query per le ultime partite in home page
	 */
	private static final String SELECT_PROSSIMEPARTITE = "SELECT * FROM partita_singola AS p INNER JOIN torneo AS t ON p.torneo = t.id WHERE (vittoriasfidantea is NULL) AND (data >= CURRENT_DATE) ORDER BY data ASC, ora ASC ";
	
	
	/**
	 * Query per cercare tutte le partite vinte da un giocatore 
	 */
	private static final String SELECT_PARTITAVGIOCATORE = "SELECT * FROM partita_singola WHERE (sfidantea = ? AND vittoriasfidantea = TRUE) OR (sfidanteb = ? AND vittoriasfidantea = FALSE) ";
	
	/**
	 * Query per cercare tutte le partite perse da un giocatore 
	 */
	private static final String SELECT_PARTITAPGIOCATORE = "SELECT * FROM partita_singola WHERE (sfidantea = ? AND vittoriasfidantea = FALSE) OR (sfidanteb = ? AND vittoriasfidantea = TRUE)";
	
	
	/**
	 * Query per cercare le partite vinte da un giocatore nell'ultimo anno solare 
	 */
	private static final String SELECT_PARTITAVGIOCATORECLASS = "SELECT * FROM partita_singola WHERE ((sfidantea = ? AND vittoriasfidantea = TRUE) OR (sfidanteb = ? AND vittoriasfidantea = FALSE)) AND ( CURRENT_DATE - data <= 366 )";
	
	/**
	 * Query per cercare le partite perse da un giocatore nell'ultimo anno solare
	 */
	private static final String SELECT_PARTITAPGIOCATORECLASS = "SELECT * FROM partita_singola WHERE ((sfidantea = ? AND vittoriasfidantea = FALSE) OR (sfidanteb = ? AND vittoriasfidantea = TRUE)) AND ( CURRENT_DATE - data <= 366 )";
	
	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Risultati della ricerca.
	 */
	private final List<PartitaSingola> partite;
	
	/**
	 * Risultati della ricerca.
	 */
	private PartitaSingola partita;	

	/**
	 * Creazione di un nuovo oggetto da salvare nel database.
	 * 
	 * @param con
	 *            connessione al database.
	 */
	public FindPartitaDatabase(final Connection con) {
		this.con = con;
		this.partite = new ArrayList<PartitaSingola>();
	}
	
	/**
	 * Ricerca tutte le partite di un torneo
	 * 
	 * @param torneo
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartite(int torneo) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITATORNEO);
				pstmt.setInt(1, torneo);
	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));
					
					partite.add(tmp);
					
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
		return partite;

	}
	
	
	/**
	 * Ricerca partita usando numero_partita
	 * 
	 * @param numero_partita
	 * @return partita
	 * @throws SQLException
	 */
	public PartitaSingola findPartita(int torneo, int numero_partita) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITATORNEO2);
				pstmt.setInt(1, torneo);
				pstmt.setInt(2, numero_partita);
				
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					partita = new PartitaSingola(rs.getInt("numero_partita"), rs.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					partita.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					partita.setSet1(Risultato.fromString(rs.getString("set1")));
					partita.setSet2(Risultato.fromString(rs.getString("set2")));
					partita.setSet3(Risultato.fromString(rs.getString("set3")));
					partita.setCampo(rs.getInt("campo"));
					partita.setCircolo(rs.getString("circolo"));
					partita.setData(rs.getDate("data"));
					partita.setOra(rs.getTime("ora"));
					partita.setForfait(rs.getBoolean("forfait"));

					
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
		return partita;

	}
	
	
	/**
	 * Ricerca tutte le partite disputate da un giocatore
	 * 
	 * @param giocatore
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaGiocatore(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITADISPUTATA);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

				
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
	
	
	/**
	 * Ricerca tutte le partite che un giocatore deve disputare
	 * 
	 * @param giocatore
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaDisputare(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITADISPUTARE);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
	
	
	/**
	 * Ricerca tutte le partite vinte da un giocatore
	 * 
	 * @param giocatorea
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaVGiocatore(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITAVGIOCATORE);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
	
	/**
	 * Ricerca le partite vinte da un giocatore nell'ultimo anno solare
	 * 
	 * @param giocatorea
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaVGiocatoreClass(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITAVGIOCATORECLASS);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
	
	/**
	 * Ricerca tutte le partite perse da un giocatore
	 * 
	 * @param giocatorea
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaPGiocatore(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITAPGIOCATORE);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
	
	/**
	 * Ricerca tutte le partite perse da un giocatore
	 * 
	 * @param giocatorea
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findPartitaPGiocatoreClass(String giocatore) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PARTITAPGIOCATORECLASS);
				pstmt.setString(1, giocatore);
				pstmt.setString(2, giocatore);

	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}

	/**
	 * Ricerca le prossime partite per la home page
	 * 
	 * @param giocatorea
	 * @return list Una lista di partite
	 * @throws SQLException
	 */
	public List<PartitaSingola> findProssimePartite() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				pstmt = con.prepareStatement(SELECT_PROSSIMEPARTITE);

	
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
					PartitaSingola tmp = new PartitaSingola(rs.getInt("numero_partita"), rs
							.getInt("torneo"), rs.getString("sfidantea"), rs.getString("sfidanteb"));
					Risultato set1 = Risultato.fromString(rs.getString("set1"));
					Risultato set2 = Risultato.fromString(rs.getString("set2"));
					Risultato set3 = Risultato.fromString(rs.getString("set3"));
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setSet1(set1);
					tmp.setSet2(set2);
					tmp.setSet3(set3);
					tmp.setCampo(rs.getInt("campo"));
					tmp.setCircolo(rs.getString("circolo"));
					tmp.setData(rs.getDate("data"));
					tmp.setOra(rs.getTime("ora"));
					tmp.setvittoriaSfidanteA(rs.getBoolean("vittoriasfidantea"));
					tmp.setNomeTorneo(rs.getString("nome"));
					tmp.setForfait(rs.getBoolean("forfait"));

					
					partite.add(tmp);
					
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
		return partite;

	}
}