package it.gemmed.servlet;

import it.gemmed.database.FindIscrizioneDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Iscrizione;
import it.gemmed.resource.Message;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet che genera la pagina disponibilita.jsp che mostra le 
 * disponibilità dei giocatori di un dato torneo;
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class DisponibilitaServlet extends AbstractDatabaseServlet {
	
	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *             Se si verificano degli errori nel comunicazione tra client/server.
	 */
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Message m = null;
		List<Iscrizione> p;
		Torneo t = null;
		try {
			p = new FindIscrizioneDatabase(DS.getConnection()).findIscrizioni(req.getParameter("id"));
			//log(p.get(0).getDisponibilita().toString());
			req.setAttribute("iscrizioni", p);
			req.getRequestDispatcher("/jsp/disponibilita.jsp").forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(e.getMessage());
		}

	}

}