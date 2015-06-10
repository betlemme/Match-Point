package it.gemmed.database;

import it.gemmed.resource.Torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Fa l'update di un torneo, richiede il passaggio del torneo modificato al costruttore.
 * 
 * @author GEMMED
 * @version 0.1
 */

public class UpdateTorneoDatabase {
	/**
	 * Statement SQL per la modifica dei dati di un torneo
	 */
	private static final String STATEMENT = "UPDATE torneo SET nome= ?, categoria=?, data_inizio= ?, data_fine= ?, arbitro= ?, federazione= ?, circolo= ?, tipologia= ?::tiposcontro, convalidato= ?::convalida WHERE id= ?";

	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo torneo che dese essere aggiunto al database
	 */
	private final Torneo torneo;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo torneo nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param t
	 *            torneo da salvare nel database
	 */
	public UpdateTorneoDatabase(final Connection con, final Torneo t) {
		this.con = con;
		this.torneo = t;
	}

	/**
	 * Savataggio di elementi di tipo torneo modificati nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateTorneo() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, torneo.getNome());
			pstmt.setString(2, torneo.getCategoria());
			pstmt.setDate(3, torneo.getData_inizio());
			pstmt.setDate(4, torneo.getData_fine());
			pstmt.setString(5, torneo.getArbitro());
			pstmt.setString(6, torneo.getFederazione());
			pstmt.setString(7, torneo.getCircolo());
			pstmt.setString(8, torneo.getTipologia());
			pstmt.setString(9, torneo.getConvalidato());
			pstmt.setInt(10, torneo.getId());
			


			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}