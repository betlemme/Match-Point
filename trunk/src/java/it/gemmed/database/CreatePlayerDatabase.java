package it.gemmed.database;

import it.gemmed.resource.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea un nuovo giocatore all'interno del database. Sono richiesti solo i campi
 * not NULL, per settare gli altri campi utilizzare la classe UpdateGiocatoreDatabase
 * 
 * @author GEMMED
 * @version 0.1
 */

public class CreatePlayerDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO giocatore (nome, cognome, data_nascita, email, password, ismaschio, telefono, indirizzo, citta, provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo arbitro che dese essere aggiunto al database
	 */
	private final Giocatore giocatore;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo giocatore nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param player
	 *            giocatore da salvare nel database
	 */
	public CreatePlayerDatabase(final Connection con, final Giocatore player) {
		this.con = con;
		this.giocatore = player;
	}

	/**
	 * Savataggio di elementi di tipo giocatore nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createEmployee() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, giocatore.getNome());
			pstmt.setString(2, giocatore.getCognome());
			pstmt.setDate(3, giocatore.getData_nascita());
			pstmt.setString(4, giocatore.getEmail());
			pstmt.setString(5, giocatore.getPassword());
			pstmt.setBoolean(6, giocatore.getIsMaschio());
			pstmt.setString(7, giocatore.getTelefono());
			pstmt.setString(8, giocatore.getIndirizzo());
			pstmt.setString(9, giocatore.getCitta());
			pstmt.setString(10, giocatore.getProvincia());





			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}
