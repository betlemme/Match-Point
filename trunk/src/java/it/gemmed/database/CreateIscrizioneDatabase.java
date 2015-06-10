package it.gemmed.database;

import it.gemmed.resource.Iscrizione;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea una nuova iscrizione all'interno del database. Sono richiesti solo i campi
 * not NULL, per settare gli altri campi utilizzare la classe UpdateIscrizioneDatabase
 * 
 * @author GEMMED
 * @version 0.1
 */

public class CreateIscrizioneDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO iscrizione (giocatore,torneo, disponibilita) VALUES (?, ?, ?)";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo iscrizione che dese essere aggiunto al database
	 */
	private final Iscrizione iscrizione;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo iscrizione nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param i
	 *            iscrizione da salvare nel database
	 */
	public CreateIscrizioneDatabase(final Connection con, final Iscrizione i) {
		this.con = con;
		this.iscrizione = i;
	}

	/**
	 * Savataggio di elementi di tipo iscrizione nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createIscrizione() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, iscrizione.getGiocatore());
			pstmt.setInt(2, iscrizione.getTorneo());
			
		//	iscrizione.getDisponibilita().toByteArray();
			Array sql = con.createArrayOf("boolean",  iscrizione.getDisponibilita());
			
			pstmt.setArray(3, sql);

			pstmt.execute();
		
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}
