package it.gemmed.database;

import it.gemmed.resource.Arbitro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Fa l'update del profilo di  un arbitro, richiede il passaggio dell'arbitro modificato al costruttore.
 * 
 * @author GEMMED
 * @version 0.1
 */

public class UpdateArbitroDatabase {
	/**
	 * Statement SQL per la modifica
	 */
	private static final String STATEMENT = "UPDATE arbitro SET nome= ?, cognome=?, data_nascita= ?, indirizzo= ?, telefono= ?, tessera= ?, scadenza_tessera= ?,  password= ?, citta = ?, provincia = ?  WHERE email = ?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo arbitro che dese essere aggiunto al database
	 */
	private final Arbitro a;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo arbitro nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param a
	 *            arbitro da salvare nel database
	 */
	public UpdateArbitroDatabase(final Connection con, final Arbitro a) {
		this.con = con;
		this.a = a;
	}

	/**
	 * Modifica di elementi di tipo arbitro nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateArbitro() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, a.getNome());
			pstmt.setString(2, a.getCognome());
			pstmt.setDate(3, a.getData_nascita());
			pstmt.setString(4, a.getIndirizzo());
			pstmt.setString(5, a.getTelefono());
			pstmt.setString(6, a.getTessera());
			pstmt.setDate(7, a.getScadenza_tessere());
			pstmt.setString(8, a.getPassword());
			pstmt.setString(9, a.getCitta());
			pstmt.setString(10, a.getProvincia());
		
			pstmt.setString(11, a.getEmail());

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}