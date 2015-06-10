package it.gemmed.database;

import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Risultato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;


/**
 * Fa l'update di una partita, richiede il passaggio del giocatore modificato al costruttore.
 * 
 * @author GEMMED
 */

public class UpdatePartitaSingolaDatabase {
	/**
	 * Statement SQL per la modifica dei dati di una partita
	 */
	private static final String STATEMENT = "UPDATE partita_singola SET numero_partita= ?, data=?, ora= ?, campo= ?, circolo= ?, sfidantea= ?, sfidanteb= ?, vittoriasfidantea= ?, torneo= ?, set1=?::risultato, set2=?::risultato, set3=?::risultato WHERE numero_partita= ? AND torneo=?";

	/**
	 * Statement SQL per la modifica di un risultato di una partita
	 */
	private static final String STATEMENTRESULT = "UPDATE partita_singola SET vittoriasfidantea = ? WHERE numero_partita= ? AND torneo=?";
	
	/**
	 * Statement SQL per la modifica di un risultato forfait di una partita
	 */
	private static final String STATEMENTRESULT_FORFAIT = "UPDATE partita_singola SET vittoriasfidantea = ?, forfait = ? WHERE numero_partita= ? AND torneo=?";

	/**
	 * Statement SQL per la modifica di un giocatore di una partita
	 */
	private static final String STATEMENTPLAYER = "UPDATE partita_singola SET sfidantea = ?, sfidanteb = ? WHERE numero_partita= ? AND torneo=?";

	/**
	 * Statement SQL per la modifica di giocatoreA di una partita
	 */
	private static final String STATEMENTPLAYERA = "UPDATE partita_singola SET sfidantea = ? WHERE numero_partita= ? AND torneo=?";
	
	/**
	 * Statement SQL per la modifica di giocatoreB di una partita
	 */
	private static final String STATEMENTPLAYERB = "UPDATE partita_singola SET sfidanteb = ? WHERE numero_partita= ? AND torneo=?";
	
	/**
	 * Statement SQL per la modifica dei risultati dei set di una partita
	 */
	private static final String STATEMENTSET = "UPDATE partita_singola SET set1=?::risultato, set2=?::risultato, set3=?::risultato WHERE numero_partita= ? AND torneo=?";
	
	/**
	 * Statement SQL per la modifica della data di una partita
	 */
	private static final String STATEMENTDATE = "UPDATE partita_singola SET data = ? WHERE numero_partita= ? AND torneo=?";
	
	/**
	 * Statement SQL per la modifica dell'ora di una partita
	 */
	private static final String STATEMENTTIME = "UPDATE partita_singola SET ora = ?::time WHERE numero_partita= ? AND torneo=?";

	
	/**
	 * Statement SQL per la modifica del campo di una partita
	 */
	private static final String STATEMENTCAMPO = "UPDATE partita_singola SET campo = ? WHERE numero_partita= ? AND torneo=?";

	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo partitasingola che dese essere aggiunto al database
	 */
	private final PartitaSingola p;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo partitasingola nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param partita
	 *            partitasingola da salvare nel database
	 */
	public UpdatePartitaSingolaDatabase(final Connection con, final PartitaSingola partita) {
		this.con = con;
		this.p = partita;
	}//UpdatePartitaSingolaDatabase

	/**
	 * Savataggio di elementi di tipo partitasingola nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */

	public void updateGiocatore() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, p.getNumeroPartita());
			pstmt.setDate(2, p.getData());
			pstmt.setTime(3, p.getOra());
			pstmt.setInt(4, p.getCampo());
			pstmt.setString(5, p.getCircolo());
			pstmt.setString(6, p.getSfidanteA());
			pstmt.setString(7, p.getSfidanteB());
			pstmt.setBoolean(8, p.isvittoriaSfidanteA());
			pstmt.setInt(9, p.getTorneo());
			pstmt.setString(10, p.getSet1().toString());
			pstmt.setString(11, p.getSet2().toString());
			pstmt.setString(12, p.getSet3().toString());
			
			pstmt.setInt(13, p.getNumeroPartita());
			pstmt.setInt(14, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateGiocatore
	
	
	/**
	 * Aggiornamento del vincitore di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateVincitore() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTRESULT);

			pstmt.setBoolean(1, p.isvittoriaSfidanteA());
			
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateVincitore
	
	
	/**
	 * Aggiornamento del vincitore di una partita, incluso il campo forfait.
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateRisultato() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTRESULT_FORFAIT);

			pstmt.setBoolean(1, p.isvittoriaSfidanteA());
			pstmt.setBoolean(2, p.getForfait());
			
			pstmt.setInt(3, p.getNumeroPartita());
			pstmt.setInt(4, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateRisultato
	
	/**
	 * Modifica giocatore di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateSfidante() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTPLAYER);

			pstmt.setString(1, p.getSfidanteA());
			pstmt.setString(2, p.getSfidanteB());
			
			pstmt.setInt(3, p.getNumeroPartita());
			pstmt.setInt(4, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidante
	
	
	/**
	 * Modifica giocatoreA di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateSfidanteA(String vincitore) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTPLAYERA);

			pstmt.setString(1, vincitore);
			//pstmt.setString(2, p.getSfidanteB());
			
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA
	
	/**
	 * Modifica giocatoreB di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateSfidanteB(String vincitore) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTPLAYERB);

			pstmt.setString(1, vincitore);
			//pstmt.setString(2, p.getSfidanteB());
			
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA

	/**
	 * Aggiornamento dei risultati dei set
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateSet(Risultato set1, Risultato set2, Risultato set3) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTSET);

			pstmt.setString(1, set1.toString());
			pstmt.setString(2, set2.toString());
			pstmt.setString(3, set3.toString());
			//pstmt.setString(2, p.getSfidanteB());
			
			pstmt.setInt(4, p.getNumeroPartita());
			pstmt.setInt(5, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA
	
	/**
	 * Aggiornamento dei risultati dei set (string version)
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateSet(String set1, String set2, String set3) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTSET);

			pstmt.setString(1, set1);
			pstmt.setString(2, set2);
			pstmt.setString(3, set3);
			//pstmt.setString(2, p.getSfidanteB());
			
			pstmt.setInt(4, p.getNumeroPartita());
			pstmt.setInt(5, p.getTorneo());
			
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA
	
	/**
	 * Aggiornamento della data di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateData(Date data) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTDATE);

			pstmt.setDate(1, data);
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA

	
	/**
	 * Aggiornamento dell'ora di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateOra(Time ora) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTTIME);

			pstmt.setTime(1, ora);
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA

	
	/**
	 * Aggiornamento del campo di una partita
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
	 */
	public void updateCampo(int campo) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENTCAMPO);

			pstmt.setInt(1, campo);
			pstmt.setInt(2, p.getNumeroPartita());
			pstmt.setInt(3, p.getTorneo());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if-else

			con.close();
		}//try-catch

	}//updateSfidanteA

}//UpdatePartitaSingolaDatabase