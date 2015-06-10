package it.gemmed.database;
import it.gemmed.resource.PartitaSingola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea una nuova Partita all'interno del database. 
 * 
 * @author GEMMED
 * @version 0.1
 */
public class CreatePartitaDatabase {

	/**
	 * Statement SQL per l'inserimento senza sfidanti
	 */
	private static final String STATEMENT = "INSERT INTO partita_singola (numero_partita, torneo, circolo) VALUES (?, ?, ?)";
	
	/**
	 * Statement SQL per l'inserimento con sfidanti
	 */
	private static final String STATEMENT2 = "INSERT INTO partita_singola (numero_partita, torneo, sfidantea, sfidanteb, circolo) VALUES (?, ?, ?, ?, ?)";
	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo partita che dese essere aggiunto al database
	 */
	private final PartitaSingola partita;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo partita nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param partita
	 *            partita da salvare nel database
	 */
	public CreatePartitaDatabase(final Connection con, final PartitaSingola partita) {
		this.con = con;
		this.partita = partita;
	}

	/**
	 * Savataggio di elementi di tipo partita senza sifdanti nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createPartita() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, partita.getNumeroPartita());
			pstmt.setInt(2, partita.getTorneo());
			pstmt.setString(3, partita.getCircolo());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}
	
	/**
	 * Savataggio di elementi di tipo partita con sifdanti nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createPartita(String A, String B) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT2);
			pstmt.setInt(1, partita.getNumeroPartita());
			pstmt.setInt(2, partita.getTorneo());
			pstmt.setString(3, partita.getSfidanteA());
			pstmt.setString(4, partita.getSfidanteB());
			pstmt.setString(5, partita.getCircolo());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}
	
}
