package it.gemmed.database;
import it.gemmed.resource.Arbitro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea un nuovo arbitro all'interno del database. Sono richiesti solo i campi
 * not NULL, per settare gli altri campi utilizzare la classe UpdateArbitroDatabase
 * 
 * @author GEMMED
 * @version 0.1
 */

public class CreateArbitroDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO arbitro (nome, cognome, data_nascita, indirizzo, telefono, email, password, tessera, scadenza_tessera, citta, provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo arbitro che dese essere aggiunto al database
	 */
	private final Arbitro arbitro;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo arbitro nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param a
	 *            arbitro da salvare nel database
	 */
	public CreateArbitroDatabase(final Connection con, final Arbitro a) {
		this.con = con;
		this.arbitro = a;
	}

	/**
	 * Savataggio di elementi di tipo arbitro nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createArbitro() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, arbitro.getNome());
			pstmt.setString(2, arbitro.getCognome());
			pstmt.setDate(3, arbitro.getData_nascita());
			pstmt.setString(4, arbitro.getIndirizzo());
			pstmt.setString(5, arbitro.getTelefono());
			pstmt.setString(6, arbitro.getEmail());
			pstmt.setString(7, arbitro.getPassword());
			pstmt.setString(8, arbitro.getTessera());
			pstmt.setDate(9, arbitro.getScadenza_tessere());
			pstmt.setString(10, arbitro.getCitta());
			pstmt.setString(11, arbitro.getProvincia());

			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}