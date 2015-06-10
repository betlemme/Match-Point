package it.gemmed.database;

import it.gemmed.resource.Ppc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per le ricerche nel database di elementi di tipo partita
 * per il calcolo della classifica
 * 
 * @author GEMMED
 * @version 0.1
 */

public class FindPpcDatabase {
	/**
	 * Query che trova tutte le partite vinte nell'ultimo anno come sfidanteA 
	 */
	private static final String STATEMENTWINA = "SELECT sfb.classifica, forfait, riduzione FROM partita_singola as p INNER JOIN giocatore AS sfa ON p.sfidantea = sfa.email INNER JOIN giocatore AS sfb ON p.sfidanteb = sfb.email INNER JOIN torneo AS t ON p.torneo = t.id WHERE (sfidantea = ? AND vittoriasfidantea = TRUE) AND ( CURRENT_DATE - data <= 366 )"; 

	/**
	 * Query che trova tutte le partite vinte nell'ultimo anno come sfidanteB
	 */
	private static final String STATEMENTWINB = "SELECT sfa.classifica, forfait, riduzione FROM partita_singola as p INNER JOIN giocatore AS sfa ON p.sfidantea = sfa.email INNER JOIN giocatore AS sfb ON p.sfidanteb = sfb.email INNER JOIN torneo AS t ON p.torneo = t.id WHERE (sfidanteb = ? AND vittoriasfidantea = FALSE) AND ( CURRENT_DATE - data <= 366 )";
	
	/**
	 * Query che trova tutte le partite perse nell'ultimo anno come sfidanteA
	 */
	private static final String STATEMENTLOSTA = "SELECT sfb.classifica, forfait, riduzione FROM partita_singola as p INNER JOIN giocatore AS sfa ON p.sfidantea = sfa.email INNER JOIN giocatore AS sfb ON p.sfidanteb = sfb.email INNER JOIN torneo AS t ON p.torneo = t.id WHERE (sfidantea = ? AND vittoriasfidantea = FALSE) AND ( CURRENT_DATE - data <= 366 )";
	
	/**
	 * Query che trova tutte le partite perse nell'ultimo anno come sfidanteB
	 */
	private static final String STATEMENTLOSTB = "SELECT sfa.classifica, forfait, riduzione FROM partita_singola as p INNER JOIN giocatore AS sfa ON p.sfidantea = sfa.email INNER JOIN giocatore AS sfb ON p.sfidanteb = sfb.email INNER JOIN torneo AS t ON p.torneo = t.id WHERE (sfidanteb = ? AND vittoriasfidantea = TRUE) AND ( CURRENT_DATE - data <= 366 )";
	

	/**
	 * La connessione al database
	 */
	private final Connection con;

	/**
	 * Risultato della ricerca
	 */
	private final List<Ppc> lista;
	

	/**
	 * Crea un nuovo oggetto per ricercare informazioni nel dababase.
	 * 
	 * @param con la connessione al database
	 *           
	 */
	public FindPpcDatabase(final Connection con) {
		this.con = con;
		this.lista = new ArrayList<Ppc>();
	}

	/**
	 * Cerca tutte le partite vinte da un giocatore nell'ultimo anno
	 * 
	 * @param giocatore l'email del giocatore
	 * @return Una lista con le partite vinte nell'ultimo anno 
	 * @throws SQLException
	 *             
	 */
	public List<Ppc> findPWin(String giocatore) throws SQLException {

		PreparedStatement pstmta = null;
		ResultSet rsa = null;

		PreparedStatement pstmtb = null;
		ResultSet rsb = null;
		try {
				pstmta = con.prepareStatement(STATEMENTWINA);
				pstmta.setString(1,giocatore);
				pstmta.setString(2,giocatore);

				rsa = pstmta.executeQuery();
				while (rsa.next()) {
					lista.add(new Ppc( rsa.getString("classifica"), rsa.getBoolean("forfait"), rsa.getInt("riduzione")));
				}

				pstmtb = con.prepareStatement(STATEMENTWINB);
				pstmtb.setString(1,giocatore);
				pstmtb.setString(2,giocatore);

				rsb = pstmtb.executeQuery();
				while (rsb.next()) {
					lista.add(new Ppc( rsb.getString("classifica"), rsb.getBoolean("forfait"), rsb.getInt("riduzione")));
				}
				
		} finally {
			if (rsa != null) {
				rsa.close();
			}
			
			if (rsb != null) {
				rsb.close();
			}
			
			if (pstmta != null) {
				pstmta.close();
			}

			if (pstmtb != null) {
				pstmtb.close();
			}
			
			con.close();
		}
		return lista;

	}	
	
	
	/**
	 * Cerca tutte le partite perse da un giocatore nell'ultimo anno
	 * 
	 * @param giocatore l'email del giocatore
	 * @return list Una lista con le partite perse nell'ultimo anno 
	 * @throws SQLException
	 *             
	 */
	public List<Ppc> findPLost(String giocatore) throws SQLException {

		PreparedStatement pstmta = null;
		ResultSet rsa = null;

		PreparedStatement pstmtb = null;
		ResultSet rsb = null;
		try {
				pstmta = con.prepareStatement(STATEMENTLOSTA);
				pstmta.setString(1,giocatore);
				pstmta.setString(2,giocatore);

				rsa = pstmta.executeQuery();
				while (rsa.next()) {
					lista.add(new Ppc( rsa.getString("classifica"), rsa.getBoolean("forfait"), rsa.getInt("riduzione")));
				}

				pstmtb = con.prepareStatement(STATEMENTLOSTB);
				pstmtb.setString(1,giocatore);
				pstmtb.setString(2,giocatore);

				rsb = pstmtb.executeQuery();
				while (rsb.next()) {
					lista.add(new Ppc( rsb.getString("classifica"), rsb.getBoolean("forfait"), rsb.getInt("riduzione")));
				}
				
		} finally {
			if (rsa != null) {
				rsa.close();
			}
			
			if (rsb != null) {
				rsb.close();
			}
			
			if (pstmta != null) {
				pstmta.close();
			}

			if (pstmtb != null) {
				pstmtb.close();
			}
			
			con.close();
		}
		return lista;

	}	
	
	
}
