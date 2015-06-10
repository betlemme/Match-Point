package it.gemmed.database;

import it.gemmed.resource.Iscrizione;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Fa l'update di una partita, richiede il passaggio della partita modificata al costruttore.
 * 
 * @author GEMMED
 * @version 0.1
 */

public class UpdateIscrizioneDatabase {
	/**
	 * Statement SQL per la modifica della disponibilità
	 */
	private static final String STATEMENT = "UPDATE iscrizione SET disponibilita= ? WHERE giocatore = ? AND torneo = ?";
	
	/**
	 * Statement SQL per l'eliminazione di una iscrizione
	 */
	private static final String STATEMENTDEL = "DELETE FROM iscrizione WHERE giocatore = ? AND torneo = ?";
	
	

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo iscrizione che dese essere aggiunto al database
	 */
	private final Iscrizione i;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo iscrizione nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param i
	 *            iscrizione da salvare nel database
	 */
	public UpdateIscrizioneDatabase(final Connection con, final Iscrizione i) {
		this.con = con;
		this.i = i;
	}

	/**
	 * aggiorna l'iscrizione di un giocatore per un torneo
	 * 
	 * @throws SQLException
	 *             
	 */
	public void updateIscrizione() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			
			Array sql = con.createArrayOf("boolean",  i.getDisponibilita());
			
			pstmt.setArray(1, sql);
			pstmt.setString(2, i.getGiocatore());
			pstmt.setInt(3, i.getTorneo());
			

			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	
	
	/**
	 * elimina l'iscrizione di un giocatore per un torneo
	 * 
	 * @throws SQLException
	 *             
	 */
	public void deleteIscrizione() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTDEL);
			
			pstmt.setString(1, i.getGiocatore());
			pstmt.setInt(2, i.getTorneo());
			

			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

	

}