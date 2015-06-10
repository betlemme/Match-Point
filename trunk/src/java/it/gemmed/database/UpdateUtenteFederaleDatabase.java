package it.gemmed.database;

import it.gemmed.resource.UtenteFederale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Fa la modifica della password di un utente federale
 * 
 * @author GEMMED
 * @version 0.1
 */

public class UpdateUtenteFederaleDatabase {
	/**
	 * Statement SQL per la modifica della password
	 */
	private static final String STATEMENT = "UPDATE federazione SET  password= ?  WHERE email = ?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo utentefederale che dese essere aggiunto al database
	 */
	private final UtenteFederale f;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo utentefederale nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param f
	 *            utentefederale da salvare nel database
	 */
	public UpdateUtenteFederaleDatabase(final Connection con, final UtenteFederale f) {
		this.con = con;
		this.f = f;
	}

	/**
	 * Savataggio di elementi di tipo utentefederale modificati nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateUtenteFederale() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, f.getPassword());
			pstmt.setString(2, f.getEmail());
			

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}