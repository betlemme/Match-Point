package it.gemmed.database;

import it.gemmed.resource.Circolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea un nuovo circolo all'interno del database.
 * 
 * @author GEMMED
 * @version 0.1
 */

public class CreateCircoloDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO circolo (nome, telefono, indirizzo, citta, provincia, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo circolo che dese essere aggiunto al database
	 */
	private final Circolo circolo;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo circolo nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param a
	 *            circolo da salvare nel database
	 */
	public CreateCircoloDatabase(final Connection con, final Circolo c) {
		this.con = con;
		this.circolo = c;
	}

	/**
	 * Savataggio di elementi di tipo circolo nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createCircolo() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, circolo.getNome());
			pstmt.setString(2, circolo.getTelefono());
			pstmt.setString(3, circolo.getIndirizzo());
			pstmt.setString(4, circolo.getCitta());
			pstmt.setString(5, circolo.getProvincia());
			pstmt.setString(6, circolo.getEmail());
			pstmt.setString(7, circolo.getPassword());


			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}
