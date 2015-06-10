package it.gemmed.database;

import it.gemmed.resource.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Fa l'update di un giocatore, richiede il passaggio del giocatore modificato al costruttore.
 * 
 * @author GEMMED
 * @version 0.1
 */

public class UpdateGiocatoreDatabase {
	/**
	 * Statement SQL per la modifica
	 */
	private static final String STATEMENT = "UPDATE giocatore SET nome= ?, cognome=?, data_nascita= ?, indirizzo= ?, telefono= ?, email= ?, password= ?, tessera= ?, scadenza_tessera= ?, classifica=?, circolo=?, atp_wta=?, itf=?, classifica_dinamica=?, citta = ?, provincia = ? WHERE email= ?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo arbitro che dese essere aggiunto al database
	 */
	private final Giocatore g;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo giocatore nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param player
	 *            giocatore da salvare nel database
	 */
	public UpdateGiocatoreDatabase(final Connection con, final Giocatore player) {
		this.con = con;
		this.g = player;
	}

	/**
	 * Savataggio delle modifiche di elementi di tipo giocatore nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateGiocatore() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, g.getNome());
			pstmt.setString(2, g.getCognome());
			pstmt.setDate(3, g.getData_nascita());
			pstmt.setString(4, g.getIndirizzo());
			pstmt.setString(5, g.getTelefono());
			pstmt.setString(6, g.getEmail());
			pstmt.setString(7, g.getPassword());
			pstmt.setString(8, g.getTessera());
			pstmt.setDate(9, g.getScadenza_tessera());
			pstmt.setString(10, g.getClassifica());
			pstmt.setString(11, g.getCircolo());
			pstmt.setInt(12, g.getAtpWta());
			pstmt.setInt(13, g.getItf());
			pstmt.setString(14, g.getClassificaDinamica());
			pstmt.setString(15, g.getCitta());
			pstmt.setString(16, g.getProvincia());
			
			pstmt.setString(17, g.getEmail());
			


			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}