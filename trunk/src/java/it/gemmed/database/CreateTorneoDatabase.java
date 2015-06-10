package it.gemmed.database;

import it.gemmed.resource.Torneo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Crea un nuovo torneo all'interno del database. Sono richiesti solo i campi
 * not NULL, per settare gli altri campi utilizzare la classe UpdateTorneoDatabase
 * 
 * @author GEMMED
 * @version 0.1
 */

public class CreateTorneoDatabase {
	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO torneo (nome, data_inizio, data_fine, circolo, tipologia, convalidato, iscrizione_inizio, iscrizione_fine) VALUES (?, ?, ?, ?, ?::tiposcontro, ?::convalida, ?, ?)";

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
	public CreateTorneoDatabase(final Connection con, final Torneo t) {
		this.con = con;
		this.torneo = t;
	}

	/**
	 * Savataggio di elementi di tipo torneo nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void createTorneo() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, torneo.getNome());
			pstmt.setDate(2, torneo.getData_inizio());
			pstmt.setDate(3, torneo.getData_fine());
			pstmt.setString(4, torneo.getCircolo());
			pstmt.setString(5, torneo.getTipologia());
			pstmt.setString(6, torneo.getConvalidato());
			pstmt.setDate(7, torneo.getIscrizioneInizio());
			pstmt.setDate(8, torneo.getIscrizioneFine());

			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			con.close();
		}

	}	

}