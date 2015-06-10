package it.gemmed.servlet;

import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.database.UpdatePartitaSingolaDatabase;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Risultato;
import it.gemmed.resource.Torneo;
import it.gemmed.resource.Circolo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che aggiorna nel database lo sfidante di una partita.
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class UpdateSfidanteServlet extends AbstractDatabaseServlet {
	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * 
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *             Se si verificano degli errori nel comunicazione tra client/server.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String azione = req.getParameter("azione");

		int id = -1;
		int numeroPartita = -1;
		String sfidante;
		Message m = null;
		PartitaSingola partita = null;
				
		try {

			id = Integer.parseInt(req.getParameter("torneo"));
			numeroPartita = Integer.parseInt(req.getParameter("id"));//è id e non numero_partita perchè di si, ma è giusto
			sfidante = req.getParameter("sfidante");
			log("Numero partita="+Integer.toString(numeroPartita));
			log("Id del torneo ="+Integer.toString(id));
			log("sfidante ="+sfidante);
			
			partita = new FindPartitaDatabase(DS.getConnection()).findPartita(id, numeroPartita);
			if(azione.equals("A")) {
				partita.setSfidanteA(sfidante);
				new UpdatePartitaSingolaDatabase(DS.getConnection(), partita).updateSfidanteA(sfidante);	
			    }
			else {
				partita.setSfidanteB(sfidante);
				new UpdatePartitaSingolaDatabase(DS.getConnection(), partita).updateSfidanteB(sfidante);
			}
			
			m = new Message("aggiornamento riuscito");
		} catch (SQLException ex) {
			log(ex.getMessage());
			log(ex.getSQLState());
		}
			
		req.setAttribute("id", partita.getTorneo());
		req.setAttribute("numeroPartita", numeroPartita);
		
		res.sendRedirect("profiloTorneoArbitro?id="+partita.getTorneo());
	}
}