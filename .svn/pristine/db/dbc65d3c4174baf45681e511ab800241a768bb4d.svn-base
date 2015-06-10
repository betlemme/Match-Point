package it.gemmed.database;

import it.gemmed.resource.UtenteFederale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new utente fed into the database. Only the not null fields are required,
 * to set the other fields use the updateplayer class.
 * 
 * @author Manuel Zulian
 * @version 0.1
 */

public class CreateUtenteFederaleDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO federazione (email, password) VALUES (?, ?)";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo utentefederale che dese essere aggiunto al database
	 */
	private final UtenteFederale fed;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo utentefederale nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param f
	 *            utentefederale da salvare nel database
	 */
	public CreateUtenteFederaleDatabase(final Connection con, final UtenteFederale f) {
		this.con = con;
		this.fed = f;
	}

	/**
	 * Savataggio di elementi di tipo utentefederale nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createUtenteFederale() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, fed.getEmail());
			pstmt.setString(2, fed.getPassword());


			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}